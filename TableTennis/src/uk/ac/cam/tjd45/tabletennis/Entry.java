package uk.ac.cam.tjd45.tabletennis;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Entry {

	public static String[] Name;
	static NumberFormat formatter = new DecimalFormat("#0.00");
	public static String timeframe;
	public static DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
	public static String[] Days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};



	public static void writetofile(String filename,Game[] games){
		PrintWriter gamefile;
		try {
			gamefile = new PrintWriter(new FileWriter(filename+".txt", true));
			for(int i = 0; i<games.length;i++){
				String datum = games[i].date+","+games[i].gameno+","+games[i].sessionno+","+games[i].player1+","+games[i].player2+","+games[i].score1+","+games[i].score2+"\n";
				gamefile.append(datum);
			}
			gamefile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static int getgamenum(String filename){
		try (BufferedReader br = new BufferedReader(new FileReader(filename+".txt"))) {
			String line;
			String lastgame = "";
			int i =0;
			while ((line = br.readLine()) != null) {
				lastgame = line;
				i++;
			}
			String[] LastGame = lastgame.split(",");
			return Integer.parseInt(LastGame[1]);
		} catch (FileNotFoundException e) {
			return 0;
		}
		// TODO Auto-generated catch block
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	public static int getsessionno(String filename){
		try (BufferedReader br = new BufferedReader(new FileReader(filename+".txt"))) {
			String line;
			String lastgame = "";
			int i =0;
			while ((line = br.readLine()) != null) {
				lastgame = line;
				i++;
			}
			String[] LastGame = lastgame.split(",");
			return Integer.parseInt(LastGame[2]);
		} catch (FileNotFoundException e) {
			return 0;
		}
		// TODO Auto-generated catch block
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	public static void ovprintout(String data,String player1,String player2){
		String[] printable = data.split(",");

		System.out.println("Head to Head comparison following "+printable[0]+" matches at an average of "+printable[1]+" points per match");
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Wins:", Name[0], printable[2],printable[3],Name[1]);
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Win %:", Name[0], printable[16],printable[17],Name[1]);
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Total Points:", Name[0], printable[4],printable[5],Name[1]);
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Average Points/Game:", Name[0], printable[6],printable[7],Name[1]);
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Most Consecutive Wins:", Name[0], printable[8]+"("+printable[9]+")",printable[10]+"("+printable[11]+")",Name[1]);
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Highest Winning Margin:", Name[0], printable[12]+"("+printable[13]+")",printable[14]+"("+printable[15]+")",Name[1]);

	}

	public static void dayovprintout(String data,String player1,String player2,int day){
		String[] printable = data.split(",");

		System.out.println(Days[day]+": "+printable[0]+" matches at an average of "+printable[1]+" points per match");
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Wins:", Name[0], printable[2],printable[3],Name[1]);
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Win %:", Name[0], printable[16],printable[17],Name[1]);
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Total Points:", Name[0], printable[4],printable[5],Name[1]);
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Average Points/Game:", Name[0], printable[6],printable[7],Name[1]);
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Most Consecutive Wins:", Name[0], printable[8]+"("+printable[9]+")",printable[10]+"("+printable[11]+")",Name[1]);
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Highest Winning Margin:", Name[0], printable[12]+"("+printable[13]+")",printable[14]+"("+printable[15]+")",Name[1]);

	}

	private static void indprintout(String indepth, String player1, String player2) {
		String[] printable = indepth.split(",");
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Average Points/Game Won:", Name[0], printable[2],printable[3],Name[1]);
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Average Points/Game Lost:", Name[0], printable[4],printable[5],Name[1]);
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Average Winning Margin:", Name[0], printable[6],printable[7],Name[1]);
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Games to go over 21:", "", printable[12],printable[13]+"%","");
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Wins (22+ points):", Name[0], printable[8],printable[9],Name[1]);
		System.out.printf( "%-26s %-6s %-6s %6s %6s %n","Win % (22+ points):", Name[0], printable[10],printable[11],Name[1]);
	}

	public static void detailedsessionprintout(String session, String player1, String player2) {
		String[] printable = session.split(",");
		int distinctsessions = Integer.parseInt(printable[0]);
		int j = 1;

		for(int i = 0;i<distinctsessions;i++){
			System.out.println("Session Comparison for "+printable[j+1]+" sessions of length "+printable[j]);
			System.out.printf( "%-26s %-6s %-6s %6s %6s %10s %-6s %n","Total Sessions Won:", Name[0], printable[j+2],printable[j+3],Name[1],"Drawn: ",printable[j+4]);
			System.out.printf( "%-26s %-6s %-6s %6s %6s %10s %-6s %n","Session Win Percentage:", Name[0], printable[j+5],printable[j+6],Name[1],"Drawn: ",printable[j+7]);
			j+=8;
		}
	}

	public static void sessionprintout(String session, String player1, String player2) {
		String[] printable = session.split(",");

		System.out.println("Session Comparison following "+printable[0]+" sessions at an average of "+printable[1]+" matches per session");
		System.out.printf( "%-26s %-6s %-6s %6s %6s %10s %-6s %n","Total Sessions Won:", Name[0], printable[2],printable[3],Name[1],"Drawn: ",printable[4]);
		System.out.printf( "%-26s %-6s %-6s %6s %6s %10s %-6s %n","Session Win Percentage:", Name[0], printable[5],printable[6],Name[1],"Drawn: ",printable[7]);
		System.out.println();
	}

	public static String combineOverviewData(String early, String late){
		String[] earlydata = early.split(",");
		String[] latedata = late.split(",");
		String[] outputA = new String[earlydata.length];

		outputA[0]=Integer.toString(Integer.parseInt(earlydata[0])+Integer.parseInt(latedata[0]));//total matches
		outputA[2]=Integer.toString(Integer.parseInt(earlydata[2])+Integer.parseInt(latedata[2]));//player1wins
		outputA[3]=Integer.toString(Integer.parseInt(earlydata[3])+Integer.parseInt(latedata[3]));//player2wins

		outputA[4]=Integer.toString(Integer.parseInt(earlydata[4])+Integer.parseInt(latedata[4]));//player1points
		outputA[5]=Integer.toString(Integer.parseInt(earlydata[5])+Integer.parseInt(latedata[5]));//player2points

		outputA[8]=Integer.toString(Math.max(Integer.parseInt(earlydata[8]),Integer.parseInt(latedata[8])));//PLAYER1WINSTREAK
		if(outputA[8].equals(earlydata[8])){
			if(outputA[8].equals(latedata[8])){
				outputA[9]=Integer.toString(Integer.parseInt(earlydata[9])+Integer.parseInt(latedata[9]));
			}else{
				outputA[9]=earlydata[9];
			}
		}else{
			outputA[9]=latedata[9];
		}
		outputA[10]=Integer.toString(Math.max(Integer.parseInt(earlydata[10]),Integer.parseInt(latedata[10])));//PLAYER2WINSTREAK
		if(outputA[10].equals(earlydata[10])){
			if(outputA[10].equals(latedata[10])){
				outputA[11]=Integer.toString(Integer.parseInt(earlydata[11])+Integer.parseInt(latedata[11]));
			}else{
				outputA[11]=earlydata[11];
			}
		}else{
			outputA[11]=latedata[11];
		}
		outputA[12]=Integer.toString(Math.max(Integer.parseInt(earlydata[12]),Integer.parseInt(latedata[12])));//PLAYER1HIGHESTWIN
		if(outputA[12].equals(earlydata[12])){
			if(outputA[12].equals(latedata[12])){
				outputA[13]=Integer.toString(Integer.parseInt(earlydata[13])+Integer.parseInt(latedata[13]));
			}else{
				outputA[13]=earlydata[13];
			}
		}else{
			outputA[13]=latedata[13];
		}
		outputA[14]=Integer.toString(Math.max(Integer.parseInt(earlydata[14]),Integer.parseInt(latedata[14])));//PLAYER1HIGHESTWIN
		if(outputA[14].equals(earlydata[14])){
			if(outputA[14].equals(latedata[14])){
				outputA[15]=Integer.toString(Integer.parseInt(earlydata[15])+Integer.parseInt(latedata[15]));
			}else{
				outputA[15]=earlydata[15];
			}
		}else{
			outputA[15]=latedata[15];
		}

		int totalpoints = Integer.parseInt(outputA[4])+Integer.parseInt(outputA[5]);
		int totalgames = Integer.parseInt(outputA[0]);
		double avpoints = (double)totalpoints/(double)totalgames;
		int Player1Points = Integer.parseInt(outputA[4]);
		int Player2Points = Integer.parseInt(outputA[5]);
		int Player1Wins = Integer.parseInt(outputA[2]);
		int Player2Wins = Integer.parseInt(outputA[3]);
		double Player1av=(double)Player1Points/(double)totalgames;
		double Player2av=(double)Player2Points/(double)totalgames;
		double WinRat1=(double)Player1Wins/(double)totalgames * (double)100;
		double WinRat2=(double)Player2Wins/(double)totalgames * (double)100;

		outputA[1]=formatter.format(avpoints);//AVPOINTS

		outputA[6]=formatter.format(Player1av);//PLAYER1AV
		outputA[7]=formatter.format(Player2av);//PLAYER2AV

		outputA[16]=formatter.format(WinRat1);//PLAYER1WINRAT
		outputA[17]=formatter.format(WinRat2);//PLAYER2WINRAT

		String output = outputA[0];

		for(int i = 1;i<outputA.length;i++){
			output=output+","+outputA[i];
		}

		return output;

	}
	public static String getOverviewData(String filename,List<Game> Games,String time,int Day){
		if(time.equals("l")){
			String output= getOverviewData("Archived Files/Lent Term/"+filename,Games,"",Day);
			return output;
		}else if(time.equals("e")){
			String output= getOverviewData("Archived Files/Easter Term/"+filename,Games,"",Day);
			return output;
		}else if(time.equals("2")){
			String output1 = getOverviewData("Archived Files/Lent Term/"+filename,Games,"",Day);
			String output2 = getOverviewData("Archived Files/Easter Term/"+filename,Games,"",Day);
			String output = combineOverviewData(output1,output2);

			return output;
		}
		else if(time.equals("y")){
			String output1 = getOverviewData("Archived Files/Lent Term/"+filename,Games,"",Day);
			String output2 = getOverviewData("Archived Files/Easter Term/"+filename,Games,"",Day);
			String output3 = getOverviewData(filename,Games,"",Day);
			
			String output4 = combineOverviewData(output1,output2);
			String output = combineOverviewData(output4,output3);

			return output;
		}else{
			try(BufferedReader br = new BufferedReader(new FileReader(filename+".txt"))){
				String line;
				String lastgame = "";
				int totalgames = 0;
				int totalpoints = 0;
				double avpoints;
				int Player1Wins = 0;
				int Player2Wins = 0;
				int Player1Points = 0;
				int Player2Points = 0;
				int Player1HStreak = 0;
				int Player2HStreak = 0;
				int Streak1 = 0;
				int Streak2 = 0;
				int Streakctr1 = 0;
				int Streakctr2 = 0;
				double Player1av;
				double Player2av;
				int Hmargin1 = 0;
				int Hmargin2 = 0;
				int margin1 = 0;
				int margin2 = 0;
				int margin1ctr = 0;
				int margin2ctr = 0;
				double WinRat1 = 0;
				double WinRat2 = 0;


				int i =0;
				while ((line = br.readLine()) != null) {

					lastgame = line;
					Game thisgame = new Game(lastgame);

					int gameday = getDayofWeek(thisgame.date);

					if(gameday == Day || Day == -1){


						if(thisgame.score1 > thisgame.score2){
							Player1Wins++;
							Streak1++;
							margin1 = thisgame.score1 - thisgame.score2;

							if(margin1 > Hmargin1){
								Hmargin1 = margin1;
								margin1ctr=1;
							}else if(margin1 == Hmargin1){
								margin1ctr++;
							}

							if(Streak2 > Player2HStreak){
								Streakctr2 = 1;
								Player2HStreak = Streak2;
							}else if(Streak2 == Player2HStreak){
								Streakctr2++;
							}
							Streak2 = 0;
						}else{
							Player2Wins++;
							Streak2++;
							margin2 = thisgame.score2 - thisgame.score1;

							if(margin2 > Hmargin2){
								Hmargin2 = margin2;
								margin2ctr = 1;
							}else if(margin2 == Hmargin2){
								margin2ctr++;
							}

							if(Streak1 > Player1HStreak){
								Streakctr1=1;
								Player1HStreak = Streak1;
							}else if(Streak1 == Player1HStreak){
								Streakctr1++;
							}
							Streak1 = 0;
						}
						Player1Points += thisgame.score1;
						Player2Points += thisgame.score2;

						totalpoints += thisgame.score1+thisgame.score2;
						Games.add(thisgame);
						i++;

					}
				}

				//Final check of streaks
				if(Streak1 > Player1HStreak){
					Streakctr1=1;
					Player1HStreak = Streak1;
				}else if(Streak1 == Player1HStreak){
					Streakctr1++;
				}
				if(Streak2 > Player2HStreak){
					Streakctr2=1;
					Player2HStreak = Streak2;
				}else if(Streak2 == Player2HStreak){
					Streakctr2++;
				}

				totalgames = i;

				avpoints=(double)totalpoints/(double)totalgames;
				Player1av=(double)Player1Points/(double)totalgames;
				Player2av=(double)Player2Points/(double)totalgames;
				WinRat1 = (double)Player1Wins/(double)totalgames * (double)100;
				WinRat2 = (double)Player2Wins/(double)totalgames * (double)100;

				br.close();



				return (totalgames+","+formatter.format(avpoints)+","+Player1Wins+","+Player2Wins+","+Player1Points+","+Player2Points+","+formatter.format(Player1av)+","+formatter.format(Player2av)+","+Player1HStreak+","+Streakctr1+","+Player2HStreak+","+Streakctr2+","+Hmargin1+","+margin1ctr+","+Hmargin2+","+margin2ctr+","+formatter.format(WinRat1)+","+formatter.format(WinRat2));


			} catch (FileNotFoundException e) {
				return "";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}

		}


	}

	public static String[] getDaysOverviewData(String filename,List<List<Game>> DayGames,String time){
		String[] outputs = new String[7];

		for (int i = 0; i<7;i++){
			outputs[i] = getOverviewData(filename,DayGames.get(i),time,i+1);
		}
		return outputs;
	}

	private static String getIndData(List<Game> Games, String overviewStr) {
		double avScore1 = 0;
		double avScore2 = 0;
		double avwScore1 = 0;
		double avwScore2 = 0;
		int wScore1 = 0;
		int wScore2 = 0;
		double avlScore1 = 0;
		double avlScore2 = 0;
		int lScore1 = 0;
		int lScore2 = 0;
		double avwMargin1 = 0;
		double avwMargin2 = 0;
		int wMargin1 = 0;
		int wMargin2 = 0;
		int ov20wins1 = 0;
		int ov20wins2 = 0;
		double ov20WinRat1 = 0;
		double ov20WinRat2 = 0;
		double ov20percent;

		String[] overview = overviewStr.split(",");
		int totalgames = Integer.parseInt(overview[0]);
		int Player1Points = Integer.parseInt(overview[4]);
		int Player2Points = Integer.parseInt(overview[5]);
		int Player1Wins = Integer.parseInt(overview[2]);
		int Player2Wins = Integer.parseInt(overview[3]);
		for (int i = 0;i<Games.size();i++){
			Game currentgame = Games.get(i);
			if(currentgame.score1>currentgame.score2){
				wScore1 += currentgame.score1;
				lScore2 += currentgame.score2;
				wMargin1 += (currentgame.score1 - currentgame.score2);
				if (currentgame.score2 > 19){
					ov20wins1++;
				}
			}else{
				wScore2 += currentgame.score2;
				lScore1 += currentgame.score1;
				wMargin2 += (currentgame.score2 - currentgame.score1);
				if (currentgame.score1 > 19){
					ov20wins2++;
				}
			}
		};

		int ov20matches = ov20wins1 + ov20wins2;
		avScore1 = (double)Player1Points/(double)totalgames;
		avScore2 = (double)Player2Points/(double)totalgames;
		avwScore1 = (double)wScore1/(double)Player1Wins;
		avwScore2 = (double)wScore2/(double)Player2Wins;
		avlScore1 = (double)lScore1/(double)Player2Wins;
		avlScore2 = (double)lScore2/(double)Player1Wins;
		avwMargin1 = (double)wMargin1/(double)Player1Wins;
		avwMargin2 = (double)wMargin2/(double)Player2Wins;
		ov20WinRat1 = (double)ov20wins1/(double)(ov20matches) * (double)100;
		ov20WinRat2 = (double)ov20wins2/(double)(ov20matches) * (double)100;
		ov20percent = (double)ov20matches/(double)totalgames * (double)100;

		return formatter.format(avScore1) + "," + formatter.format(avScore2) + "," + formatter.format(avwScore1) +"," + formatter.format(avwScore2)+"," + formatter.format(avlScore1)+"," + formatter.format(avlScore2)+"," + formatter.format(avwMargin1)+"," + formatter.format(avwMargin2) +"," + ov20wins1+","+ov20wins2+"," + formatter.format(ov20WinRat1)+"," + formatter.format(ov20WinRat2)+","+ov20matches+","+formatter.format(ov20percent);
	}

	public static String getSessionData(List<Game> Games,String[] Sessionbreakdown){
		int numofsessions = (Games.get(Games.size()-1).sessionno-Games.get(0).sessionno)+1;
		int sessionctr = 0;
		double avsessionsize = 0;
		int sessionwinsctr1 = 0;
		int sessionwinsctr2 = 0;
		int sessionwins1 = 0;
		int sessionwins2 = 0;
		int sessionsdrawn = 0;
		double sessionwinp1 = 0;
		double sessionwinp2 = 0;
		double sessiondrawp = 0;

		List<Session> Sessions = new ArrayList<Session>();
		Game firstGame = Games.get(0);
		Session thisSession = new Session(firstGame.sessionno,firstGame);

		for(int i = 1; i < Games.size();i++){
			if(Games.get(i).sessionno==Games.get(i-1).sessionno){
				thisSession.addGame(Games.get(i));
			}else{
				Sessions.add(thisSession);
				thisSession = new Session(Games.get(i).sessionno,Games.get(i));
			}

		}
		Sessions.add(thisSession);

		for(int i = 0;i<Sessions.size();i++){
			thisSession = Sessions.get(i);
			sessionctr += thisSession.sessionsize;

			for(int j = 0;j<thisSession.sessionsize;j++){
				if(thisSession.games.get(j).score1>thisSession.games.get(j).score2){
					sessionwinsctr1++;
				}else{
					sessionwinsctr2++;
				}
			}

			if(sessionwinsctr1>sessionwinsctr2){
				sessionwins1++;
			}else if(sessionwinsctr2>sessionwinsctr1){
				sessionwins2++;
			}else{
				sessionsdrawn++;
			}

			sessionwinsctr1 = 0;
			sessionwinsctr2 = 0;
		}
		avsessionsize = (double)sessionctr/(double)numofsessions;

		sessionwinp1 = (double)sessionwins1/(double)numofsessions * (double)100;
		sessionwinp2 = (double)sessionwins2/(double)numofsessions * (double)100;
		sessiondrawp = (double)sessionsdrawn/(double)numofsessions * (double)100;

		Sessionbreakdown[0] = getSessionbreakdownData(Sessions);

		return numofsessions+","+formatter.format(avsessionsize)+","+sessionwins1+","+sessionwins2+","+sessionsdrawn+","+formatter.format(sessionwinp1)+","+formatter.format(sessionwinp2)+","+formatter.format(sessiondrawp);
	}

	public static String getSessionbreakdownData(List<Session> Sessions){
		List<SessionBreakdown> Breakdowns = new ArrayList<SessionBreakdown>();

		for (int i = 0; i<Sessions.size();i++){
			boolean added = false;
			Session thisSession = Sessions.get(i);

			for(int j = 0; j<Breakdowns.size();j++){
				SessionBreakdown thisBreakdown = Breakdowns.get(j);
				if(thisSession.sessionsize==thisBreakdown.sessionlengths){
					thisBreakdown.addSession(thisSession);
					added = true;
				}
			}

			if(!added){

				Breakdowns.add(new SessionBreakdown(thisSession.sessionsize,thisSession));
			}
		}


		int distinctSessionS = (Breakdowns.size());

		String[] SessionOverviews = new String[distinctSessionS];

		int SessionWins1 = 0;
		int SessionWins2 = 0;
		int SessionDraws = 0;
		int SessionSize = 0;
		int NumofSessions = 0;
		double Av1 = 0;
		double Av2 = 0;
		double AvDrawn = 0;
		MyPair[] order = new MyPair[distinctSessionS];
		int max = Breakdowns.get(0).sessionlengths;

		for(int i = 0;i<Breakdowns.size();i++){
			order[i] = new MyPair(i,Breakdowns.get(i).sessionlengths);
		}

		for (int i = 0; i < order.length; i++) {
			for (int j = i + 1; j < order.length; j++) {
				MyPair tmp = new MyPair(0,0);
				if (order[i].value() < order[j].value()) {
					tmp = order[i];
					order[i] = order[j];
					order[j] = tmp;
				}
			}
		}

		for(int i = 0;i<distinctSessionS;i++){


			SessionWins1 = Breakdowns.get(order[i].key()).sessionwins1;
			SessionWins2 = Breakdowns.get(order[i].key()).sessionwins2;
			SessionDraws = Breakdowns.get(order[i].key()).sessiondraws;
			NumofSessions = Breakdowns.get(order[i].key()).numofsessions;
			SessionSize = Breakdowns.get(order[i].key()).sessionlengths;

			Av1 = (double)SessionWins1/(double)NumofSessions * (double)100;
			Av2 = (double)SessionWins2/(double)NumofSessions * (double)100;
			AvDrawn = (double)SessionDraws/(double)NumofSessions * (double)100;
			SessionOverviews[i] = ","+SessionSize+","+NumofSessions+","+SessionWins1+","+SessionWins2+","+SessionDraws+","+formatter.format(Av1)+","+formatter.format(Av2)+","+formatter.format(AvDrawn);
		}

		String Concat="";

		for(int i = 0;i<distinctSessionS;i++){
			Concat = Concat+SessionOverviews[i];
		}

		return distinctSessionS+Concat;
	}

	public static int getDayofWeek(String date){
		Date newdate;
		try {
			newdate = dateformat.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(newdate);
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			return dayOfWeek;
		} catch (ParseException e) {
			return -1;
		}


	}

	public static void entry(String names) {
		Name = names.split(" ");
		int sessionno = 0;
		int gamenum = 0;
		int gamesinsession;
		String[] Name = names.split(" ");
		String Filename = Name[0]+"v"+Name[1];
		boolean complete = false;
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

		System.out.println("Would you like to enter data(e), or analyse data(a)?");
		Scanner entry = new Scanner(System.in);
		String answer = entry.nextLine();
		boolean entrysuccessful = false;

		while(!complete){
			if(answer.equals("e")){
				System.out.println("Would you like to use today's date?");
				answer = entry.nextLine();

				if(answer.equals("n")){
					System.out.println("Please enter date in this format: 'dd-mm-yyyy'");
					answer = entry.nextLine();
					date = answer;
				}
				while(!entrysuccessful){
					System.out.println("Enter the number of games in this session");
					answer = entry.nextLine();
					gamesinsession = Integer.parseInt(answer);
					gamenum = getgamenum(Filename)+1;
					sessionno = getsessionno(Filename)+1;
					System.out.println("Enter the score of each game with "+Name[0]+"'s score first, separated by a space from "+Name[1]+"'s score");
					Game[] games = new Game[gamesinsession];
					for (int i = 0;i<gamesinsession;i++){
						answer = entry.nextLine();
						String[] Score = answer.split(" ");
						games[i]=new Game(gamenum+i, sessionno, date, Name[0], Name[1], Score[0], Score[1]);
					}

					System.out.println("Do you want to review this session before committing it?");
					answer=entry.nextLine();

					if(answer.equals("y")){
						System.out.println("Session number: "+sessionno);
						for(int i = 0;i<gamesinsession;i++){
							System.out.println("Game "+(i+1)+" "+games[i].player1+" "+games[i].score1+"  "+games[i].score2+" "+games[i].player2);

						}
						System.out.println("Correct?");
						answer = entry.nextLine();
						if (answer.equals("y")){
							entrysuccessful = true;
							writetofile(Filename,games);
						}
					}else{
						entrysuccessful = true;
						writetofile(Filename,games);
					}
					complete = true;
				}
			}else if (answer.equals("a")){
				List<Game> Games = new ArrayList<Game>(); 
				System.out.println("What kind of analysis would you like?");
				System.out.println("Analysis for this term only (t)");
				System.out.println("Analysis for lent term 2017 only (l)");
				System.out.println("Analysis for easter term 2017 only (e)");
				System.out.println("Analysis for last year only (2)");
				System.out.println("Analysis for whole playing period (y)");
				answer = entry.nextLine();
				timeframe = answer;

				String overview = getOverviewData(Filename,Games,timeframe,-1);
				System.out.println("General overview (g)");
				System.out.println("In depth analysis (i)");
				System.out.println("Session analysis (s)");
				System.out.println("Day of the week analysis (d)");	
				answer = entry.nextLine();
				if (answer.equals("g")){

					ovprintout(overview,Name[0],Name[1]);
					complete = true;

				}else if (answer.equals("i")){
					ovprintout(overview,Name[0],Name[1]);
					String indepth = getIndData(Games,overview);
					indprintout(indepth,Name[0],Name[1]);
					String[] breakdownData = new String[1];
					breakdownData[0] = "";
					String session = getSessionData(Games,breakdownData);
					System.out.println();
					sessionprintout(session,Name[0],Name[1]);
					detailedsessionprintout(breakdownData[0],Name[0],Name[1]);
					complete = true;
				}else if (answer.equals("s")){
					String[] breakdownData = new String[1];
					breakdownData[0] = "";
					String session = getSessionData(Games,breakdownData);
					sessionprintout(session,Name[0],Name[1]);
					detailedsessionprintout(breakdownData[0],Name[0],Name[1]);
					complete = true;
				}else if (answer.equals("d")){
					List<List<Game>> DayGames = new ArrayList<>();
					for(int i = 0;i<7;i++){
						DayGames.add(new ArrayList<Game>());
					}
					String[] weekdays = getDaysOverviewData(Filename,DayGames,timeframe);
					for (int i = 1; i<7;i++){
						dayovprintout(weekdays[i],Name[0],Name[1],i);
					}
					dayovprintout(weekdays[0],Name[0],Name[1],0);
					complete = true;
				}

			}else{
				System.out.println("Please enter a valid option");
			}

			
		}
	}

	public static void main(String[] args){

		boolean finished = false;

		while(!finished){

			boolean entrycomplete = false;

			PrintWriter namesfile2=null;
			FileOutputStream gamefile = null;
			String[] Names = new String[100];
			Scanner user_input = new Scanner(System.in);
			
			while (!entrycomplete){
				System.out.println("Are you playing with existing players? (y/n)");
				
				String answer = user_input.nextLine();
				if (answer.equals("y")){
					System.out.println("Please select from this list by entering the number ");
					try (BufferedReader br = new BufferedReader(new FileReader("namesfile.txt"))) {
						String line;
						int i =0;
						while ((line = br.readLine()) != null) {
							Names[i] = line;
							System.out.println(i+" "+Names[i]);
							i++;
						}
						String pair = user_input.nextLine();
						if(!pair.equals("q")){
							entry(Names[Integer.parseInt(pair)]);

							entrycomplete = true;
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}else{
					System.out.println("Please enter both players names separated by a space or q to cancel");
					String names = user_input.nextLine();
					if(!names.equals("q")){
						try{
							PrintWriter namesfile = new PrintWriter(new FileWriter("namesfile.txt", true));
							namesfile.append(names+"\n");
							namesfile.close();

							entry(names);
							

						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally{
							if (namesfile2 != null){
								namesfile2.close();
							}
						}

						entrycomplete = true;
						
					}

				}
			}
			
		
			
			System.out.println();
	
			String answer = user_input.nextLine();
			if(answer.equals("q")){
				finished = true;
				user_input.close();
			}
			
			
		}
		
	}
}
