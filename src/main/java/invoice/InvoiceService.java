package invoice;

public class InvoiceService {

    private static final int COST_PER_TIME=1;
    private static final double MIN_COST_PER_KILOMETER = 10;
    private static final double MINIMUM_FARE = 5;

    private static final int COST_PER_TIME_FOR_PRIMIUM=2;
    private static final double MIN_COST_PER_KILOMETER_FOR_PRIMIUM = 15;
    private static final double PRIMIUM_FARE=20;
    RideCategories categories=null;
    RideRepository rideRepository=null;

    enum RideCategories{
        NORMAL_RIDES,PREMIUM_RIDES
    }

    public InvoiceService(RideCategories categories) {
        this.categories=categories;
    }

    public double calculateFair(double distance, int time) {
        double totalFare=0.0;
      if(this.categories.equals(RideCategories.NORMAL_RIDES)){
          totalFare=distance*MIN_COST_PER_KILOMETER + time*COST_PER_TIME;
          return totalFare;
      }
       else
           totalFare = distance * MIN_COST_PER_KILOMETER_FOR_PRIMIUM + time * COST_PER_TIME_FOR_PRIMIUM;
       return  totalFare;

    }

    public InvoiceService()
    {
        this.rideRepository=new RideRepository();
    }

    public InvoiceSummary calculateFare(Ride[] rides)
    {
        double totalFare=0.0;
        for(Ride ride:rides)
            totalFare += calculateFair(ride.distance, ride.time);
      return new InvoiceSummary(rides.length,totalFare);
    }

    public InvoiceSummary getInvoiceSummary(String userid) {

        return this.calculateFare(rideRepository.getRide(userid));
    }

    public void addRides(String userid, Ride[] rides)
    {
        rideRepository.addRides(userid,rides);
    }

}
