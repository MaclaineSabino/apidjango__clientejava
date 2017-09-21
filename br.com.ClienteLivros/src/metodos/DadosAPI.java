package metodos;

import java.util.List;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import componentes.AutorPaginacao;
import componentes.EstantePaginacao;
import componentes.LivroPaginacao;
import componentes.OperacaoPaginacao;
import componentes.Paginacao;
import entidades.Autor;
import entidades.Estante;
import entidades.EstanteDetail;
import entidades.Livro;
import entidades.LivroDetail;
import entidades.Operacao;
import entidades.User;
import entidades.Usuario;

public class DadosAPI {
	
	private Paginacao getListaEntidadeJson(String url){
		
		Client client = Cliente.getClient();
		
		WebTarget target = client.target(url).path("/");

		Response response = target.request(MediaType.APPLICATION_JSON).get();
		System.out.println(response.getStatus());
		String l = response.readEntity(String.class);
		Paginacao json = new Util().limpJson(l);	
		return json;
		
		
		
	}
	
	
private Paginacao getListaEntidadeJson2(String url){
		
		Client client = Cliente.getClientSSL(LoginSenha.user,LoginSenha.senha);
		
		
		
		WebTarget target = client.target(url).path("/");

		Response response = target.request(MediaType.APPLICATION_JSON).get();
	
		String l = response.readEntity(String.class);
		Paginacao json = new Util().limpJson(l);	
		

		return json;
		
		
		
	}
	
	
	
	
	
	
	public LivroPaginacao getLivros(String url){
		LivroPaginacao listalivros = new LivroPaginacao();
		listalivros.setPaginacao(getListaEntidadeJson(url));
		
		 
		Gson a = new Gson();
		
		List<Livro> livros = a.fromJson(listalivros.getPaginacao().getAtual(),new TypeToken<List<Livro>>(){}.getType());
		
		listalivros.setLivros(livros);
		
		return listalivros;
		
		
	}
	
	public LivroPaginacao getMeusLivros(String url){
		LivroPaginacao listalivros = new LivroPaginacao();
		listalivros.setPaginacao(getListaEntidadeJson2(url));
		
		 
		Gson a = new Gson();
		
		List<Livro> livros = a.fromJson(listalivros.getPaginacao().getAtual(),new TypeToken<List<Livro>>(){}.getType());
		
		listalivros.setLivros(livros);
		
		return listalivros;
		
		
	}
	
	public LivroDetail getLivro(String url,String pk){
		LivroDetail livroDetail=null;
		if(pk !=null){
		Client client = Cliente.getClient();
		
		WebTarget target = client.target(url).path("/"+pk+"/");
		
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		
		livroDetail = response.readEntity(LivroDetail.class);
		}
		return livroDetail;
	}
	
	
	
	public int deletarLivro(String url,String pk){
		LivroDetail livroDetail=null;
		
		Client client = Cliente.getClient();
		
		WebTarget target = client.target(url).path("/"+pk+"/");
		
		Response response = target.request(MediaType.APPLICATION_JSON).delete();
		
		livroDetail = response.readEntity(LivroDetail.class);
		
		return response.getStatus();
	}
	
	public EstantePaginacao getEstantes(String url){
		EstantePaginacao listaEstantes = new EstantePaginacao();
		listaEstantes.setPaginacao(getListaEntidadeJson(url));
		

		Gson a = new Gson();
		
		List<Estante> estantes = a.fromJson(listaEstantes.getPaginacao().getAtual(),new TypeToken<List<Estante>>(){}.getType());
		listaEstantes.setListaEstantes(estantes);
		
		return listaEstantes;
		
	}
	
	
	public OperacaoPaginacao listarMinhasTransacoes(String url){
		OperacaoPaginacao transacoes = new OperacaoPaginacao();
		transacoes.setPaginacao(getListaEntidadeJson2(url));
		
		
		
		
		Gson a = new Gson();
		List<Operacao> operacoes = a.fromJson(transacoes.getPaginacao().getAtual(), new TypeToken<List<Operacao>>(){}.getType());
		
		transacoes.setListaOperacoes(operacoes);
		
		
		return transacoes;
		
		
	}
	
	
	
	
	public EstanteDetail getEstante(String url,String pk){
		EstanteDetail estanteDetail=null;
		if(pk !=null){
		Client client = Cliente.getClient();
		
		WebTarget target = client.target(url).path("/"+pk+"/");
		
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		
		estanteDetail = response.readEntity(EstanteDetail.class);
		}
		return estanteDetail;
	}
	
