package com.kookietalk.kt.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.kookietalk.kt.dao.ImageDAO;
import com.kookietalk.kt.dao.UserDAO;
//import com.kookietalk.kt.dao.UserDAOImpl;
import com.kookietalk.kt.dao.UserDAOJDBC;
import com.kookietalk.kt.entity.Image;
import com.kookietalk.kt.model.Payment;
import com.kookietalk.kt.model.Timezone;
import com.kookietalk.kt.model.User;
import com.kookietalk.kt.services.AmazonSESSample;
import com.kookietalk.kt.services.CalendarHelper;
import com.kookietalk.kt.services.ChargeCreditCard;

import net.authorize.api.contract.v1.ANetApiResponse;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.MessagesType;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor ste = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, ste);
	}
	
	 //get base URL
    public String getBaseURL(HttpServletRequest request){
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
	
	@RequestMapping("/image")
	public String image(Model model) {

		model.addAttribute("user", new User());
		return "image";
	}

	@RequestMapping("/profile")
	public String profile(Model model) {

		model.addAttribute("user", new User());
		return "teachProfile";
	}

	@RequestMapping("/payment")
	public String payment(Model model) {
		model.addAttribute("user", new User());
		return "teachPayment";
	}

	@RequestMapping("/intro")
	public String intro(HttpSession session, Model model) {

		User student = (User) session.getAttribute("user");
		model.addAttribute("student", student);
		model.addAttribute("timezoneList", UserController.getTimeZoneDD());
		
		// get user photo if it exists
		Image image = ImageDAO.getUserPhoto(student.getUserId());
		if(image != null) {
			model.addAttribute("photo", image);
		} else {
			System.out.println("Image is null");
		}

		return "learnIntro";
	}

	@RequestMapping("/billing")
	public String billing(Model model) {
		model.addAttribute("payment", new Payment());
		return "learnBilling";
	}

	@RequestMapping("/newStudent")
	public String registerStudent(Model model, @ModelAttribute("user") User user) {
		model.addAttribute("timezoneList", UserController.getTimeZoneDD());
		return "registerStudent";
	}

	@RequestMapping("/saveStudent")
	public String saveStudent(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "registerStudent";
		}
		// ... and check email and password confirmations
		boolean fail = false;
		StringBuffer buf = new StringBuffer();
		String password = user.getPassword().trim();
		String confirmPwd = user.getConfirmPwd().trim();
		String email = user.getEmailAddress().trim();
		String confirmEmail = user.getConfirmEmail().trim();
		if (!password.equals(confirmPwd)) {
			buf.append("Password confirmation failed to match.<br>");
			fail = true;
		}
		if (!email.equals(confirmEmail)) {
			buf.append("Email confirmation failed to match.<br>");
			fail = true;
		}

		if (fail) {
			model.addAttribute("error", buf.toString());
			return "registerStudent";
		}
		// Save student info in database
		UserDAO udao = new UserDAOJDBC();
		boolean saveOp = udao.saveStudent(user);
		if (!saveOp) {
			model.addAttribute("error", "Email address already in use.");
			return "registerStudent";
		}
		System.out.println(saveOp);
		return "login";
	}

	@RequestMapping("/updateStudent")
	public String updateStudent(@Valid @ModelAttribute("student") User student, BindingResult result, Model model,
			HttpServletRequest request, @RequestParam CommonsMultipartFile[] image) {
		if (result.hasErrors()) {

			List<FieldError> ers = result.getFieldErrors();
			Iterator<FieldError> it = ers.iterator();
			while (it.hasNext()) {
				FieldError er = it.next();
				System.out.println("Error in " + er.getField() + " : " + er.getDefaultMessage());
			}

			return "learnIntro";
		}
		// ... and check email and password confirmations
		boolean fail = false;
		StringBuffer buf = new StringBuffer();
		String password = student.getPassword().trim();
		String confirmPwd = student.getConfirmPwd().trim();
		String email = student.getEmailAddress().trim();
		String confirmEmail = student.getConfirmEmail().trim();
		if (!password.equals(confirmPwd)) {
			buf.append("Password confirmation failed to match.<br>");
			fail = true;
		}
		if (!email.equals(confirmEmail)) {
			buf.append("Email confirmation failed to match.<br>");
			fail = true;
		}

		if (fail) {
			model.addAttribute("error", buf.toString());
			return "learnIntro";
		}

		String directory = "C:/dev/temp/";
		File file = null;
		try {
			if (image != null && image.length > 0) {
				for (CommonsMultipartFile aFile : image) {
					System.out.println("Saving file: " + aFile.getOriginalFilename());
					if (!aFile.getOriginalFilename().equals("")) {
						file = new File(directory + aFile.getOriginalFilename());
						aFile.transferTo(file);
					}
				}
			}
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
		
		InputStream is = null;
		boolean success = false;

		if (file != null) {
			try {
				 is = new FileInputStream(file); 
				 success = ImageDAO.setImage(is, student.getUserId(), "userPhoto", file.getName());
				 if(success) {
					 is.close();
					 file.delete();
				 }
			} catch (FileNotFoundException fnfex) {
				fnfex.printStackTrace();
			} catch (IOException ioex) {
				ioex.printStackTrace();
			}
		}

		// Save student info in database
		UserDAOJDBC udao = new UserDAOJDBC();
		System.out.println(student);
		boolean saveOp = udao.updateStudent(student);
		if (!saveOp) {
			model.addAttribute("error", "Update failed.");
			return "learnIntro";
		}
		return "learn";
	}

	@RequestMapping("/newAdmin")
	public String registerAdmin(Model model, @ModelAttribute("user") User user) {
		model.addAttribute("timezoneList", UserController.getTimeZoneDD());
		return "registerAdmin";
	}

	@RequestMapping("/saveAdmin")
	public String saveAdmin(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "registerAdmin";
		}
		// ... and check email and password confirmations
		boolean fail = false;
		StringBuffer buf = new StringBuffer();
		String password = user.getPassword().trim();
		String confirmPwd = user.getConfirmPwd().trim();
		String email = user.getEmailAddress().trim();
		String confirmEmail = user.getConfirmEmail().trim();
		if (!password.equals(confirmPwd)) {
			buf.append("Password confirmation failed to match.<br>");
			fail = true;
		}
		if (!email.equals(confirmEmail)) {
			buf.append("Email confirmation failed to match.<br>");
			fail = true;
		}

		if (fail) {
			model.addAttribute("error", buf.toString());
			return "registerAdmin";
		}
		// Save admin info in database
		UserDAO udao = new UserDAOJDBC();
		boolean saveOp = udao.saveAdmin(user);
		if (!saveOp) {
			model.addAttribute("error", "Email address already in use.");
			return "registerAdmin";
		}
		System.out.println(saveOp);
		return "login";
	}

	@RequestMapping("/admin")
	public String adminHome() {
		return "admin";
	}

	@RequestMapping("/learn")
	public String studentHome() {
		return "learn";
	}

	@RequestMapping("/teach")
	public String teachHome() {
		return "teach";
	}

	@RequestMapping("/newInstructor")
	public String registerInstructor(Model model, @ModelAttribute("user") User user) {
		model.addAttribute("timezoneList", UserController.getTimeZoneDD());
		return "registerInstructor";
	}

	@RequestMapping("/saveInstructor")
	public String saveInstuctor(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "registerInstructor";
		}
		// ... and check email and password confirmations
		boolean fail = false;
		StringBuffer buf = new StringBuffer();
		String password = user.getPassword().trim();
		String confirmPwd = user.getConfirmPwd().trim();
		String email = user.getEmailAddress().trim();
		String confirmEmail = user.getConfirmEmail().trim();
		if (!password.equals(confirmPwd)) {
			buf.append("Password confirmation failed to match.<br>");
			fail = true;
		}
		if (!email.equals(confirmEmail)) {
			buf.append("Email confirmation failed to match.<br>");
			fail = true;
		}

		if (fail) {
			model.addAttribute("error", buf.toString());
			return "registerInstructor";
		}
		// Save instructor info in database
		UserDAO udao = new UserDAOJDBC();
		System.out.println(user);
		boolean saveOp = udao.saveTeacher(user);
		if (!saveOp) {
			model.addAttribute("error", "Email address already in use.");
			return "registerInstructor";
		}
		System.out.println(saveOp);
		return "login";
	}

	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, HttpSession session,
			@ModelAttribute("user") User user) {
		if (error != null) {
			model.addAttribute("error", "Invalid Username or Password");
		}

		if (logout != null) {

			model.addAttribute("msg", "You have been logged out successfully.");
		}
		System.out.println("User credentials: " + user.toString());
		session.setAttribute("user", user);

		return "login";
	}

	@RequestMapping("/reset")
	public String pwdReset(Model model) {
		model.addAttribute("user", new User());
		return "pwdReset";
	}

	@RequestMapping("/sendMail")
	public String sendMail(HttpSession session) {
		// HttpSession dependency, validate session
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}

		try {
			AmazonSESSample.send("", "", "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "pwdResetFin";
	}

	@RequestMapping("/resetPwd")
	public String resetPwd(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {

		// get and verify token

		// if token is valid...

		try {
			// save the new password for this user
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "login";
	}
	
	@RequestMapping("/process")
	public String process(@Valid @ModelAttribute("payment") Payment payment, BindingResult result, Model model) {
		System.out.println("Processing CC payment");
		if(result.hasErrors()) {
			return "learnBilling";
		}else {
			String message = "";
			boolean flag = false;
			ChargeCreditCard ccc = new ChargeCreditCard();
			ANetApiResponse response = ccc.process(payment.getCcNumber(), payment.getExp(), payment.getCvv(), payment.getAmount());
			if(response != null  && response.getMessages().getResultCode() == MessageTypeEnum.OK) {	
				// set 'flag' to true if everything  is OK
				flag = true;
			}
			
			if(!flag) {
				model.addAttribute("message", "Something went wrong... will include real message later.");
				return "learnBilling";
			}
		}
		return "learn";
	}

	public static ArrayList<Timezone> getTimeZoneDD() {
		ArrayList<Timezone> list = new ArrayList<Timezone>();
		String[] ids = TimeZone.getAvailableIDs();
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			TimeZone zone = TimeZone.getTimeZone(id);
			String name = zone.getDisplayName();
			Timezone entry = new Timezone();
			entry.setKey(id);
			entry.setValue(id + " : " + name);
			list.add(entry);
		}

		Collections.sort(list, new Comparator<Timezone>() {
			public int compare(Timezone o1, Timezone o2) {
				int result = 0;

				Timezone z1 = (Timezone) o1;
				Timezone z2 = (Timezone) o2;
				result = z1.getKey().compareTo(z2.getKey());
				return result;
			}
		});
		return list;
	}

	public static void main(String[] args) {
		List<Timezone> list = UserController.getTimeZoneDD();
		Iterator<Timezone> it = list.iterator();
		while (it.hasNext()) {
			Timezone zone = it.next();
			String key = zone.getKey();
			String value = zone.getValue();
			System.out.println(key + " : " + value);
		}

	}

}
