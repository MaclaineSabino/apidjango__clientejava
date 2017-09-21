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
public class Usuario implements Serializable {

	/**
	 * 
	  */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pk;
	@Column(nullable = true, length = 50)
	private  User user;
	@Column(nullable = true, length = 50)
	private  String nome;
	@Column(nullable = true, length = 50)
	private  String endereco;
	@Column(nullable = true, length = 50)
	private  String cidade;
	@Column(nullable = true, length = 50)
	private  String estado;
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Usuario [pk=" + pk +", user= "+user+ ", nome=" + nome+", endereco= "+endereco+
				", cidade= "+cidade+", Estado= "+estado+
				 "]";
	}
	
	
	

}
