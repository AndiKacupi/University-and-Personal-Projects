import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random; 

public class Game {
	
	Random rand = new Random(); 
	
	int rand_int = rand.nextInt(2); 
	
	private String[][] board = {{" "," "," "},{" "," "," "},{" "," "," "}};
	
	static String[][] copyBoard(String[][] aBoard){
		
		String [][] newBoard= {{"","",""},{"","",""},{"","",""}};
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(aBoard[i][j].equals("S")) {
					newBoard[i][j]="S";
				}else if(aBoard[i][j].equals("O")) {
					newBoard[i][j]="O";
				}else if(aBoard[i][j].equals(" ")) {
					newBoard[i][j]=" ";
				}
			}
		}
		return newBoard;
	}
	
	public void play() {
				Minimax minmax= new Minimax();
				boolean gameOn=true;
				boolean computersTurn=true;
				boolean firstSearch=false;
				if(computersTurn) {
					 firstSearch=true;
				}
				@SuppressWarnings("resource")
				Scanner myObj = new Scanner(System.in);
				if(rand_int==0) {
					board[1][0] = "O";
				}else {
					board[1][2] = "O";
				}
				
				while(gameOn) {
					if(computersTurn) {
						System.out.println("Computer's Turn *\n");
						String [][] newBoard = copyBoard(board);
						State stateCo = new State(newBoard);
						if(firstSearch) {
							stateCo.setFirstState(true);
							firstSearch=false;
						}
						//stateCo.setFirstState(true);
						stateCo.printState();
						minmax.findBestMove(stateCo,true);
						ArrayList<State> c = stateCo.getChildrenStates();
						float max = -10;
						for(int i=0;i<c.size();i++){
							//c.get(i).printState();
							//System.out.println(c.get(i).getValue());
							if(max<c.get(i).getValue()) {
								max=c.get(i).getValue();
								stateCo=c.get(i);
							}
						}
						stateCo.printState();
						computersTurn=false;
						board=stateCo.getBoard();
						if(stateCo.isTerminalState(false)) {
							gameOn=false;
							if(stateCo.getValue()==0) {
								System.out.println("It's a Draw !!!");
							}else {
								System.out.println("Computer Wins !!!");
							}
						}
					}else {
						System.out.println("Player's Turn *\n");
						State statePl = new State(board);
						statePl.printState();
						System.out.println("Enter symbol (S OR O)");
					    String usersNextSymbol = myObj.nextLine();
					    
					    while ( !usersNextSymbol.equals("s") &&  !usersNextSymbol.equals("S") && !usersNextSymbol.equals("o") && !usersNextSymbol.equals("O")) {
					    	System.out.print("Invalid variable ,\n");
	                		System.out.print("try again! \n" );
	                		System.out.print("Valid var: S / O ?  \n");
	                		usersNextSymbol = myObj.nextLine();
					    }
					    
					    
					   
					    
					    System.out.print("chose a row : 0 / 1/ 2 ? \n");  // Elenxo egkirotitas (apo 1 eos 3)
		                int grammi = myObj.nextInt();
		                myObj.nextLine();
		                while(grammi != 1 && grammi != 2 && grammi !=0) {
		                	System.out.print("Invalid variable   \n");
		                	System.out.print("try again! \n");
		                	System.out.print("valid cor -0,1,2- \n");
		                	grammi= myObj.nextInt();
		                }
		                System.out.print("chose a column: 0 / 1 / 2 ? ");  // Elenxo egkirotitas (apo 1 eos 3)
		                int stili = myObj.nextInt();
		                myObj.nextLine();
		                while(stili != 1 && stili != 2 && stili !=0) {
		                	System.out.print("Invalid variable   \n");
		                	System.out.print("try again! \n");
		                	System.out.print("valid cor -0,1,2- \n");
		                	stili= myObj.nextInt();
		                }
		                System.out.print("\n");
		                
					    
					    if (usersNextSymbol.equals("s")) {
					    	usersNextSymbol = "S";
					    }else if ( usersNextSymbol.contentEquals("o")) {
					    	usersNextSymbol = "O";
						}
					   
					    
					    String gr = String.valueOf(grammi);
					    String st = String.valueOf(stili);
					    board[Integer.parseInt(gr)][Integer.parseInt(st)]=usersNextSymbol;
					    //System.out.
					    statePl = new State(board);
					    //statePl.printState();
					    //System.out.print(statePl.isTerminalState(true));
					    computersTurn=true;
					    if(statePl.isTerminalState(true)) {
							gameOn=false;
							if(statePl.getValue()==0) {
								System.out.println("It's a Draw !");
							}else {
								System.out.println("Player Wins !");
							}
							statePl.printState();
						}
					}
				}
				
	}
}
