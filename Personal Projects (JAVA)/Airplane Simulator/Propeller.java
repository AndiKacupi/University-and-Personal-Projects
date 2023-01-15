class Propeller extends Aircraft{
	private double flightRange;
	private double kilometers = 0;
	private Aircraft aircraft;
	
	public Propeller(int key, double cost, Travel travel, Aircraft aircraft, double flightRange){
		super(key, cost, travel);
		this.aircraft = aircraft;
		this.flightRange = flightRange;
		if (flightRange >= travel.getDistance()){
			kilometers += travel.getDistance();
		}else{
			System.out.println("This propeller aircraft cannot fly for the travel: " + travel);
		}
	}
	
	public double computeCost(){
		return super.getCost() + 0.1*kilometers;
	}
	
	public String toString(){
		return super.toString() + ", maximum flight-range: " + flightRange + "km, Maintenance cost: $" + this.computeCost();	
	}
}