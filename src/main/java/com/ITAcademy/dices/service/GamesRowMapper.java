package com.ITAcademy.dices.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.ITAcademy.dices.domains.Games;

public class GamesRowMapper implements RowMapper {

	Map <Integer, Games> gameById = new HashMap <>();
	
	@Override
	public Collection <Games> mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		do {
		    int id = rs.getInt("id_games");
		    boolean result = rs.getBoolean("result");
		    int dice = rs.getInt("dices");
		    Games game = gameById.get(id);
		    if (game == null) {
		        game = new Games(id);
		        game.setResult(result);
		        gameById.put(game.getIdGame(), game);
		    }
		    game.addDice(dice);
		} while (rs.next());
		
		Collection <Games> games = gameById.values();
		return games;
	}

}
