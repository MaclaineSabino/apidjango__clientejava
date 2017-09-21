package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public  class Livro implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pk;
	@Column(nullable = true, length = 50)
	private  String url;
	@Column(nullable = true, length = 50)
	
	
	private  String titulo;
	@Column(nullable = true, length = 50)
	private  String categoria;
	@Column(nullable = true, length = 50)
	private  int ano_publicacao;
	@Column(nullable = true, length = 50)
	private  String estante;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	
	public String getEstante() {
		return estante;
	}
	public void setEstante(String estante) {
		this.estante = estante;
	}
	
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getAno_publicacao() {
		return ano_publicacao;
	}
	public void setAno_publicacao(int ano_publicacao) {
		this.ano_publicacao = ano_publicacao;
	}
	
	
	@Override
	public String toString() {
		return "Livro [pk=" + pk +", titulo=" + titulo + ", categoria= " + categoria+
				", ano de publicação: "+ano_publicacao+
				 "]";
	}
	
	
	

}
