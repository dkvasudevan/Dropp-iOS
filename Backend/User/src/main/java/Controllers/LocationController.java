package myProject;
import java.util.UUID;
import java.util.List;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "LocationController", description = "REST APIs related to Location Entity")
@RestController
/**
 * Controller class for location objects
 */
public class LocationController {
    /**
     * Instance of repo class for location
     */
    @Autowired
    LocationRepo location_db;

    /**
     * Instance of repo class for user
     */
    @Autowired
    UserRepo user_db;

    /**
     * Method to get location by id
     * @param id of loccation object
     * @return location with primary id
     */
    @ApiOperation(value = "Get location by location id", response = Iterable.class, tags = "getLocationById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/location/{id}")
    Location getLocation(@PathVariable Integer id) {
        return location_db.findOne(id);
    }

    /**
     * Get location by id of user
     * @param userId of user with location
     * @return location that is owned by user with id
     */
    @ApiOperation(value = "Get location by user id", response = Iterable.class, tags = "getLocationByUserId")
    @GetMapping("/location/userid/{userId}")
    List<Location> getLocationByUserId(@PathVariable Integer userId) {return location_db.getByUserId(userId);}

    /**
     * Get all location in location table
     * @return list of locations
     */
    @ApiOperation(value = "Get list of all locations", response = Iterable.class, tags = "getAllLocations")
    @GetMapping("/location/all")
    List<Location> getAllLocations() {
        return location_db.findAll();
    }

    /**
     * Get Location by username
     * @return
     */
    @ApiOperation(value = "Get location by username", response = Iterable.class, tags = "getLocationByUsername")
    @GetMapping("/location/username/{username}")
    List<Location> getLocationByUsername(@PathVariable String username) {
        return location_db.getByUsername(username);
    }

    /**
     * Get all location nearby a specific longitude and latitude of a requesting user
     * Validate that age follows proper protocal
     * @param latitude of requesting user
     * @param longitude of requesting user
     * @param age of requesting user
     * @return List of nearby locations
     */
    @ApiOperation(value = "Get nearby locations of a given latitude and longitude", response = Iterable.class,
            tags = "getNearbyLocations")
    @GetMapping("location/{latitude}/{longitude}/{age}")
    List<Location> getNearbyLocations(@PathVariable Double latitude, @PathVariable Double longitude,
                                      @PathVariable Integer age){

        List<Location> nearby = new ArrayList<Location>();

        List<Location> all = getAllLocations();
        Double dist = 50.0;

        List<Double> debug = new ArrayList<Double>();

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        List<User2> user_list = new ArrayList<User2>();
        String bday = "0/0/0";
        for(Location loc: all){
            Double lat = loc.getLatitude();
            Double lon = loc.getLongitude();
            user_list = user_db.getByUsername(loc.getUsername());
            if(user_list.size() > 0) {
                User2 user = user_list.get(0);
                bday = user.getBirthday();
            }

//            debug.add(lat);
//            debug.add(lon);
//            debug.add(distance(latitude,longitude,lat,lon));
            if(distance(latitude, longitude, lat, lon) <= dist && ageValid(bday, age, day, month, year)){
                nearby.add(loc);
            }

        }

        return nearby;


    }

    /**
     * Method that checks if user is of valid age
     * Users of 18 or older can only see other users of 18 or older
     * Users under 18 can only see other users under 18
     * @param bday
     * @param age
     * @param day
     * @param month
     * @param year
     * @return boolean of whether the user is of valid age or not
     */
    public static boolean ageValid( String bday, Integer age, int day, int month, int year){


        String[] arr = bday.split("/");
        int day1 = Integer.parseInt(arr[0]);
        int month1 = Integer.parseInt(arr[1]);
        int year1 = Integer.parseInt(arr[2]);

        int curr_age = 0;
        int year_diff = year - year1;
        if (month > month1){
            curr_age = year_diff;
        }
        else if(month < month1){
            curr_age = year_diff - 1;
        }
        else{
            if(day >= day1){
                curr_age = year_diff;
            }
            else{
                curr_age = year_diff -1;
            }
        }

        if (age >= 18 && curr_age >= 18){
            return true;
        }
        else if(age < 18 && curr_age < 18){
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Finds the distance between two points of latitude and longitude
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return distance in miles to the nearest mile
     */
    public static int distance(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) +
                    Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            return ((int)dist);
        }
    }

    /**
     * Posts location to the database
     * @param location
     * @param user_location_id
     * @return user that was created
     */
    @ApiOperation(value = "Post location to the user that posted it", response = Iterable.class, tags = "createLocation")
    @PostMapping("/location/{user_location_id}")
    Location createLocation(@RequestBody Location location, @PathVariable int user_location_id) {
//		UUID SocialId = UUID.randomUUID()
//		p.setSocial_id(SocialId)
        User2 curr_user = user_db.findOne(user_location_id);
        location.setUser_location( curr_user);
        location.setUsername(curr_user.getUsername());
        System.out.println(location);
        location_db.save(location);

        return location;
    }



//    @PutMapping("/location/{location_id}")
//    Location updateLocation(@RequestBody Location location, @PathVariable Integer location_id) {
//        Location old_location = location_db.findOne(location_id);
//        old_location.setLongitude(location.longitude);
//        old_location.setLatitude(location.latitude);
//        location_db.save(old_location);
//        return old_location;
//    }

    /**
     * delete location by id
     * @param id
     * @return string with user id that was deleted
     */
    @ApiOperation(value = "Delete Location by id", response = Iterable.class, tags = "deleteLocationById")
    @DeleteMapping("/location/{id}")
    String deleteLocation(@PathVariable Integer id) {
        location_db.delete(id);
        return "deleted " + id;
    }

    @ApiOperation(value = "Delete Location by username", response = Iterable.class, tags = "deleteLocationByUser")
    @DeleteMapping("/location/username/{username}")
    String deleteLocation(@PathVariable String username) {
        List<Location> loc = location_db.getByUsername(username);
        for (int i = 0; i < loc.size(); i++){
            Location curr = loc.get(i);
            int id = curr.getId();
            location_db.delete(id);
        }
        return "deleted " + username;
    }


}
