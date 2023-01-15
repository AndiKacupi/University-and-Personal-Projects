class AircraftCarrier {
	public static void main(String[] args){
		Travel travel1 = new Travel("Athens", "Ioannina", 311.0);
		Travel travel2 = new Travel("Athens", "Frankfurt", 2430.90);
		Travel travel3 = new Travel("Athens", "New York", 7919.03);
		
		Aircraft [] aircrafts = new Aircraft[4];
		
		aircrafts[0] = new Aircraft(1, 2000, travel1);
		aircrafts[1] = new Propeller (2, 2500, travel2, aircrafts[0], 4000);
		aircrafts[2] = new Propeller (3, 2600, travel3, aircrafts[0], 5000);
		aircrafts[3] = new Boeing (4, 2800, travel3, aircrafts[0], 10000);
		
		for(int i=0; i<4; i++) {
			aircrafts[i].display();
		}
	}
}