	public AutorPaginacao getAutores(String url){
		AutorPaginacao listaAutores = new AutorPaginacao();
		listaAutores.setPaginacao(getListaEntidadeJson(url));
		
		 
		Gson a = new Gson();
		
		List<Autor> autores = a.fromJson(listaAutores.getPaginacao().getAtual(),new TypeToken<List<Autor>>(){}.getType());
		
		listaAutores.setListaAutores(autores);
		
		return listaAutores;
		
	}
	
	public int cadastrarAutor(String url,String nome){
		
		Autor autor = new Autor();
		autor.setAutor(nome);
		
		
		
		
		int retorno = 0;
		
		Client client = Cliente.getClientSSL(LoginSenha.user,LoginSenha.senha);
		
		
		
		WebTarget target = client.target(url).path("/");
		
		Entity<Autor> entity = Entity.entity(autor, MediaType.APPLICATION_JSON);
		
		
		Response response = target.request(MediaType.APPLICATION_JSON).post(entity);
	
		retorno = response.getStatus();
		
		
		return retorno;
		
		
		
	}
	
	public int cadastrarEstante(String autor, String titulo, String categoria, String ano_publicacao,String modalidade_negocio, String preco,String url){
	    
		
		
		
		
		
		String json2 = "{\"livro\":{\"autor\":\""+autor+"\",\"titulo\":\""+titulo+"\",\"categoria\":\""+categoria+"\",\"ano_publicacao\":\""+ano_publicacao+"\"},\"modalidade_negocio\":\""+modalidade_negocio+"\",\"preco\":\""+preco+"\"}";
		
		//JsonArray jArray = new JsonParser().parse(json2).getAsJsonArray();
		
		
		
		
		
		
		Client client = Cliente.getClientSSL(LoginSenha.user,LoginSenha.senha);
		
		
		
		WebTarget target = client.target(url).path("/");
		
		Entity<String> entity = Entity.entity(json2, MediaType.APPLICATION_JSON);
		System.out.println(entity.toString());
		
		Response response = target.request(MediaType.APPLICATION_JSON).post(entity);
	
		int retorno = response.getStatus();
		
		return retorno;
	}
	
	
	public int efetuarTransacao(int pk,String url){
		
		
		String json2 = "{\"estante\":"+pk+"}";
		
		
	
		
		Client client = Cliente.getClient();
		client.register(HttpAuthenticationFeature.basic(LoginSenha.user,LoginSenha.senha));
		
		WebTarget target = client.target(url).path("/");
		Entity<String> entity = Entity.entity(json2, MediaType.APPLICATION_JSON);
		
		Response response = target.request(MediaType.APPLICATION_JSON).post(entity);
		System.out.println(entity.toString());
		
		
	    return response.getStatus();
		
		
		
		
		
		
		
		
	}
	
	
	
	
	public int cadastrarUsuario(String emailU,String usernameU,String senhaU,String nomeU,String enderecoU,String cidadeU,String estadoU,String url){
		User user = new User();
		user.setEmail(emailU);
		user.setUsername(usernameU);
		user.setPassword(senhaU);
		Usuario usuario = new Usuario();
		usuario.setUser(user);
		usuario.setNome(nomeU);
		usuario.setEndereco(enderecoU);
		usuario.setCidade(cidadeU);
		usuario.setEstado(estadoU);
		
		Client client = Cliente.getClient();
		
		WebTarget target = client.target(url).path("/");
		Entity<Usuario> entity = Entity.entity(usuario, MediaType.APPLICATION_JSON);
		
		Response response = target.request(MediaType.APPLICATION_JSON).post(entity);
		System.out.println(entity.toString());
		
		
	    return response.getStatus();
	}

}
