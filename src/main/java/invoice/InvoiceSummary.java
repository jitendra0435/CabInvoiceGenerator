package invoice;

public class InvoiceSummary {
    public final int numOfRides;
    public final double totalFair;
    public final double average;

    public InvoiceSummary(int numOfrides, double totalFair) {
        this.numOfRides=numOfrides;
        this.totalFair=totalFair;
        this.average=this.numOfRides/this.totalFair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return numOfRides == that.numOfRides &&
                Double.compare(that.totalFair, totalFair) == 0 &&
                Double.compare(that.average, average) == 0;
    }

}
