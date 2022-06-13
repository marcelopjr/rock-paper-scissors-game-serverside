package com.marcelo.rpsgame.models;

import com.marcelo.rpsgame.enums.GameStatus;

public class Game {
	
	private String gameId;
	private PlayerGame player1;
	private PlayerGame player2;
	private Player playerTurn;
	private boolean isPlayer1Turn;
	private int draws;
	
	private Player lastWinner;
	private boolean hasAWinner;
	
	private boolean hasADraw;
	
	private GameStatus status;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public PlayerGame getPlayer1() {
		return player1;
	}

	public void setPlayer1(PlayerGame player1) {
		this.player1 = player1;
	}

	public PlayerGame getPlayer2() {
		return player2;
	}

	public void setPlayer2(PlayerGame player2) {
		this.player2 = player2;
	}

	public Player getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(Player playerTurn) {
		this.playerTurn = playerTurn;
	}

	public boolean isPlayer1Turn() {
		return isPlayer1Turn;
	}
	
	public void setPlayer1Turn(boolean isPlayer1Turn) {
		this.isPlayer1Turn = isPlayer1Turn;
	}

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public Player getLastWinner() {
		return lastWinner;
	}

	public void setLastWinner(Player lastWinner) {
		this.lastWinner = lastWinner;
	}

	public boolean isHasAWinner() {
		return hasAWinner;
	}

	public void setHasAWinner(boolean hasAWinner) {
		this.hasAWinner = hasAWinner;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public boolean isHasADraw() {
		return hasADraw;
	}

	public void setHasADraw(boolean hasADraw) {
		this.hasADraw = hasADraw;
	}
	
}
