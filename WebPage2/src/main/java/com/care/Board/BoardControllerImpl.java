package com.care.Board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.care.Common.BoardTools;

@RequestMapping("board")
@Controller
public class BoardControllerImpl {
	
@Autowired
private BoardService boardServ;

private final int BLOCKSIZE = 4;
private static final Logger logger = LoggerFactory.getLogger(BoardControllerImpl.class);



@RequestMapping("getContents/{num}")
public String getContents(Model model, @PathVariable String num) {
	Board board = boardServ.getContents(Integer.parseInt(num));
	model.addAttribute("board", board);
	return "forward:/view";
}



@RequestMapping("selectBoard")
public String searchBoard(Model model,
		@RequestParam(value= "pn", defaultValue="1") String pn,
		@RequestParam(value= "searchSelect", defaultValue="") String searchSelect,
		@RequestParam(value= "search", defaultValue="") String search
		) {
	
	List<Board> boardLst = boardServ.getBoard(pn,searchSelect,search);
	String nav = boardServ.getNav(pn,searchSelect,search);
	
	model.addAttribute("search", boardServ.getSearch());
	model.addAttribute("nav", nav);
	model.addAttribute("boardLst", boardLst);
	
	return "forward:/board";
}

@RequestMapping("deleteBoard/{deleteNum}")
public String deleteBoard(Model model,
		@PathVariable String deleteNum
		) {
	String [] checkBoxArr = deleteNum.split(" ");
	
	boardServ.deleteBoard(checkBoxArr);
	
	return "forward:/board/selectBoard";
}

@RequestMapping("writeBoard")
public String writeBoard() {
	
	return "forward:/write";
}

@RequestMapping("writeProc")
public String writeProc(Board board) {
	
	boardServ.writeProc(board);
	
	return "forward:/board/selectBoard";
}
}
