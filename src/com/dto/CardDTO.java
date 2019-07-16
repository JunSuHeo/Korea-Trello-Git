package com.dto;

public class CardDTO {
	private int num;
	private String title;
	private String content;
	private int listnum;
	
	public CardDTO(int num, String title, String content, int listnum)
	{
		this.num = num;
		this.title = title;
		this.content = content;
		this.listnum = listnum;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getListnum() {
		return listnum;
	}
	public void setListnum(int listnum) {
		this.listnum = listnum;
	}
}
