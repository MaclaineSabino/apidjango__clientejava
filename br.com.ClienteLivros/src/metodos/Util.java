package metodos;

import javax.swing.JOptionPane;

import componentes.LivroPaginacao;
import componentes.Paginacao;

public class Util {
	public Paginacao limpJson(String json){
		String[] split = json.split("\"");
		Paginacao saidalista = new Paginacao();
		saidalista.setAvancar(split[5]);
		saidalista.setAnterior(split[9]);
		System.out.println(saidalista.getAnterior());
		int colchete1 = json.indexOf('[');
		int colchete2 = json.indexOf(']');
		
		System.out.println(json);
		
		String saida = json.substring(colchete1, colchete2+1);
		saidalista.setAtual(saida);
				
		return saidalista;
		
	}
	
	
	public String pegaPk(String select){
		String saida = null;
		
		
		if(select.contains("[")){
			int colchete1 = select.indexOf("[");
			int colchete2 = select.indexOf("]");
		saida = select.substring(colchete1+1, colchete2);
		}
		return saida;
	}
	
	public String tratamentoErro(int status){
		String st = String.valueOf(status);
		switch(status){
		case 200:{
			
			
			return "código: " +st+"\nAção realizada com sucesso";
			
		}
		case 201:{
			
			return "código: " +st+"\ndados criados com sucesso";
			
		}
		case 204:{
			return "código: " +st+"\noperação feita com sucesso";
		}
		case 400:{
			
			return "código: " +st+"\ndados inválidos";
		}
		case 401:{
			return "código: "+st+"\nnão autorizado, efetue o login para puder realizar essa ação";
		}
		case 403:{
			return "código: "+st+"\nproblemas na autenticação";
		}
		case 500:{
			
			return "código: " +st+"\nerro do servidor";
		}
		
		default:{
			
			return "código: " +st+"\nocorreu um erro inespecificado";
		}
		
		
		
		
		}
		
		
		
	}
	
	public void mensagem(String texto){
		JOptionPane.showMessageDialog(null, texto);
	}

}
