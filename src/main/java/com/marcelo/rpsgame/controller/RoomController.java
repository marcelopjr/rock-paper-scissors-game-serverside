package com.marcelo.rpsgame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.rpsgame.exception.GlobalException;
import com.marcelo.rpsgame.models.Player;
import com.marcelo.rpsgame.models.Room;
import com.marcelo.rpsgame.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@GetMapping()
	public ResponseEntity<List<Room>> createRoom(){
		return ResponseEntity.ok(roomService.getAllRooms());
	}
	
	@GetMapping("/id/{roomId}")
	public ResponseEntity<Room> getRoomById(@PathVariable String roomId){
		return ResponseEntity.ok(roomService.getRoomById(roomId));
	}

	@PostMapping("/create/{roomName}")
	public ResponseEntity<Room> createRoom(@PathVariable String roomName, @RequestBody Player player1){
		Room roomCreated = roomService.createRoom(roomName, player1);
		List<Room> listRoomsUpdated = roomService.getAllRooms();
		simpMessagingTemplate.convertAndSend("/topic/newrooms", listRoomsUpdated);
		return ResponseEntity.ok(roomCreated);
	}
	
	@PostMapping("/connect/{roomId}")
	public ResponseEntity<Room> connectRoom(@PathVariable String roomId, @RequestBody Player player2) throws GlobalException{
		Room room = roomService.connectRoom(player2, roomId);
		simpMessagingTemplate.convertAndSend("/topic/game-progress/"+roomId, room);
		return ResponseEntity.ok(room);
	}
	
	@PostMapping("/disconnect/{roomId}/{playerId}")
	public ResponseEntity<Room> disconnectRoom(@PathVariable String roomId, @PathVariable String playerId) throws GlobalException{
		Room room = roomService.disconnectRoom(roomId, playerId);
		if(room == null) {
			List<Room> listRoomsUpdated = roomService.getAllRooms();
			simpMessagingTemplate.convertAndSend("/topic/newrooms", listRoomsUpdated);
			return ResponseEntity.ok(room);
		}else {
			simpMessagingTemplate.convertAndSend("/topic/game-progress/"+roomId, room);
			return ResponseEntity.ok(room);
		}
		
	}
	
	@DeleteMapping("/close/{roomId}")
	public ResponseEntity<String> closeRoom(@PathVariable String roomId) throws GlobalException{
		roomService.closeRoom(roomId);
		List<Room> listRoomsUpdated = roomService.getAllRooms();
		simpMessagingTemplate.convertAndSend("/topic/newrooms", listRoomsUpdated);
		simpMessagingTemplate.convertAndSend("/topic/game-progress/"+roomId, "GAMECLOSE/"+roomId);
		return ResponseEntity.ok("Sala fechada");
	}
	
}
