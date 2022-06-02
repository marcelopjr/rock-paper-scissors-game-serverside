package com.marcelo.rpsgame.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.rpsgame.Storage.RoomStorage;
import com.marcelo.rpsgame.exception.GlobalException;
import com.marcelo.rpsgame.models.Game;
import com.marcelo.rpsgame.models.Player;
import com.marcelo.rpsgame.models.PlayerGame;
import com.marcelo.rpsgame.models.Room;

@Service
public class RoomService {
	
	@Autowired
	private GameService gameService;
	
	public List<Room> getAllRooms(){
		List<Room> listRoom = new ArrayList<Room>(RoomStorage.getInstance().getRooms().values());
		return listRoom;
	}
	
	public Room getRoomById(String roomId) {
		return RoomStorage.getInstance().getRooms().get(roomId);
	}
	
	public Room createRoom(String roomName, Player player) {
		
		Room room = new Room();
		Game newGame = gameService.createGame(player);
		
		room.setRoomId(UUID.randomUUID().toString());
		room.setName(roomName);
		room.setGame(newGame);
		room.setCreator(player);
		
		RoomStorage.getInstance().setRoom(room);
		return room;
	}
	
	public Room connectToRoom(Player player2, String roomId) throws GlobalException {
		
		if(!RoomStorage.getInstance().getRooms().containsKey(roomId)) {
			throw new GlobalException("Sala inexistente!");
		}
		
		Room room = RoomStorage.getInstance().getRooms().get(roomId);
		PlayerGame playerGame = new PlayerGame();
		
		playerGame.setPlayer(player2);
		playerGame.setWins(0);
		
		room.getGame().setPlayer2(playerGame);
		room.getGame().setPlayerTurn(room.getGame().getPlayer1().getPlayer());
		room.getGame().setPlayer1Turn(true);
		
		RoomStorage.getInstance().setRoom(room);
		
		return room;
	}
	
	public void closeRoom(String roomId) throws GlobalException {
		
		if(!RoomStorage.getInstance().getRooms().containsKey(roomId)) {
			throw new GlobalException("Sala inexistente!");
		}
		
		RoomStorage.getInstance().getRooms().remove(roomId);
		
	}

}
