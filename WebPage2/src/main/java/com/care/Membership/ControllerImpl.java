package com.care.Membership;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.HandlerMapping;

@RequestMapping("membership")
//@SessionAttributes("sessionVerifyNum")
@SessionAttributes("sessionInfo")
@Controller
public class ControllerImpl {
@Autowired
private IService iServ;
@ModelAttribute("sessionInfo")
public Map<String, Object> getSessionInfo(){
	return new HashMap<String, Object>();
	//return new TreeMap<String, Object>();
}


private static final Logger logger = LoggerFactory.getLogger(ControllerImpl.class);

@RequestMapping("idCheck")
public String idCheck(Member member, Model model,
		@ModelAttribute("sessionInfo") Map<String, Object> sInfo) {
	model.addAttribute("msg", iServ.idCheck(member, sInfo));
	model.addAttribute("member", member);
	
	return "forward:/membership";
}


@RequestMapping("sendVerifyNum")
public String sendVerifyNum(Member member, Model model,
		@ModelAttribute("sessionInfo") Map<String, Object> sInfo) {
	iServ.sendVerifyNum(sInfo);
	
	//model.addAttribute("sessionVerifyNum", iServ.sendVerifyNum());
	model.addAttribute("msg", "인증번호를 이메일로 전송하였습니다.");
	model.addAttribute("member", member);
	return "forward:/membership";
}

@RequestMapping("verifyProc")
public String verifyProc(Member member, Model model, 
		@ModelAttribute("sessionInfo") Map<String, Object> sInfo,
		@RequestParam("verifyNum") String rVNum) {
	
	model.addAttribute("msg", iServ.verifyProc(sInfo, rVNum));
	model.addAttribute("member", member);
	return "forward:/membership";
}

@RequestMapping("searchPostCode")
public String searchPostCode() {
	
	return "MemberForm/searchPostCodeForm";
}

@RequestMapping("searchZipCode")
public String searchZipCode(Model model, @RequestParam("address") String address) {
	model.addAttribute("postcodeLst", iServ.searchPostCode(address));
	
	return "MemberForm/searchPostCodeForm";
}

@RequestMapping("membershipProc")
public String membershipProc(Model model, Member member,
		@ModelAttribute("sessionInfo") Map<String, Object> sInfo) {
	
	if(iServ.membershipProc(sInfo,member)) return "forward:/login";
	
	model.addAttribute("msg", "입력된 정보를 다시 확인해주세요");
	return "forward:/membership";
}

@RequestMapping("loginProc")
public String loginProc(Model model, Member member,
		@ModelAttribute("sessionInfo") Map<String, Object> sInfo) {
	
	if(iServ.loginProc(sInfo, member)) return "redirect:/home";
	model.addAttribute("result" , "회원 정보를 확인해주세요");
	
	return "forward:/login";
}

@RequestMapping({"searchID", "searchPW"})
public String searchIDPW(Model model, HttpServletRequest req) {
	String path = (String)req.getAttribute(
			HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

	model.addAttribute("path", path);
	
	return "forward:/searchIDPW";
}

@RequestMapping("searchIDPWProc")
public String searchIDPWProc(Model model, @RequestParam("memEmail") String memEmail,
		@RequestParam(value= "memId", required = false) String memId, Member member) {
	
	String result = iServ.searchIDPW(memEmail, memId);
	
	model.addAttribute("result" , result);
	
	return "forward:/login";
}

}
