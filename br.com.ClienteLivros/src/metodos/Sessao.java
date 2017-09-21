package metodos;

import java.util.List;

import javax.swing.JOptionPane;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import componentes.Paginacao;
import entidades.Livro;
import entidades.User;

public class Sessao {
	private String user;
	private String senha;
	
	private String limpJson(String json){
		String saida;
		
		int colchete1 = json.indexOf('[');
		int colchete2 = json.indexOf(']');
		
		System.out.println(json);
		
		if (json.contains("[")){
		
		saida = json.substring(colchete1, colchete2+1);
		}else{
			saida = null;
		}
		
				
		return saida;
		
	}
	
private String getListaEntidadeJson(String url,String user,String senha){
		
		Client client = Cliente.getClientSSL(user,senha);
		
		
		
		WebTarget target = client.target(url).path("/");

		Response response = target.request(MediaType.APPLICATION_JSON).get();
		
		System.out.println(response.getStatus());
	
		String l = response.readEntity(String.class);
		String json = limpJson(l);	
		

		return json;
		
		
		
	}

public User getUser(String usuario, String senha){
	User user=null;
	String userJson = getListaEntidadeJson(Urls.user,usuario,senha);
	
	if(userJson==null){
		JOptionPane.showMessageDialog(null, "código: 401 \nNão foi possível efetuar login, verifique senha e usuário");
	}else{
	
	Gson a = new Gson();
	
	List<User> listUser = a.fromJson(userJson,new TypeToken<List<User>>(){}.getType());
	
	
	
	user =listUser.get(0);
	}
	
	return user;
	
	
}

}
