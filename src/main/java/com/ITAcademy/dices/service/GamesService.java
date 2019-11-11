package com.ITAcademy.dices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ITAcademy.dices.DAO.IGamesDAO;
import com.ITAcademy.dices.domains.Games;
import com.ITAcademy.dices.domains.Player;
//import com.ITAcademy.dices.repositories.IGamesRepository;
import com.ITAcademy.dices.tools.Gamble;

@Service
public class GamesService implements IGamesDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplateObject(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// Add game
	public Games gambleDices(Integer idPlayer, int numberDices) {
		int numberOfGames;
		String SQL1 = "SELECT MAX(id_games) FROM games";
		try {
			numberOfGames = jdbcTemplate.queryForObject(SQL1, Integer.class);
			} catch (NullPointerException e) {
			numberOfGames=0;
		}
	    Gamble gamble = new Gamble();
		Games newGame = new Games(gamble.throwDices(numberDices), gamble.winOrFailTwoDices());
		
		newGame.setIdGame(numberOfGames+1);
		
		String SQL2 = "INSERT INTO games (id_games, result, player_id_player) VALUES (?,?,?)";  // 'id_player'
		jdbcTemplate.update(SQL2, new Object[] {newGame.getIdGame(), newGame.getResult(), idPlayer});
		
		String SQL3 = "INSERT INTO games_dices (dices, games_id_games) VALUES (?,?)";
		for (int e : newGame.getDice()) {
			jdbcTemplate.update(SQL3, new Object[] {e, newGame.getIdGame()});
		}
		setPlayerSuccess(idPlayer);
		return newGame;
	}
	
	// List all games
	public List<Games> getAllGames() {    
		String SQL = "SELECT * FROM playingdices.games "
				+ "INNER JOIN playingdices.games_dices ON games.id_games = games_dices.games_id_games";
		return jdbcTemplate.query(SQL, new Object [] {}, new GamesRowMapper());
	}
	
	// List all games from single player
	public List<Games> getPlayerGames(Integer idPlayer) {
		String SQL = "SELECT * FROM playingdices.games "
				+ "INNER JOIN playingdices.games_dices ON games.id_games = games_dices.games_id_games "
				+ "WHERE player_id_player = ?";
		return jdbcTemplate.query(SQL, new Object [] {idPlayer}, new GamesRowMapper());
		
	}

	// Delete all games from a player
	public void deleteGames(Integer idPlayer) {
		String SQL = "DELETE FROM playingdices.games WHERE player_id_player = ?";  
		jdbcTemplate.update(SQL, idPlayer);
		
	}
	
	// Calculate player Success
	public void setPlayerSuccess(Integer idPlayer) {
		Player player = getPlayer(idPlayer);
		
		String SQL1 = "SELECT AVG(result) FROM playingdices.games WHERE player_id_player = ?";
		double rateSuccess = jdbcTemplate.queryForObject(SQL1, Double.class, idPlayer);
		
		rateSuccess *= 100;
		player.setRateSuccess(rateSuccess);
		
		String SQL2 = "UPDATE playingdices.player SET rate_success = ? WHERE id_player = ?";
		jdbcTemplate.update(SQL2, new Object[] {player.getRateSuccess(), idPlayer});
	}
	
	// Get player to calculate his success
	public Player getPlayer(Integer idPlayer) {
		String SQL = "SELECT * FROM player WHERE id_player = ?";
		return jdbcTemplate.queryForObject(SQL, new Object [] {idPlayer}, new PlayerRowMapper());
	}
	
		
}
