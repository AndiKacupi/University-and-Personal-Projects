class Travel {
	private String startPoint;
	private String endPoint;
	private double distance;
	
	public Travel(String startPoint, String endPoint, double distance){
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.distance = distance;
	}
	
	public String toString(){
		return startPoint + ":" + endPoint;
	}
	
	public void setStartPoint(String startPoint){
		this.startPoint = startPoint;
	}
	
	public void setEndPoint(String endPoint){
		this.endPoint = endPoint;
	}
	
	public void setDistance(double distance){
		this.distance = distance;
	}
	
	public String getStartPoint(){
		return startPoint;
	}
	
	public String getEndPoint(){
		return endPoint;
	}
	
	public double getDistance(){
		return distance;
	}
}