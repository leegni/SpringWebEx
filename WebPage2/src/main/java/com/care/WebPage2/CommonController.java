package com.care.WebPage2;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.Membership.IService;


@Controller
public class CommonController {
	
	
	private static final Logger logger =
			LoggerFactory.getLogger(CommonController.class);
	
	private Map<String, String> pageMap;
	public CommonController() {
		pageMap = new HashMap<String, String>();

		pageMap.put("home", "home");
		pageMap.put("login", "MemberForm/loginForm");
		pageMap.put("membership", "MemberForm/memberForm");
		pageMap.put("searchIDPW", "MemberForm/searchIDPW");
		pageMap.put("board", "BoardForm/boardForm");
		pageMap.put("view", "BoardForm/viewForm");
		pageMap.put("write", "BoardForm/writeForm");

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/{formPath}")
	public String path(Model model, @PathVariable String formPath) {
		model.addAttribute("formPath", "form/"+formPath);
		
		return "index";
	}
	
	@RequestMapping(value = "/form/{formPath}")
	public String path(@PathVariable String formPath) {
		
		return pageMap.get(formPath);
	}
	
	
	
}
