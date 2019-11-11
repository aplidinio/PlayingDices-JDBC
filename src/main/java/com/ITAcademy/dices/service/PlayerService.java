package com.ITAcademy.dices.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ITAcademy.dices.DAO.IPlayerDAO;
import com.ITAcademy.dices.domains.Player;
//import com.ITAcademy.dices.repositories.IPlayerRepository;

@Service							
public class PlayerService implements IPlayerDAO{
		
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplateObject(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	// List all players
	public List <Player> getAllPlayers(){
		String SQL = "SELECT * FROM player";
		return jdbcTemplate.query(SQL, new Object [] {}, new PlayerRowMapper());

	}
	
	// List one player
	public Player getPlayer(Integer idPlayer){
		String SQL = "SELECT * FROM player WHERE id_player = ?";
		return jdbcTemplate.queryForObject(SQL, new Object [] {idPlayer}, new PlayerRowMapper());

	}
	
	// Creates a new player
	public Player addPlayer(Player newPlayer) {
		if (newPlayer.getName()=="") newPlayer.setName("Anonymous");
		newPlayer.setDateRegister(Date.valueOf(LocalDate.now()));
		String SQL = "INSERT INTO player (name_player, date_register, rate_success) VALUES (?,?,?)";  // 'id_player'
		jdbcTemplate.update(SQL, new Object[] {newPlayer.getName(), newPlayer.getDateRegister(), null});
		return newPlayer;
	}
	
	// Delete a player
	public void deletePlayer(Integer idPlayer) {
		String SQL = "DELETE FROM player WHERE id_player = ?";
	    jdbcTemplate.update(SQL, idPlayer);
	}

	// Modify a player
	public Player modifyPlayer(Player modifyPlayer, Integer idPlayer) {		
		Player player = getPlayer(idPlayer);
		player.setName(modifyPlayer.getName());

		String SQL2 = "UPDATE player SET name_player = ? WHERE id_player = ?";  // 'id_player'
		jdbcTemplate.update(SQL2, new Object[] {player.getName(), idPlayer});
		return player;
			
	}
	
	// Get average success from all players
	public double getAverageSuccess() {
		String SQL = "SELECT AVG(result) FROM playingdices.games";
		return jdbcTemplate.queryForObject(SQL, Double.class);
		
	}
	
	// Calculate Success from a single player
	public double getPlayerSuccess(Integer idPlayer){
		try {
			//String SQL = "SELECT rate_success FROM playingdices.player WHERE id_player = ?";
			String SQL = "SELECT AVG(result) FROM playingdices.games WHERE player_id_player = ?";
			return (jdbcTemplate.queryForObject(SQL, Double.class, idPlayer))*100;
		} catch (NullPointerException e) {
			return -1;
		}
	
	}

	// Get Top Player
	public List <Player> getTopPlayer() {  // list better than single player, if there is more than one player
		String SQL = "SELECT * FROM playingdices.player WHERE rate_success ="
				+ " (SELECT MAX(rate_success) FROM playingdices.player);";
		return jdbcTemplate.query(SQL, new Object [] {}, new PlayerRowMapper());
		/*return jdbcTemplate.queryForObject(SQL, new Object[]{}, (rs, rowNun) ->
		new Player(
		  rs.getInt("id_player"),
		  rs.getString("name_player"),  
		  rs.getDate("date_register"),  
		  rs.getInt("rate_success")
				));*/
	}
	
	// Get worst player
	public List <Player> getBottomPlayer() {
		String SQL = "SELECT * FROM playingdices.player WHERE rate_success ="
				+ " (SELECT MIN(rate_success) FROM playingdices.player);";
		return jdbcTemplate.query(SQL, new Object [] {}, new PlayerRowMapper());

	}
		
}



