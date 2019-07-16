package com.dto;

public class BoardDTO {
	private int num;
	private String boardName;
	private String[] idList;
	private String adminId;
	
	public BoardDTO(int num, String boardName, String[] idList, String adminId)
	{
		this.num = num;
		this.boardName = boardName;
		this.idList = idList;
		this.adminId = adminId;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String[] getIdList() {
		return idList;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
}
