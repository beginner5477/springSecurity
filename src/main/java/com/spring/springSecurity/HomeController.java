package com.spring.springSecurity;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "member/login";
	}
	
	@RequestMapping(value="/member/memberMain", method=RequestMethod.GET)
	public String getMemberMain() {
		return "member/memberMain";
	}
	@RequestMapping(value="/pwdCheck", method=RequestMethod.GET)
	public String getPwdCheck(Model model, String pwd) {
		//비밀번호 암호화처리(스프링 시큐리티에서 제공하는 비크립트패스워드인코더 객체를 이용한 암호화)
		String encPwd = passwordEncoder.encode(pwd);
		model.addAttribute("pwd",pwd);
		model.addAttribute("encPwd",encPwd);
		return "member/memberMain";
	}
	@RequestMapping(value="/pwdCheckOk", method=RequestMethod.POST)
	public String getPwdCheckOk(Model model, String pwd, String encPwd) {
		if(passwordEncoder.matches(pwd, encPwd)) {
			model.addAttribute("pwdFlag","비밀번호 일치");
		} else {
			model.addAttribute("pwdFlag","비밀번호 불일치");
		}
		return "member/memberMain";
	}
	
}
