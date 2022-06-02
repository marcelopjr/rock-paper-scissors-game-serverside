package com.marcelo.rpsgame.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcelo.rpsgame.models.Player;

@Service
public class PlayerService {
	
	public Player createPlayer(String nomePlayer) {
		
		Player player = new Player();
		
		player.setPlayerId(UUID.randomUUID().toString());
		player.setNome(nomePlayer);
		
		return player;
	}

}
