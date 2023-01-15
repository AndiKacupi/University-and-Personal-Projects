import java.util.HashSet;
class Flight {

    private String start;
    private String destination;
    private HashSet<Passenger> passengers = new HashSet<Passenger>();
    
    public Flight()
    {
      
    }
    
    public Flight(String start, String destination)
    {
      this.start=start;
      this.destination=destination;
    }
    
    public String getStart()
    {
      return start;
    }
    public String getDestination()
    {
      return destination;
    }
    
    public void addPassenger(Passenger name)
    {
      passengers.add(name);
    }
    
    public void removePassenger(Passenger name)
    {
      passengers.remove(name);
    }
    
    public String toString()
    { 
      String str = "";
      for( Passenger x:passengers)
      {
        str=str+x+"\n";
      }
      return "Flight: " + start + "-" + destination +"\n" + str;
    }
    
    public void replacePassenger(Passenger passenger1, Passenger passenger2)
    {
      if(passengers.contains(passenger1) && (! passengers.contains(passenger2)) && passenger2.getFlightInfo()==null)
      {
        addPassenger(passenger2);
        removePassenger(passenger1);
      }
    }
}