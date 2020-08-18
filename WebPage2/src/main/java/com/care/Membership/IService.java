package com.care.Membership;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.support.SessionStatus;

public interface IService {
	public String idCheck(Member member, Map<String, Object> sInfo);
	public void sendVerifyNum(Map<String, Object> sInfo);
	public String verifyProc(Map<String, Object> sInfo, String rVNum);
	public List<Postcode> searchPostCode(String address);
	public boolean membershipProc(Map<String, Object> sInfo, Member member);
	public boolean loginProc( Map<String, Object> sInfo, Member member);
	public String searchIDPW(String memEmail, String memId);
}
