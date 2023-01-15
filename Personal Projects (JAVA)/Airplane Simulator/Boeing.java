class Boeing extends Aircraft{
	private double flightRange;
	private double kilometers = 0;
	private Aircraft aircraft;
	
	public Boeing(int key, double cost, Travel travel, Aircraft aircraft, double flightRange){
		super(key, cost, travel);
		this.aircraft = aircraft;
		this.flightRange = flightRange;
		if (flightRange >= travel.getDistance()){
			kilometers += travel.getDistance() + 50;
		}else{
			System.out.println("This Boeing aircraft cannot fly for the travel: " + travel);
		}
	}
	
	public double computeCost(){
		return super.getCost() + 0.2*kilometers;
	}
	
	public String toString(){
		return super.toString() + ", maximum flight-range: " + flightRange + "km, Maintenance cost: $" + this.computeCost();	
	}
	
	public void update(double kilometers){
		this.kilometers += kilometers;
	}
}