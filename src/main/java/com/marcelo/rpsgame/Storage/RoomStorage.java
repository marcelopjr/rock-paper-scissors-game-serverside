package com.marcelo.rpsgame.Storage;

import java.util.HashMap;
import java.util.Map;

import com.marcelo.rpsgame.models.Room;

public class RoomStorage {
	
	private static Map<String, Room> rooms;
	private static RoomStorage instance;
	
	private RoomStorage() {
		rooms = new HashMap<>();
	}
	
	public static synchronized RoomStorage getInstance() {
		if(instance == null) {
			instance = new RoomStorage();
		}
		return instance;
	}
	
	public Map<String, Room> getRooms(){
		return rooms;
	}
	
	public void setRoom(Room room) {
		rooms.put(room.getRoomId(), room);
	}

}
