package com.ITAcademy.dices.DAO;

import java.util.List;

import com.ITAcademy.dices.domains.Player;

// Not necessary to run, but recommended as good practice
public interface IPlayerDAO {
	
	// List all players
	public List <Player> getAllPlayers();
		
	// List one player
	public Player getPlayer(Integer idPlayer);
	
	// Creates a new player
	public Player addPlayer(Player newPlayer);
	
	// Delete a player
	public void deletePlayer(Integer idPlayer);
	
	// Modify a player
	public Player modifyPlayer(Player modifyPlayer, Integer idPlayer);	
	
	// Get average success from all players
	public double getAverageSuccess();
	
	// Calculate Success from a single player
	public double getPlayerSuccess(Integer idPlayer);
	
	// Get Top Player
	public List <Player> getTopPlayer();
	
	// Get worst player
	public List <Player> getBottomPlayer();

}
