class FlightTest
{
	public static void main(String[] args){
		Flight ATH_NYC = new Flight("ATH","NYC");
		Flight NYC_SFO = new Flight("NYC","SFO");
		Flight SKG_BCN = new Flight("SKG","BCN");
		
		Passenger p1 = new Passenger(1);
		Passenger p2 = new Passenger(2);
		Passenger p3 = new Passenger(3);
		Passenger p4 = new Passenger(4);
		
		p1.board(ATH_NYC);
		p2.board(ATH_NYC);
		System.out.println(ATH_NYC);
		p3.board(NYC_SFO);
		System.out.println(NYC_SFO);
		p4.board(SKG_BCN);
		System.out.println(SKG_BCN);
		
		p1.disembark();		
		p2.connect(NYC_SFO);
		p4.connect(NYC_SFO);
		p1.connect(NYC_SFO);

		NYC_SFO.replacePassenger(p3,p1); // 
		System.out.println("------------------");
		
		System.out.println(ATH_NYC);
		System.out.println(NYC_SFO);
		System.out.println(SKG_BCN);
	}
}