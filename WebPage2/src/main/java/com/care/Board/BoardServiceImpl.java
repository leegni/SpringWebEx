package com.care.Board;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.Common.BoardTools;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;
	private final int BLOCKSIZE = 4;
	private final String NAVURL = "/WebPage2/board/selectBoard";
	private final String PN = "pn=";
	private final String SEARCHSELECT = "searchSelect=";
	private final String SEARCH = "search=";
	
	private String getSearchSelect(String opt) {
		if("작성자".contentEquals(opt))
			return "memId";
		else if("제목".contentEquals(opt))
			return "title";

			return "all";
	}


	@Override
	public Board getContents(int num) {
		Board board = boardDao.getContents(num);
		
		return board;
	}


	@Override
	public String getSearch() {
		List<String> lst = new ArrayList<String>();
		lst.add("전체");
		lst.add("작성자");
		lst.add("제목");
		
		return BoardTools.getSearchWord(lst, "SearchBoard();");
	}

	@Override
	public List<Board> getBoard(String pn, String searchSelect, String search) {
		Map<String, Object> map = new HashMap<String, Object>();
		//int pNum = 0;
		//(pn !=null ) pNum = Integer.parseInt(pn)-1;	
		//if(searchSelect==null) searchSelect="";
		
		int pNum =Integer.parseInt(pn)-1;	
		map.put("start",pNum*BLOCKSIZE);
		map.put("end",(pNum+1)*BLOCKSIZE);
		map.put("searchSelect", getSearchSelect(searchSelect));
		map.put("search", /*search==null?"":*/search);
		
		List<Board> boardLst = boardDao.getBoard(map);
		
		return boardLst;
	}
	
	@Override
	public String getNav(String pn,  String searchSelect, String search) {
		
		int pageNum =Integer.parseInt(pn);
		
		searchSelect =getSearchSelect(searchSelect);
		try {search = URLEncoder.encode(search, "UTF-8");} 
		catch (UnsupportedEncodingException e) {e.printStackTrace();}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchSelect", searchSelect);
		map.put("search", search);
		
		return BoardTools.getNavi(pageNum, BLOCKSIZE, 
				boardDao.countBoard(map), 
				NAVURL+"?"+SEARCHSELECT+searchSelect+"&"+
				SEARCH+search+"&"+
				PN);
		
	}


	@Override
	public void deleteBoard(String [] num) {
	
		boardDao.deleteBoard(num);
	}


	@Override
	public void writeProc(Board board) {
		
		boardDao.writeProc(board);
		
	}


}
