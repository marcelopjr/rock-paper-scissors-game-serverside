package com.marcelo.rpsgame.models;

import java.util.List;

public class Chat {
	
	private String chatId;
	private String roomId;
	private List<MessageChat> mensagens;
	
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public List<MessageChat> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<MessageChat> mensagens) {
		this.mensagens = mensagens;
	}
	
}
