package uk.ac.cam.tjd45.tabletennis;
import java.util.ArrayList;
import java.util.List;

public class Session {
	public int sessionno;
	public List<Game> games = new ArrayList<Game>();
	public int sessionsize = 0;
	
	public Session(int number,Game firstgame){
		sessionno = number;
		games.add(firstgame);
		sessionsize++;
	}
	
	public void addGame(Game game){
		games.add(game);
		sessionsize++;
	}
}
