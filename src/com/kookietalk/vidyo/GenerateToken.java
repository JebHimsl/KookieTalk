package com.kookietalk.vidyo;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;
import java.time.format.DateTimeParseException;

public class GenerateToken {

	public static final String PROVISION_TOKEN = "provision";
	private static final long EPOCH_SECONDS = 62167219200l;
	private static final String DELIM = "\0";

	public static String generateProvisionToken(String key, String jid, String expires, String vcard)
			throws NumberFormatException {
		String payload = String.join(DELIM, PROVISION_TOKEN, jid, expires, vcard);
		return new String(
				Base64.encodeBase64((String.join(DELIM, payload, HmacUtils.hmacSha384Hex(key, payload))).getBytes()));
	}

	public static String calculateExpiry(String expires) throws NumberFormatException {
		long expiresLong = 0l;
		long currentUnixTimestamp = System.currentTimeMillis() / 1000;
		expiresLong = Long.parseLong(expires);
		return "" + (EPOCH_SECONDS + currentUnixTimestamp + expiresLong);
	}

	private static void printUsageAndExit() {
		System.out.println();
		System.out.println("This script will generate a provision login token from a developer key");
		System.out.println("Options:");
		System.out.println("--key           Developer key supplied with the developer account");
		System.out.println("--appID         ApplicationID supplied with the developer account");
		System.out.println("--userName      Username to generate a token for");
		System.out.println("--vCardFile     Path to the XML file containing a vCard for the user (optional)");
		System.out.println("--expiresInSecs Number of seconds the token will be valid");
		System.out.println(
				"--expiresAt     Time at which the token will expire ex: (2055-10-27T10:54:22Z) can be used instead of expiresInSecs");
		System.out.println();
		System.exit(1);
	}

	
	/*
	public static void main(String[] args) {

		if (args.length == 0) {
			printUsageAndExit();
		}

		System.out.println("Args length = " + args.length);

		String key = null;
		String appID = null;
		String userName = null;
		String vCardFilePath = null; // optional
		String expiresInSeconds = null; // required if expiresAt is not set
		String expiresAt = null; // optional; used only if expiresInSeconds is not set

		for (String arg : args) {
			String[] parts = arg.split("=");
			if (parts.length > 0) {
				if ("--key".equals(parts[0])) {
					if (parts.length > 1) {
						key = parts[1];
					}
				} else if ("--appID".equals(parts[0])) {
					if (parts.length > 1) {
						appID = parts[1];
					}
				} else if ("--userName".equals(parts[0])) {
					if (parts.length > 1) {
						userName = parts[1];
					}
				} else if ("--vCardFile".equals(parts[0])) {
					if (parts.length > 1) {
						vCardFilePath = parts[1];
					}
				} else if ("--expiresInSecs".equals(parts[0])) {
					if (parts.length > 1) {
						expiresInSeconds = parts[1];
					}
				} else if ("--expiresAt".equals(parts[0])) {
					if (parts.length > 1) {
						expiresAt = parts[1];
					}
				}
			}
		}

		if (key == null) {
			System.out.println("key not set");
			printUsageAndExit();
		} else if (appID == null) {
			System.out.println("appID not set");
			printUsageAndExit();
		} else if (userName == null) {
			System.out.println("userName not set");
			printUsageAndExit();
		}

		// calculate expiration
		String expires = "";
		if (expiresInSeconds != null) {
			expires = calculateExpiry(expiresInSeconds);
		} else {
			Instant instant = null;
			if (expiresAt != null) {
				try {
					instant = Instant.parse(expiresAt);
				} catch (DateTimeParseException e) {
					System.out.println("Invalid date format. Ex: (2055-10-27T10:54:22Z)");
					printUsageAndExit();
				}
			} else {
				System.out.println("expiresInSecs or expiresAt not set");
				printUsageAndExit();
			}

			expires = String.valueOf(EPOCH_SECONDS + instant.getEpochSecond());
		}

		// vCardFile is optional
		String vCard = "";
		if (vCardFilePath != null) {
			File vCardFile = new File(vCardFilePath);
			if (!vCardFile.exists()) {
				System.out.println("File not found: " + vCardFilePath);
				System.exit(1);
			}
			try {
				vCard = new String(Files.readAllBytes(vCardFile.toPath()));
			} catch (IOException ioe) {
				System.out.println("Failed to read file: " + vCardFilePath);
				System.exit(1);
			}
		}

		try {
			System.out.println("Setting key           :  " + key);
			System.out.println("Setting appID         :  " + appID);
			System.out.println("Setting userName      :  " + userName);
			System.out.println("Setting vCardFile     :  " + vCardFilePath);
			System.out.println("Setting expiresInSecs :  " + expiresInSeconds);
			System.out.println("Setting expiresAt     :  " + expiresAt);
			System.out.println("Generating Token...");
			System.out.println(generateProvisionToken(key, userName + "@" + appID, expires, vCard));
		} catch (NumberFormatException nfe) {
			System.out.println("Failed to parse expiration time: " + expires);
			System.exit(1);
		}
		System.exit(0);

	}
	/**/

	public static String getToken(String expiresAt) {

		String token = null;
		
		// calculate expiration
		String expires = "";

		Instant instant = null;
		if (expiresAt != null) {
			try {
				instant = Instant.parse(expiresAt);
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format. Ex: (2055-10-27T10:54:22Z)");
			}
		} else {
			System.out.println("expiresAt not set");
		}

		expires = String.valueOf(EPOCH_SECONDS + instant.getEpochSecond());
		
		token = generateProvisionToken("26261bcb97e34baf9a14e7094f383c4f", "Kookie" + "@" + "c37714.vidyo.io", expires, "");
		

		return token;
	}
	
	public static void main(String[] args) {
		
		String expire = "2055-10-27T10:54:22Z";
		System.out.println("Token: " + getToken(expire));
	}
}
