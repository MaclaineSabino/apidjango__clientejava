package componentes;

import java.util.List;

import entidades.Operacao;

public class OperacaoPaginacao extends Paginacao {
	private Paginacao paginacao;
	private List<Operacao> listaOperacoes;

	public List<Operacao> getListaOperacoes() {
		return listaOperacoes;
	}

	public void setListaOperacoes(List<Operacao> listaOperacoes) {
		this.listaOperacoes = listaOperacoes;
	}

	public Paginacao getPaginacao() {
		return paginacao;
	}

	public void setPaginacao(Paginacao paginacao) {
		this.paginacao = paginacao;
	}

}
