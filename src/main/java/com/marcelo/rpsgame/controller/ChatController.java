package com.marcelo.rpsgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.rpsgame.models.Chat;
import com.marcelo.rpsgame.models.MessageChat;
import com.marcelo.rpsgame.service.ChatService;

@RestController
@RequestMapping("/chat")
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@PostMapping("/create/{roomId}")
	public ResponseEntity<Chat> createChatRoom(@PathVariable String roomId){
		Chat chatCreated = chatService.createChat(roomId);
		return ResponseEntity.ok(chatCreated);
	}
	
	@PostMapping("/message/{chatId}")
	public void postMessageOnChatRoom(@PathVariable String chatId, @RequestBody MessageChat message){
		Chat chat = chatService.postMessageOnChat(chatId, message);
		simpMessagingTemplate.convertAndSend("/topic/chat-room/"+chatId, chat);
	}
	
}
