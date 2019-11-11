package com.ITAcademy.dices.domains;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Player {
    
	private String name;
	private Integer idPlayer;  
	private Date dateRegister;
	private List <Games> games = new ArrayList <Games>();
	private double rateSuccess;
	
	public Player() {
		
	}
		
	public Player(String name, Date dateRegister, ArrayList <Games> games, int rateSuccess) {
		
		this.name = name;
		this.dateRegister = Date.valueOf(LocalDate.now());
		this.games = games;
		this.rateSuccess = rateSuccess;
	}
	
	public double getRateSuccess() {
		return rateSuccess;
	}

	public void setRateSuccess(double rateSuccess) {
		this.rateSuccess = rateSuccess;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(Integer idPlayer) {
		this.idPlayer = idPlayer;
	}

	public Date getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Date date) {
		this.dateRegister = date;
	}

	public List<Games> getGames() {
		return games;
	}

	public void setGames(List<Games> games) {
		this.games = games;
	}
	
	@Override
	public String toString() {
		return "Player [name=" + name + ", idPlayer=" + idPlayer + ", dateRegister=" + dateRegister + 
				", rateSuccess=" + rateSuccess + "]";
	}

}
