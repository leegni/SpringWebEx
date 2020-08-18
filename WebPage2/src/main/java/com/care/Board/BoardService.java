package com.care.Board;

import java.util.List;
import java.util.Map;

public interface BoardService {
	public List<Board> getBoard(String pn,String searchSelect, String search);
	public Board getContents(int num);
	public String getNav(String pn,String searchSelect, String search);
	public String getSearch();
	public void deleteBoard(String [] num);
	public void writeProc(Board board);
}
