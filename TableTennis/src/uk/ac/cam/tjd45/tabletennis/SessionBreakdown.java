package uk.ac.cam.tjd45.tabletennis;
import java.util.ArrayList;
import java.util.List;

public class SessionBreakdown {
	public List<Session> sessions = new ArrayList<Session>();
	public int sessionlengths = 0;
	public int numofsessions = 0;
	public int sessionwins1 = 0;
	public int sessionwins2 = 0;
	public int sessiondraws = 0;
	
	SessionBreakdown(int sessionlength,Session session){
		sessionlengths = sessionlength;
		sessions.add(session);
		numofsessions++;
		if(SessionWinner(session)==1){
			sessionwins1++;
		}else if(SessionWinner(session)==2){
			sessionwins2++;
		}else{
			sessiondraws++;
		}
	}
	
	public void addSession(Session session){
		sessions.add(session);
		numofsessions++;
		if(SessionWinner(session)==1){
			sessionwins1++;
		}else if(SessionWinner(session)==2){
			sessionwins2++;
		}else{
			sessiondraws++;
		}
	}
	
	public int SessionWinner(Session session){
		int ctr = 0;
		for(int i = 0;i<session.sessionsize;i++){
			for(int j = 0;j<session.games.size();j++){
				if(session.games.get(j).score1>session.games.get(j).score2){
					ctr++;
				}else{
					ctr--;
				}
			}
		}
		if(ctr>0){
			return 1;
		}else if(ctr<0){
			return 2;
		}else{
			return 0;
		}
	}
}
