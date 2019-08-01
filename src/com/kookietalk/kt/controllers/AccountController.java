package com.kookietalk.kt.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
public class AccountController {

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor ste = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, ste);
	}
	
	 //get base URL
    public String getBaseURL(HttpServletRequest request){
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
	
	@RequestMapping("/")
	public String showHomePage() {
		return "index";
	}
	
	
	@RequestMapping("/canvas")
	public String canvas(Model model) {
		return "websocket";
	}
	
	
	@RequestMapping("/media")
	public String media(Model model) {
		return "chatSession";
	}
	
	@RequestMapping("/pricing")
	public String pricing() {
		return "pricing";
	}
	
}
