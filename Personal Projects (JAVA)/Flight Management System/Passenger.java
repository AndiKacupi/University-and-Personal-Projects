class Passenger {

      private int passportNumber;
      private Flight flight;
      
      public Passenger(int passportNumber)
      {
        this.passportNumber=passportNumber;
      }
      
      public Flight getFlightInfo()
      {
        return flight;
      }
      
      public void board(Flight flight)
      {
        if(this.flight == null)
        {
          this.flight=flight;
          flight.addPassenger(this);
        }
      }
      
      public void disembark()
      {
        if(this.flight!=null)
        {
          flight.removePassenger(this);
          flight=null;
        }
      }
      
      public String toString()
      {
        return "Passenger " + passportNumber;
      }
      
      public void connect(Flight f)
      {
        if(flight!=null && flight.getDestination().equals(f.getStart()) )
        {
          disembark();
          board(f);
        }
      }
}