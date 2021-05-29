package myProject;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.stereotype.Repository;


/**
 * Class to represent the repository where "dropp"/Locations object are stored
 */
@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {

    @Query(value = "SELECT * FROM location Where user_location_id = ?1", nativeQuery = true)
        public List<Location> getByUserId(int user_location_id);

    @Query(value = "SELECT * FROM Places WHERE acos(sin(lat) * sin(latitude) + cos(lat) * cos(latitude) * cos(longitude - (lon))) * 6371 <= 1;", nativeQuery = true)
        public List<Location> getNearbyLocations(Double lat, Double lon);

    @Query(value = "SELECT * FROM location Where username = ?1", nativeQuery = true)
        public List<Location> getByUsername(String username);

}
