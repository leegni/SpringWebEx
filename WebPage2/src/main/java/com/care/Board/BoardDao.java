package com.care.Board;

import java.util.List;
import java.util.Map;

public interface BoardDao {
	public List<Board> getBoard(Map<String, Object> map);
	public Board getContents(int num);
	public int countBoard(Map<String, Object> map);
	public void deleteBoard(String [] num);
	public void writeProc(Board board);
}
