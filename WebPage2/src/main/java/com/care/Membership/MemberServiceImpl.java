package com.care.Membership;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.SessionStatus;

import com.jin.mail.SHA;

@Service
public class MemberServiceImpl implements IService {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired
	private IDao iDao;
	
	final String NOTUSEDID = "사용 가능한 아이디 입니다.";
	final String USEDID = "이미 사용중인 아이디 입니다.";
	final String VERIFYCHECKOK = "인증 성공";
	final String VERIFYCHECKFAIL = "인증 실패";
	final String ALREADYVERIFY = "이미 인증 되었습니다.";
	final String NOVERIFYNUM = "인증번호가 없습니다.";
	
	@Override
	public String idCheck(Member member, Map<String, Object> sInfo) {
		String id = (String)sInfo.get("checkedId");
		if(id!=null && id.contentEquals(member.getMemId())) {
			logger.warn("session");
			
			return NOTUSEDID;
		}
		if(iDao.idCheck(member.getMemId())==0) {
			sInfo.put("checkedId", member.getMemId());
			logger.warn("db");
			return NOTUSEDID;
		}
		return USEDID;
	}

	@Override
	public void sendVerifyNum(Map<String, Object> sInfo) {
		String vNum = String.format("%04d", (int)(Math.random()*10000));
		sInfo.put("verifyNum", vNum);
	
	}

	@Override
	public String verifyProc(Map<String, Object> sInfo, String rVNum) {
		String vNum = (String)sInfo.get("verifyNum");
		Boolean verifyOK = (Boolean)sInfo.get("verifyOK");
		if(verifyOK!=null && verifyOK==true) return ALREADYVERIFY;
		if(vNum==null) return NOVERIFYNUM;
		if(rVNum.contentEquals(vNum)) {
			sInfo.remove("verifyNum");
			sInfo.put("verifyOK", true);
			return VERIFYCHECKOK;
		}
		
		return VERIFYCHECKFAIL;
		
	}

	@Override
	public List<Postcode> searchPostCode(String address) {
		return iDao.searchPostCode(address);
	}

	@Override
	public boolean membershipProc(Map<String, Object> sInfo, Member member) {
		String iOK = (String)sInfo.get("checkedId");
		Boolean vOK = (Boolean)sInfo.get("verifyOK");
		
		if(iOK!=null && member.getMemId().contentEquals(iOK) && vOK!=null && vOK==true) {
			iDao.membershipProc(member);
			if(!"".contentEquals(member.getMemPostCode()))
				iDao.insertMemberPostCode(member);
		sInfo.remove("verifyOK");
		sInfo.remove("checkedId");
		
		return true;
		}
		else
		return false;
	}

	@Override
	public boolean loginProc( Map<String, Object> sInfo, Member member) {
		
		sInfo.put("loginId", member.getMemId());
		if(iDao.loginProc(member)==1) return true;
		return false;
	}

	@Override
	public String searchIDPW(String memEmail, String memId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("memEmail", memEmail);
		map.put("memId", memId);
		
		String retId = iDao.searchIDPW(map);
		String pass = "P@ssW0rd";
		SHA sha = new SHA();
		sha.encryptSHA512(pass);
		// 아이디가 존재하는 경우 이메일로 패스워드 전송 , 멤버 테이블 패스워드 업데이트
		
		if(retId==null) return "회원 정보가 없습니다.";
		return "이메일에 회원님의 정보를 보내드렸습니다";
	}

	
}
