package com.marcelo.rpsgame.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.marcelo.rpsgame.Storage.ChatStorage;
import com.marcelo.rpsgame.models.Chat;
import com.marcelo.rpsgame.models.MessageChat;

@Service
public class ChatService {

	public Chat getChatById(String chatId) {
		return ChatStorage.getInstance().getChats().get(chatId);
	}
	
	public Chat createChat(String roomId) {
		
		Chat chat = new Chat();
		
		chat.setChatId(roomId);
		chat.setRoomId(roomId);
		ChatStorage.getInstance().setChats(chat);
		return chat;
	}
	
	public Chat postMessageOnChat(String chatId, MessageChat message) {
		
		Chat chat = ChatStorage.getInstance().getChats().get(chatId);
		
		message.setDate(LocalDate.now());
		if(chat.getMensagens() == null) {
			List<MessageChat> listaMessageInitialize = new ArrayList<MessageChat>();
			chat.setMensagens(listaMessageInitialize);
			chat.getMensagens().add(message);
		}else {
			chat.getMensagens().add(message);
		}
		
		
		ChatStorage.getInstance().setChats(chat);
		return chat;
	}
}
