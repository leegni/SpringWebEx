package com.care.Membership;

import java.util.Random;

public class VerifyProc {

	public String getVerifyNum() {
		
		Random random = new Random();
		int num = (int)Math.pow(10, 5);
		String formatStr = "%0"+5+"d";
		String randNum= String.format(formatStr,random.nextInt(num));
		
		return randNum;
		}
	
}
