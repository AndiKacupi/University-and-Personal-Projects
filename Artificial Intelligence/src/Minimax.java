import java.util.ArrayList;

public class Minimax {
	
	static int count=0;
	State nextState;
	
	public Minimax() {
		
	}
	
	public float findBestMove(State aState, boolean maximizingPlayer) {
		
		if (aState.isTerminalState(maximizingPlayer)) {
			float stateValue = aState.getValue();
			return stateValue;
		}
		
		if (maximizingPlayer) {
			float value = -2;
			//if(value!=1 || value!=0) {
				ArrayList<State> childrenStates = aState.getChildren();
				for (int i=0;i<childrenStates.size();i++) {
					value = Math.max(value, findBestMove(childrenStates.get(i),false));
				}
			//}
			aState.setValue(value);
			return value;
		}else{
    		float value = 2;
    		//if(value!=-1 || value!=0 ) {
    			ArrayList<State> childrenStates = aState.getChildren();
    			for (int i=0;i<childrenStates.size();i++ ) {
        			value = Math.min(value, findBestMove(childrenStates.get(i),true));
        		} 
    		//}
    		aState.setValue(value);
    		return value;
		}
	}
	
	public State getNextState() {
		return nextState;
	}
}
