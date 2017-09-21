package metodos;


import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class Cliente {
	
	public static Client getClient(){
		ClientConfig clientConfig = new ClientConfig();
		
		Client client = ClientBuilder.newClient(clientConfig);
		client.register(GsonMessageBodyHandler.class);
		return client;
		
		
	}
	
	public static Client getClientSSL(String user, String password){
		
		
		
		ClientConfig clientConfig = new ClientConfig();
		
		Client client = ClientBuilder.newClient(clientConfig);
		client.register(GsonMessageBodyHandler.class);
		client.register(HttpAuthenticationFeature.basic(user, password));
		return client;
		
		
	}
	
	
	

}
