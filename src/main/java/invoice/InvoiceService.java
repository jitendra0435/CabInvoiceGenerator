package invoice;

public class InvoiceService {

    private static int COST_PER_TIME;
    private static double MIN_COST_PER_KILOMETER ;
    private static double MINIMUM_FARE;
    RideCategories categories=null;
    RideRepository rideRepository;

    enum RideCategories{
        NORMAL_RIDES,PREMIUM_RIDES
    }

    public InvoiceService(RideCategories categories) {
        this.rideRepository=new RideRepository();
        if(categories.equals(RideCategories.NORMAL_RIDES)){
            COST_PER_TIME=1;
            MIN_COST_PER_KILOMETER=10;
            MINIMUM_FARE=5;
        }
        else  if(categories.equals(RideCategories.PREMIUM_RIDES)){
            COST_PER_TIME=2;
            MIN_COST_PER_KILOMETER = 15;
            MINIMUM_FARE=20;
        }
    }

    public double calculateFair(double distance, int time) {
         double totalFare = distance*MIN_COST_PER_KILOMETER + time*COST_PER_TIME;
          return Math.max(totalFare,MINIMUM_FARE);
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
