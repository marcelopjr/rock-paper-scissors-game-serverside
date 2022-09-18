package com.marcelo.rpsgame.Storage;

import java.util.HashMap;
import java.util.Map;

import com.marcelo.rpsgame.models.Chat;

public class ChatStorage {
	
	private static Map<String, Chat> chats;
	private static ChatStorage instance;
	
	private ChatStorage() {
		chats = new HashMap<>();
	}
	
	public static synchronized ChatStorage getInstance() {
		if(instance == null) {
			instance = new ChatStorage();
		}
		return instance;
	}
	
	public Map<String, Chat> getChats(){
		return chats;
	}
	
	public void setChats(Chat chat) {
		chats.put(chat.getRoomId(), chat);
	}

}
