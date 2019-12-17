package invoice;
import org.junit.Assert;
import org.junit.Test;

public class InvoiceserviceTest {

    @Test
    public void givenDistanceAndTime_forNormalRide_shouldRetrunTotalfare()
    {
        InvoiceService invoiceGenerator=new InvoiceService(InvoiceService.RideCategories.NORMAL_RIDES);
        double distance=2.0;
        int time=5;
        double result=invoiceGenerator.calculateFair(distance,time);
        Assert.assertEquals(25.0,result,0.0);
    }

    @Test
    public void givenDistanceAndTime_shouldReturnMinFare() {
        InvoiceService invoiceGenerator=new InvoiceService(InvoiceService.RideCategories.NORMAL_RIDES);
        double distance=0.1;
        int time=1;
        double result=invoiceGenerator.calculateFair(distance,time);
        Assert.assertEquals(5,result,0.0);

    }

    @Test
    public void givenMultipleRides_shouldReturnTotalFare() {
        InvoiceService invoiceGenerator=new InvoiceService(InvoiceService.RideCategories.NORMAL_RIDES);
        Ride[] rides={ new Ride(2.0,5),
                      new Ride(1.0,1)};
        InvoiceSummary summary=invoiceGenerator.calculateFare(rides);
        InvoiceSummary ExpectedinvoiceSummary=new InvoiceSummary(2,36.0);
        Assert.assertEquals(ExpectedinvoiceSummary,summary);
    }

    @Test
    public void givenUserIdRides_shouldReturnInvoiceSummary() {
        String userid="a@b.com";
        Ride[] rides={ new Ride(2.0,5),
                new Ride(1.0,1)};
        InvoiceService invoiceService=new InvoiceService(InvoiceService.RideCategories.NORMAL_RIDES);
        invoiceService.addRides(userid,rides);
        InvoiceSummary invoiceSummary=invoiceService.getInvoiceSummary(userid);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,36.0);
        Assert.assertEquals(expectedInvoiceSummary,invoiceSummary);

    }

    @Test
    public void givenDistanceAndTime_forPremiumUser_shouldRetrunTotalfare()
    {
        InvoiceService invoiceGenerator=new InvoiceService(InvoiceService.RideCategories.PREMIUM_RIDES);
        double distance=2.0;
        int time=5;
        double result=invoiceGenerator.calculateFair(distance,time);
        Assert.assertEquals(40.0,result,0.0);
    }

    @Test
    public void givenUserIdRides_forPrimiumUser_shouldReturnInvoiceSummary() {
        String userid="a@b.com";
        Ride[] rides={ new Ride(2.0,5),
                new Ride(1.0,1)};
        InvoiceService invoiceService=new InvoiceService(InvoiceService.RideCategories.PREMIUM_RIDES);
        invoiceService.addRides(userid,rides);
        InvoiceSummary invoiceSummary=invoiceService.getInvoiceSummary(userid);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,60.0);
        Assert.assertEquals(expectedInvoiceSummary,invoiceSummary);

    }
}
