package componentes;

import java.util.List;

import entidades.Autor;

public class AutorPaginacao extends Paginacao {
	private Paginacao paginacao;
	
	private List<Autor> listaAutores;

	public List<Autor> getListaAutores() {
		return listaAutores;
	}

	public void setListaAutores(List<Autor> listaAutores) {
		this.listaAutores = listaAutores;
	}

	public Paginacao getPaginacao() {
		return paginacao;
	}

	public void setPaginacao(Paginacao paginacao) {
		this.paginacao = paginacao;
	}

}
