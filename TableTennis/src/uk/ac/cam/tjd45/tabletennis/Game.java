package uk.ac.cam.tjd45.tabletennis;

public class Game {
	public int gameno;
	public int sessionno;
	public String date;
	public String player1;
	public String player2;
	public int score1;
	public int score2;
	
	public Game(int gamenum,int sessionnum, String dateofgame, String player1name, String player2name, String sscore1,String sscore2){
		gameno=gamenum;
		sessionno=sessionnum;
		date=dateofgame;
		player1=player1name;
		player2=player2name;
		score1=Integer.parseInt(sscore1);
		score2=Integer.parseInt(sscore2);
		
	}
	
	public Game(String compressed){
		String[] decompressed = compressed.split(",");
		date = decompressed[0];
		gameno = Integer.parseInt(decompressed[1]);
		sessionno = Integer.parseInt(decompressed[2]);
		player1 = decompressed[3];
		player2 = decompressed[4];
		score1 = Integer.parseInt(decompressed[5]);
		score2 = Integer.parseInt(decompressed[6]);
		
	}
}
