class Aircraft {
	private int key;
	private double cost;
	private Travel travel;
	
	public Aircraft(int key, double cost, Travel travel){
		this.key = key;
		this.cost = cost;
		this.travel = travel;
	}
	
	public String toString(){
		return travel + ", $" + cost;
	}
	
	public void display(){
		System.out.println(this);
		System.out.println("Flight distance " + travel.getDistance() + " km");
		System.out.println("");
	}
	
	public void setCost(double cost){
		this.cost = cost;
	}
	
	public double getCost(){
		return cost;
	}
}