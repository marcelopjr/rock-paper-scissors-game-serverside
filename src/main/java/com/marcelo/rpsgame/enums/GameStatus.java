package com.marcelo.rpsgame.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.marcelo.rpsgame.exception.GlobalException;


public enum GameStatus {
	WAITING,
	INGAME;

	@JsonCreator
	public static GameStatus verificar(String valor) throws GlobalException {
		for (GameStatus status : values()) {
			if (valor.equals(status.name())) {
				return status;
			}
		}
		throw new GlobalException("Status Inválido !!");
	}

}
