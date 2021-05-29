package myProject;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Class to represent repo where User objects will be stored
 */
@Repository
public interface UserRepo extends JpaRepository<User2, Integer> {

    @Query(value = "SELECT * FROM user2 Where username = ?1", nativeQuery = true)
        public List<User2> getByUsername(String username);
}
