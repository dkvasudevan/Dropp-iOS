package myProject;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(value = "UserController", description = "REST APIs related to User Entity")
@RestController
public class UserController {
	/**
	 * Instance of repo class for the user
	 */
	@Autowired
	UserRepo db;


	/**
	 * Get user by user id
	 * @param id
	 * @return the user object if found by the given id
	 */
	@ApiOperation(value = "Get User by userId", response = Iterable.class, tags = "getUser")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/user/{id}")
	User2 getUser(@PathVariable int id) {
		return db.findOne(id);
	}

	/**
	 * get user by the users username
	 * @param username of user
	 * @return User that has the username
	 */
	@ApiOperation(value = "Get User by username", response = Iterable.class, tags = "getUserByUserUsername")
	@GetMapping("/user/username/{username}")
	List<User2> getUserByUsername(@PathVariable String username){
		List<User2> user = db.getByUsername(username);
		return user;
	}

	/**
	 * Get all locations for a specific user
	 * @param id
	 * @return list of locations
	 */
	@ApiOperation(value = "Get list of all locations posted by the userId", response = Iterable.class, tags = "getLocationsByUserId")
	@GetMapping("/user/locations/{id}")
	List<Location> getLocationsByUserId(@PathVariable int id){
		User2 curr_user = db.findOne(id);
		return curr_user.getLocations();
	}

	/**
	 * Get all users in the user table
	 * @return all users
	 */
	@ApiOperation(value = "Get list of all users", response = Iterable.class, tags = "getAllUsers")
	@GetMapping("/user/all")
	List<User2> getAllUsers() {
		return db.findAll();
	}

	/**
	 * Creates a new user
	 * @param p
	 * @return user that was created
	 */
	@ApiOperation(value = "Added the new user to the database of existing users", response = Iterable.class, tags = "createUser")
	@PostMapping("/user")
	User2 createUser(@RequestBody User2 p) {

		db.save(p);
		return p;
	}

	@ApiOperation(value = "update user by id",
			response = Iterable.class, tags = "updateUserById")
	@PutMapping("/user/{id}")
	User2 updateUser(@RequestBody User2 p, @PathVariable int id) {
		User2 old_p = db.findOne(id);
		old_p.setId(p.getId());
		old_p.setFirst_name(p.getFirst_name());
		old_p.setLast_name(p.getLast_name());
		old_p.setUsername(p.getUsername());
		old_p.setPassword(p.getPassword());
		old_p.setEmail(p.getEmail());
		old_p.setBirthday(p.getBirthday());
		old_p.setBio(p.getBio());
		old_p.setLocations(p.getLocations());
		old_p.setSnapchat(p.getSnapchat());
		old_p.setInstagram(p.getInstagram());
		old_p.setTiktok(p.getTiktok());
		old_p.setFacebook(p.getFacebook());
		old_p.setLinkedin(p.getLinkedin());
		old_p.setTwitter(p.getTwitter());
		old_p.setPhoto(p.getPhoto());

		//old_p.setAddress(p.address);
		db.save(old_p);
		return old_p;
	}

	/**
	 * deletes specified user by id
	 * @param id
	 * @return string with id of user that was deleted
	 */
	@ApiOperation(value = "Delete user by userId", response = Iterable.class, tags = "deleteUser")
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable int id) {
		db.delete(id);
		return "deleted " + id;
	}


}
