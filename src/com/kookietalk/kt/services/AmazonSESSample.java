package com.kookietalk.kt.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AmazonSESSample {

	// Replace sender@example.com with your "From" address.
	// This address must be verified.
	static final String FROM = "mailsender@kookietalk.com";
	static final String FROMNAME = "KookieTalk Mail Sender";

	// Replace recipient@example.com with a "To" address. If your account
	// is still in the sandbox, this address must be verified.
	static final String TO = "jeb@kookietalk.com";

	// Replace smtp_username with your Amazon SES SMTP user name.
	static final String SMTP_USERNAME = "AKIAICZJEU5FE5VLMH7A";

	// Replace smtp_password with your Amazon SES SMTP password.
	static final String SMTP_PASSWORD = "AhWR28/1GAACZi3wtIDh288v2/zJJOrbDRzMTrpZWmpc";

	// The name of the Configuration Set to use for this message.
	// If you comment out or remove this variable, you will also need to
	// comment out or remove the header below.
	// static final String CONFIGSET = "ConfigSet";

	// Amazon SES SMTP host name. This example uses the US West (Oregon) region.
	// See
	// https://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
	// for more information.
	static final String HOST = "email-smtp.us-west-2.amazonaws.com";

	// The port you will connect to on the Amazon SES SMTP endpoint.
	static final int PORT = 587;

	static final String SUBJECT = "KookieTalk Password Reset";

	static final String BODY = String.join(System.getProperty("line.separator"), "<h1>KookieTalk Password Reset</h1>",
			"<p>This email was sent from KookieTalk.com due to a password reset request.",
			"<p>Please ignore this email if you do not want to reset your password.  Otherwise, please follow the link below to complete the password reset process.  Thank you.");

	public static void main(String[] args) throws Exception {

		// Create a Properties object to contain connection configuration information.
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		// Create a Session object to represent a mail session with the specified
		// properties.
		Session session = Session.getDefaultInstance(props);

		// Create a message with the specified information.
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM, FROMNAME));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
		msg.setSubject(SUBJECT);
		msg.setContent(BODY, "text/html");

		// Add a configuration set header. Comment or delete the
		// next line if you are not using a configuration set
		// msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

		// Create a transport.
		Transport transport = session.getTransport();

		// Send the message.
		try {
			System.out.println("Sending...");

			// Connect to Amazon SES using the SMTP username and password you specified
			// above.
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

			// Send the email.
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
		} catch (Exception ex) {
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
		} finally {
			// Close and terminate the connection.
			transport.close();
		}
	}

	public static void send(String to, String link, String token) throws Exception {

		if (to == null || to.trim().equals("")) {
			to = "jeb@kookietalk.com";
		}
		token = "123456";
		link = "https://kookiedev-905122306.us-west-2.elb.amazonaws.com/kt/user/resetPwd?token=" + token;
		String formatLink = "<br><a href=\"" + link + "\">Click here to complete password reset</a>";

		// Create a Properties object to contain connection configuration information.
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		// Create a Session object to represent a mail session with the specified
		// properties.
		Session session = Session.getDefaultInstance(props);

		// Create a message with the specified information.
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM, FROMNAME));
		// msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		msg.setSubject(SUBJECT);
		msg.setContent(BODY + formatLink, "text/html");

		// Add a configuration set header. Comment or delete the
		// next line if you are not using a configuration set
		// msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

		// Create a transport.
		Transport transport = session.getTransport();

		// Send the message.
		try {
			System.out.println("Sending...");

			// Connect to Amazon SES using the SMTP username and password you specified
			// above.
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

			// Send the email.
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
		} catch (Exception ex) {
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
		} finally {
			// Close and terminate the connection.
			transport.close();
		}
	}
}
