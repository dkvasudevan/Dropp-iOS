package myProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * Application Class
 *
 */
@SpringBootApplication
@EnableWebSocket
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	

}
