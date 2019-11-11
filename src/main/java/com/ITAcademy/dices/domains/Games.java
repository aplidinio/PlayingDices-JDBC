package com.ITAcademy.dices.domains;


import java.util.ArrayList;
import java.util.List;

public class Games {
		
	private int idGame;
	private List <Integer> dices = new ArrayList<Integer>();
	private boolean result;

	public Games() {
	}
	
	public Games(int idGame) {
		this.idGame = idGame;
	}
	
	public Games(ArrayList <Integer> dices, boolean result) {
		
		this.dices = dices;
		this.result = result;
	}
		
	public int getIdGame() {
		return idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}

	public List<Integer> getDice() {
		return dices;
	}

	public void setDice(List<Integer> dices) {
		this.dices = dices;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return "Games [idGame=" + idGame + ", dices=" + dices + ", result=" + result + ", player=" +"]";
	}

	public void addDice(int dice) {
		dices.add(dice);
		
	}
}
