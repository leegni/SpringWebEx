package com.care.Board;


public class Board {

	 private Integer num;
	 // 작성자 
	 private String memId;

	 // 제목 
	 private String title;

	 // 내용 
	 private String contents;
	 
	 private Integer hits;

	 public Integer getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	// 작성일 
	 private String writedate;


	 public String getMemId() {
	     return memId;
	 }

	 public void setMemId(String memId) {
	     this.memId = memId;
	 }

	 public String getTitle() {
	     return title;
	 }

	 public void setTitle(String title) {
	     this.title = title;
	 }

	 public String getContents() {
	     return contents;
	 }

	 public void setContents(String contents) {
	     this.contents = contents;
	 }

	 public String getWritedate() {
	     return writedate;
	 }

	 public void setWritedate(String writedate) {
	     this.writedate = writedate;
	 }

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}


    public void CopyData(Board param)
    {

        this.num = param.getNum();
        this.memId = param.getMemId();
        this.title = param.getTitle();
        this.contents = param.getContents();
        this.writedate = param.getWritedate();
    	this.hits = param.getHits();
    }
	
	}