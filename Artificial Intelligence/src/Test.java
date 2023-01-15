import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) {
		
	

	 boolean isValid;
	        do {
	            isValid = true;
	        	Game newGame = new Game();
	    		newGame.play();
	    	
	            @SuppressWarnings("resource")
				Scanner userReply = new Scanner(System.in);
	            System.out.println("Do you want to play again? Answer with (Y/N)");
	            
	            String answer = userReply.nextLine().toLowerCase();
	            
	            if ("y".equals(answer)) {
	                isValid =true;
	            } else {
	                isValid =false;
	                System.out.println("game exit");
	                			
	            }
	        } while (isValid);

	        
	        
	        
	}

}