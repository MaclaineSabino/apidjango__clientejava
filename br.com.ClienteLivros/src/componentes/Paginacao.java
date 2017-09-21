package componentes;

import java.util.List;

import entidades.Livro;

public class Paginacao {
	
	private String avancar;
	private String anterior;
	private String atual;
	public String getAvancar() {
		return avancar;
	}
	public void setAvancar(String avancar) {
		this.avancar = avancar;
	}
	public String getAnterior() {
		return anterior;
	}
	public void setAnterior(String anterior) {
		this.anterior = anterior;
	}
	public String getAtual() {
		return atual;
	}
	public void setAtual(String atual) {
		this.atual = atual;
	}

}
