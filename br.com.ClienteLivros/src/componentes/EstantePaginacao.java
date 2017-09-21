package componentes;

import java.util.List;

import entidades.Estante;

public class EstantePaginacao extends Paginacao {
	private Paginacao paginacao;
	
	private List<Estante> listaEstantes;

	public List<Estante> getListaEstantes() {
		return listaEstantes;
	}

	public void setListaEstantes(List<Estante> listaEstantes) {
		this.listaEstantes = listaEstantes;
	}

	public Paginacao getPaginacao() {
		return paginacao;
	}

	public void setPaginacao(Paginacao paginacao) {
		this.paginacao = paginacao;
	}

}
