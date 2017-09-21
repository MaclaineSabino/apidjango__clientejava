package componentes;

import java.util.List;

import entidades.Livro;

public class LivroPaginacao extends Paginacao {
private List<Livro> livros;
private Paginacao paginacao;

public List<Livro> getLivros() {
	return livros;
}

public void setLivros(List<Livro> livros) {
	this.livros = livros;
}

public Paginacao getPaginacao() {
	return paginacao;
}

public void setPaginacao(Paginacao paginacao) {
	this.paginacao = paginacao;
}
}
