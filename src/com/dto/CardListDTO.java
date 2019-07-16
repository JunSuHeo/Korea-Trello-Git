package com.dto;

import java.util.ArrayList;

public class CardListDTO {
	private int num;
	private String title;
	private int boardNum;
	private ArrayList<CardDTO> cards;
	
	public CardListDTO(int num, String title, int boardNum, ArrayList<CardDTO> cards)
	{
		this.num = num;
		this.title = title;
		this.boardNum = boardNum;
		this.cards = cards;
	}
	
	public CardListDTO(int num, String title, int boardNum)
	{
		this(num, title, boardNum, new ArrayList<CardDTO>());
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
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public ArrayList<CardDTO> getCards() {
		return cards;
	}

	public void setCards(ArrayList<CardDTO> cards) {
		this.cards = cards;
	}
}
