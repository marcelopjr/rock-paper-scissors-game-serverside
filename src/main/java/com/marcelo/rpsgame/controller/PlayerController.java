package com.marcelo.rpsgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.rpsgame.models.Player;
import com.marcelo.rpsgame.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@PostMapping("/create/{playerName}")
	public ResponseEntity<Player> createRoom(@PathVariable String playerName){
		return ResponseEntity.ok(playerService.createPlayer(playerName));
	}

}
