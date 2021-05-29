package myProject.WebSocketServer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * Check the main application file as well!
 * This class configures your endpoints with the rest of the
 * Spring Application. It redirects the "ws://" requests to your endpoint.
 *
 * @author Bruce Bitwayiki
 *
 */
@Configuration
public class serverWebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
