import java.util.ArrayList;

public class State {
	
	private String[][] board;
	private ArrayList<int[]> emptyCells= new ArrayList<int[]>();
	private float stateValue ;
	private ArrayList<State> childrenStates = new ArrayList<State>();
	private boolean firstState = false;
	
	public State(String[][] aBoard) {
		board=aBoard;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(board[i][j]==" ") {
					int[] coor= {i,j};
					emptyCells.add(coor);
				}
			}
		}
	}
	
	public boolean isTerminalState(boolean maximizingPlayer) {
		if(board[0][0].equals("S") && board[0][1].equals("O") && board[0][2].equals("S")) {
			if(maximizingPlayer) {
				stateValue=-1;
			}else {
				stateValue=1;
			}
			return true;
		}else if(board[0][0].equals("S") && board[1][1].equals("O") && board[2][2].equals("S")) {
			if(maximizingPlayer) {
				stateValue=-1;
			}else {
				stateValue=1;
			}
			return true;
		}else if(board[0][0].equals("S") && board[1][0].equals("O") && board[2][0].equals("S")) {
			if(maximizingPlayer) {
				stateValue=-1;
			}else {
				stateValue=1;
			}
			return true;
		}else if(board[0][2].equals("S") && board[1][2].equals("O") && board[2][2].equals("S")) {
			if(maximizingPlayer) {
				stateValue=-1;
			}else {
				stateValue=1;
			}
			return true;
		}else if(board[2][0].equals("S") && board[2][1].equals("O") && board[2][2].equals("S")) {
			if(maximizingPlayer) {
				stateValue=-1;
			}else {
				stateValue=1;
			}
			return true;
		}else if(board[0][1].equals("S") && board[1][1].equals("O") && board[2][1].equals("S")) {
			if(maximizingPlayer) {
				stateValue=-1;
			}else {
				stateValue=1;
			}
			return true;
		}else if(board[2][0].equals("S") && board[1][1].equals("O") && board[0][2].equals("S")) {
			if(maximizingPlayer) {
				stateValue=-1;
			}else {
				stateValue=1;
			}
			return true;
		}else {
			if (emptyCells.size()==0){
				stateValue=0;
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<int[]> getEmptyCells(){
		return emptyCells;
	}
	public float getValue() {
		return stateValue;
	}
	
	public String[][] getBoard(){
		return board;
	}
	
	public void printState() {
		System.out.println("   0   1    2\n");
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				if(j==2) {
					System.out.print(board[i][j]);
				}else if(j==0){
					System.out.print(String.valueOf(i)+"  "+board[i][j]+" | ");
				}else {
					System.out.print(board[i][j]+" | ");
				}
			}
			System.out.print("\n  ------------\n");
		}
		System.out.println(" ___________________");
	}
	
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
	
	
	
	public ArrayList<State> getChildren(){
		ArrayList<String [][]> children = new ArrayList<String [][]>();
		
		for(int i=0;i<emptyCells.size();i++) {
			
			if(firstState) {
				//System.out.println("INSIDE FIRST STATE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
				if(board[1][0].equals("O")) {
					if((emptyCells.get(i)[0]!=0 || emptyCells.get(i)[1]!=0) 
							&& (emptyCells.get(i)[0]!=2 || emptyCells.get(i)[1]!=0)) {
						String [][] newBoardS = copyBoard(board);
						newBoardS[emptyCells.get(i)[0]][emptyCells.get(i)[1]]="S";
						children.add(newBoardS);
						String [][] newBoardO =  copyBoard(board);
						newBoardO[emptyCells.get(i)[0]][emptyCells.get(i)[1]]="O";
						children.add(newBoardO);
					}
					/*String [][] newBoardO =  copyBoard(board);
					newBoardO[emptyCells.get(i)[0]][emptyCells.get(i)[1]]="O";
					children.add(newBoardO);*/
				}else{
					if((emptyCells.get(i)[0]!=0 || emptyCells.get(i)[1]!=2) 
							&& (emptyCells.get(i)[0]!=2 || emptyCells.get(i)[1]!=2)) {
						String [][] newBoardS = copyBoard(board);
						newBoardS[emptyCells.get(i)[0]][emptyCells.get(i)[1]]="S";
						children.add(newBoardS);
						String [][] newBoardO =  copyBoard(board);
						newBoardO[emptyCells.get(i)[0]][emptyCells.get(i)[1]]="O";
						children.add(newBoardO);
					}
					/*String [][] newBoardO =  copyBoard(board);
					newBoardO[emptyCells.get(i)[0]][emptyCells.get(i)[1]]="O";
					children.add(newBoardO);*/
				}
				
			}else{
				String [][] newBoardS = copyBoard(board);
				newBoardS[emptyCells.get(i)[0]][emptyCells.get(i)[1]]="S";
				children.add(newBoardS);
				String [][] newBoardO =  copyBoard(board);
				newBoardO[emptyCells.get(i)[0]][emptyCells.get(i)[1]]="O";
				children.add(newBoardO);
			}
			/*if(emptyCells.get(i)[0]!=1 || emptyCells.get(i)[1]!= 1) {
			String [][] newBoardS = copyBoard(board);
			newBoardS[emptyCells.get(i)[0]][emptyCells.get(i)[1]]="S";
			children.add(newBoardS);
			}*/
					
			/*if((emptyCells.get(i)[0]!=0 || emptyCells.get(i)[1]!= 0) && 
			(emptyCells.get(i)[0]!=0 || emptyCells.get(i)[1]!= 2) &&
			(emptyCells.get(i)[0]!=2 || emptyCells.get(i)[1]!= 0) &&
			(emptyCells.get(i)[0]!=2 || emptyCells.get(i)[1]!= 2)) {*/
			/*String [][] newBoardO =  copyBoard(board);
			newBoardO[emptyCells.get(i)[0]][emptyCells.get(i)[1]]="O";
			children.add(newBoardO);*/
			//}
					
		}
		/*if(firstState) {
			for(int t=0;t<children.size();t++) {
				for(int p=0;p<3;p++) {
					for(int j=0;j<3;j++) {
						System.out.print(children.get(t)[p][j]);
					}
					System.out.print("\n");
				}
			}
		}*/
		if(firstState) {
			for(int i=0;i<children.size();i++) {
				State newState = new State(children.get(i).clone());
				newState.setFirstState(true);
				childrenStates.add(newState);
			}
			return childrenStates;
		}else {
			for(int i=0;i<children.size();i++) {
				State newState = new State(children.get(i).clone());
				childrenStates.add(newState);
			}
			return childrenStates;
		}
		
	}
	
	public ArrayList<State> getChildrenStates(){
		return childrenStates;
	}
	public void setValue(float aValue) {
		stateValue = aValue;
	}
	public void setBoard(String[][] aBoard) {
		board=aBoard;
	}
	public void setFirstState(boolean aFirstState) {
		firstState=aFirstState;
	}
}
