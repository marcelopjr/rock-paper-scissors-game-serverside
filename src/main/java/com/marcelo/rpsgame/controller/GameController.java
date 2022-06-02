package com.marcelo.rpsgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.rpsgame.exception.GlobalException;
import com.marcelo.rpsgame.models.GamePlay;
import com.marcelo.rpsgame.models.Room;
import com.marcelo.rpsgame.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@PostMapping("/gameplay/{roomId}")
	public ResponseEntity<?> gamePlay(@PathVariable String roomId, @RequestBody GamePlay gamePlay) throws GlobalException{
		Room room = gameService.gameplay(roomId, gamePlay);
		simpMessagingTemplate.convertAndSend("/topic/game-progress/"+roomId, room);
		return ResponseEntity.ok(room);
	}

}
