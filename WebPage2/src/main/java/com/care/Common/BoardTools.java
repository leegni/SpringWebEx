package com.care.Common;

import java.util.List;

public class BoardTools {

	public static String getNavi(int pn, int blockSize, int countBoard, String url) {
		int pageCount = countBoard/blockSize;
		if(countBoard%blockSize>0) pageCount++;
		String result="";
		
		if(pn!=1)	
			result+="<a href='"+url+(pn-1)+"'> [이전] </a>";
		for(int i=1;i<=pageCount;i++) {
			result+="<a href='"+url+i+"'> "+ i +" </a>";
		}
		if(pn!=pageCount) result+="<a href='"+url+(pn+1)+"'> [다음] </a>";
		return result;
	}
	
	public static String getSearchWord(List<String> lst, String scriptFuncName) {
		String tag = "<table><tr><td><select name='searchSelect'>";
		for(String opt:lst)
			tag += "<option>" + opt + "</option>";

		tag += "</select>";
		tag += "<input type=text name='search'/>";
		tag += "<input type=button name='searchBtn' onclick=" + scriptFuncName + " value='검색' style='width: 80px; '/>";
		tag +="</td></tr></table>";
		return tag;
	}
}
