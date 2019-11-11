package com.ITAcademy.dices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ITAcademy.dices.domains.Games;
import com.ITAcademy.dices.service.GamesService;

@RestController
@RequestMapping("/dices/games")
public class GamesController {
	
	@Autowired
	GamesService gamesService;

	//to test service
	@GetMapping
	public String check() {
		return "You are in Games section!";
	}
	
	//Making 2 dices game
	@PostMapping("/{idPlayer}/twodices")
	public Games gambleTwoDices(@PathVariable Integer idPlayer) {
		return gamesService.gambleDices(idPlayer, 2);
	}
	
	//Making 6 dices game
	@PostMapping("/{idPlayer}/sixdices")
	public Games gambleSixDices(@PathVariable Integer idPlayer) {
		return gamesService.gambleDices(idPlayer, 6);
	}

	//List all games
	@GetMapping("/allgames")
	public List <Games> getAllGames(){  
		return gamesService.getAllGames();	
	}
	
	//List all games from single player
	@GetMapping("/{idPlayer}/allgames")
	public List <Games> getPlayerGames(@PathVariable Integer idPlayer){  
		return gamesService.getPlayerGames(idPlayer);	
	}
	
	//Delete all games from a player
	@DeleteMapping("/{idPlayer}")
	public void deleteGames(@PathVariable Integer idPlayer) {
		gamesService.deleteGames(idPlayer);
	}
	

}
