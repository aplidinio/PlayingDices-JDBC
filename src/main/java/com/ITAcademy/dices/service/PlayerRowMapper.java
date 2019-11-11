package com.ITAcademy.dices.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ITAcademy.dices.domains.Player;

public class PlayerRowMapper implements RowMapper <Player> {  
	 
	@Override
	public Player mapRow(ResultSet rs, int rowNum) throws SQLException {  
	  Player player = new Player();  
	  player.setName(rs.getString("name_player"));  
	  player.setDateRegister(rs.getDate("date_register"));  
	  player.setIdPlayer(rs.getInt("id_player"));
	  player.setRateSuccess(rs.getDouble("rate_success"));
	 
	  return player;  
	 }
}
