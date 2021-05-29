package myProject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class DanTest   {

    @Test
    public void testAgeCheck()   {
        //Create an instance of the encryption and test it using
        LocationController l1 = new LocationController();

        int age = 20;
        String bday = "15/5/2000";
        int day = 29;
        int month = 10;
        int year = 2020;

        assertEquals(true, l1.ageValid(bday, age, day, month, year));


    }

    @Test
    public void testUnderAgeCheck()   {
        //Create an instance of the encryption and test it using
        LocationController l1 = new LocationController();

        int age = 20;
        String bday = "15/5/2008";
        int day = 29;
        int month = 10;
        int year = 2020;

        assertEquals(false, l1.ageValid(bday, age, day, month, year));


    }

    @Test
    public void testDesmoinesAmes(){
        LocationController l1 = new LocationController();

        double lat2 = 41.586834;
        double lon2 = -93.624962;
        double lat1 = 42.030781;
        double lon1 = -93.631912;

        assertEquals(30, l1.distance(lat1,lon1,lat2,lon2));
    }

    @Test
    public void testNewYorkAmes(){
        LocationController l1 = new LocationController();
        double lat2 = 40.712776;
        double lon2 = -74.005974;
        double lat1 = 42.030781;
        double lon1 = -93.631912;


        assertEquals(1019, l1.distance(lat1,lon1,lat2,lon2));


    }

    @Test
    public void testParisLille(){
        LocationController l1 = new LocationController();
        double lat1 = 48.856613;
        double lon1 = 2.352222;
        double lat2 = 50.629250;
        double lon2 = 3.057256;

        assertEquals(126, l1.distance(lat1, lon1, lat2, lon2));


    }

    @Test
    public void testParisSydney(){
        LocationController l1 = new LocationController();
        double lat1 = 48.856613;
        double lon1 = 2.352222;
        double lat2 = -33.868820;
        double lon2 = 151.209290;

        assertEquals(10538, l1.distance(lat1, lon1, lat2, lon2));


    }


}
