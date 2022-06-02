package com.marcelo.rpsgame.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcelo.rpsgame.Storage.RoomStorage;
import com.marcelo.rpsgame.exception.GlobalException;
import com.marcelo.rpsgame.models.Game;
import com.marcelo.rpsgame.models.GamePlay;
import com.marcelo.rpsgame.models.Player;
import com.marcelo.rpsgame.models.PlayerGame;
import com.marcelo.rpsgame.models.Room;

@Service
public class GameService {
	
	public Game createGame(Player player) {
		
		Game game = new Game();
		PlayerGame player1 = new PlayerGame();
		
		player1.setPlayer(player);
		player1.setWins(0);
		
		game.setGameId(UUID.randomUUID().toString());
		game.setPlayer1(player1);
		game.setDraws(0);
		
		return game;
		
	}
	
	public Room gameplay(String roomId, GamePlay gameplay) throws GlobalException {
		
		Room room = RoomStorage.getInstance().getRooms().get(roomId);
		Game game = room.getGame();
		PlayerGame ganhador = null;

		Player playerTurn = game.getPlayerTurn();
		
		if(!playerTurn.getPlayerId().equals(gameplay.getPlayerId())) { 
			throw new GlobalException("Não está na sua vez!");
		}
		
		if(game.isPlayer1Turn()) {
			game.getPlayer1().setMove(gameplay.getPlayerMove());
			game.setPlayerTurn(game.getPlayer2().getPlayer());
			game.setPlayer1Turn(false);
		}
		else {
			game.getPlayer2().setMove(gameplay.getPlayerMove());
			game.setPlayerTurn(game.getPlayer1().getPlayer());
			game.setPlayer1Turn(true);
		}
		
		if(game.getPlayer1().getMove() != null && game.getPlayer2().getMove() != null) {
			ganhador = checkWinner(game.getPlayer1(), game.getPlayer2());
		}
		
		if(ganhador != null) {
			
			if(ganhador.getPlayer().getPlayerId().equals(game.getPlayer1().getPlayer().getPlayerId())) {
				game.getPlayer1().setWins(game.getPlayer1().getWins()+1);
			}else {
				game.getPlayer2().setWins(game.getPlayer2().getWins()+1);
			}
			
			game.getPlayer1().setMove(null);
			game.getPlayer2().setMove(null);
		}
		
		if(game.getPlayer1().getMove() != null && game.getPlayer2().getMove() != null && ganhador == null) {
			game.setDraws(game.getDraws()+1);
			
			game.getPlayer1().setMove(null);
			game.getPlayer2().setMove(null);
		}
		
		room.setGame(game);
		RoomStorage.getInstance().setRoom(room);
		
		return RoomStorage.getInstance().getRooms().get(roomId);
		
	}
	
	public PlayerGame checkWinner(PlayerGame player1, PlayerGame player2) {
		
		if(player1.getMove().equals(player2.getMove())) {
			return null;
		}
		
		switch (player1.getMove()) {
		case "Rock": return player2.getMove().equals("Scissor") ? player1 : player2;

		case "Scissor": return player2.getMove().equals("Paper") ? player1 : player2;
			
		case "Paper": return player2.getMove().equals("Rock") ? player1 : player2;
		
		default: return null;
		}
		
	}
 
}
