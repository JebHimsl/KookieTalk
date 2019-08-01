package com.kookietalk.kt.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PhotoController extends BaseController {

	public static final String PARAM_LATESTPHOTO = "LATEST_PHOTO_PARAM";

	@RequestMapping(value = "/uploadPhoto", method = RequestMethod.GET)
	public String uploadPhotoForm(ModelMap model, HttpServletRequest request) {
		model.addAttribute(PARAM_BASE_URL, getBaseURL(request));
		// return "uploadPhoto";
		return "photoViewer";
	}

	@RequestMapping(value = "/uploadimgctlr", method = RequestMethod.POST)
	public String uploadImageCtlr(ModelMap model, HttpServletRequest request, @RequestParam MultipartFile file) {
		String latestUploadPhoto = "";
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		//System.out.println("RootPath=[" + rootPath + "]");
		File dir = new File(rootPath + File.separator + "img");
		if (!dir.exists()) {
			dir.mkdirs();
		}

		File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
		latestUploadPhoto = file.getOriginalFilename();
		
		//System.out.println("Writing file to: " + serverFile.getAbsolutePath());

		// write uploaded image to disk
		/*
		 * try { 
		 * 		try ( InputStream is = file.getInputStream(); BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) { 
		 * 		int i;
		 * 		while ((i = is.read()) != -1) { 
		 * 			stream.write(i); 
		 * 		} 
		 * 		stream.flush(); 
		 * 		} 
		 * } catch (IOException e) { 
		 * 		System.out.println("error : " + e.getMessage()); 
		 * } 
		 * 
		 /**/

		InputStream is = null;
		BufferedOutputStream stream = null;
		try {
			is = file.getInputStream();
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			int i;
			while ((i = is.read()) != -1) {
				stream.write(i);
			}
			stream.flush(); 
		} catch (IOException ioex) {
			ioex.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException ioex) {
				ioex.printStackTrace();
			}
			try {
				if (stream != null) {
					stream.close();
				}
			} catch (IOException ioex) {
				ioex.printStackTrace();
			}
		}

		// send baseURL to jsp
		model.addAttribute(PARAM_BASE_URL,	getBaseURL(request));
	
		//send photo name to jsp
        model.addAttribute(PARAM_LATESTPHOTO, latestUploadPhoto);
        //return "uploadPhoto";
        return "photoViewer";
    }
}