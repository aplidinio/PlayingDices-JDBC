package com.ITAcademy.dices.DAO;

import java.util.List;

import com.ITAcademy.dices.domains.Games;

//Not necessary to run, but recommended as good practice
public interface IGamesDAO {
	
	// Add game
	public Games gambleDices(Integer idPlayer, int numberDices);
	
	// List all games
	public List<Games> getAllGames();
	
	// List all games from single player
	public List<Games> getPlayerGames(Integer idPlayer);
	
	// Delete all games from a player
	public void deleteGames(Integer idPlayer);
	
	// Calculate player Success
	public void setPlayerSuccess(Integer idPlayer);

}
