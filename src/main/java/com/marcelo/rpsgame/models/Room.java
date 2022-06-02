package com.marcelo.rpsgame.models;

public class Room {
	
	private String roomId;
	private String name;
	private Game game;
	private Player creator;
	
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Player getCreator() {
		return creator;
	}
	public void setCreator(Player creator) {
		this.creator = creator;
	}
	
}
