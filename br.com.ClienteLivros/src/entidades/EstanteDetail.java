package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class EstanteDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pk;
	@Column(nullable = true, length = 50)
	
	private LivroDetail livro;
	@Column(nullable = true, length = 50)
	private  String modalidade_negocio;
	@Column(nullable = true, length = 50)
	private  String preco;
	@Column(nullable = true, length = 50)
	private  String codigo;
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public LivroDetail getLivro() {
		return livro;
	}
	public void setLivro(LivroDetail livro) {
		this.livro = livro;
	}
	public String getModalidade_negocio() {
		return modalidade_negocio;
	}
	public void setModalidade_negocio(String modalidade_negocio) {
		this.modalidade_negocio = modalidade_negocio;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@Override
	public String toString() {
		return "Estante [pk=" + pk +", livro= "+livro+", modalidade_negocio= "+modalidade_negocio+", preço=" + preco + ", código= " + codigo+
		
		 "]";
	}

	
	

}
