package com.care.Membership;

import java.util.List;
import java.util.Map;

public interface IDao {
	public int idCheck(String memId);
	public List<Postcode> searchPostCode(String address);
	public void membershipProc(Member member);
	public void insertMemberPostCode(Member member);
	public int loginProc(Member member);
	public String searchIDPW(Map<String, String> map);
}
