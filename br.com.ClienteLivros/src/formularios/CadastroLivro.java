package formularios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import componentes.AutorPaginacao;
import entidades.Autor;
import metodos.DadosAPI;
import metodos.Urls;
import metodos.Util;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CadastroLivro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField titulo;
	private JTextField categoria;
	private JTextField ano_publicacao;
	private JTextField preco;
	FormPrincipal formprincipal;

	
	/**
	 * Create the dialog.
	 */
	public CadastroLivro(FormPrincipal formprinc) {
		formprincipal = formprinc;
		
		AutorPaginacao autores = new DadosAPI().getAutores(Urls.autores);
		String[] listaAut = lista(autores.getListaAutores());
		String[] modalidade = {"VENDA","TROCA"};
		setBounds(100, 100, 569, 361);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(31, 52, 52, 14);
		contentPanel.add(lblAutor);
		
		JComboBox autor = new JComboBox(listaAut);
		autor.setBounds(155, 49, 271, 20);
		contentPanel.add(autor);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setBounds(31, 93, 46, 14);
		contentPanel.add(lblTtulo);
		
		titulo = new JTextField();
		titulo.setBounds(155, 90, 271, 20);
		contentPanel.add(titulo);
		titulo.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(31, 132, 93, 14);
		contentPanel.add(lblCategoria);
		
		categoria = new JTextField();
		categoria.setBounds(155, 129, 271, 20);
		contentPanel.add(categoria);
		categoria.setColumns(10);
		
		JLabel lblAnoDePublicao = new JLabel("Ano de publica\u00E7\u00E3o");
		lblAnoDePublicao.setBounds(31, 167, 114, 14);
		contentPanel.add(lblAnoDePublicao);
		
		ano_publicacao = new JTextField();
		ano_publicacao.setBounds(155, 164, 271, 20);
		contentPanel.add(ano_publicacao);
		ano_publicacao.setColumns(10);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastrarAutor(formprincipal).setVisible(true);
				
				CadastroLivro.this.dispose();
				
				
			}
		});
		button.setBounds(431, 48, 52, 23);
		contentPanel.add(button);
		preco = new JTextField();
		preco.setBounds(155, 229, 271, 20);
		contentPanel.add(preco);
		preco.setColumns(10);
		
		JComboBox modali = new JComboBox(modalidade);
		modali.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (String.valueOf(modali.getSelectedItem()).equals("TROCA")){
					preco.setEnabled(false);
					preco.setText("0.0");
					
				}else{
					
					preco.setEnabled(true);
					
					
				}
				
			}
		});
		modali.setBounds(155, 198, 271, 20);
		contentPanel.add(modali);
		
		JLabel lblModalidade = new JLabel("Modalidade");
		lblModalidade.setBounds(31, 198, 114, 14);
		contentPanel.add(lblModalidade);
		
		
		
		JLabel lblPreo = new JLabel("Pre\u00E7o");
		lblPreo.setBounds(31, 232, 46, 14);
		contentPanel.add(lblPreo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Cadastrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					String autorU =String.valueOf(autor.getSelectedItem());
					String tituloU = titulo.getText();
					String categoriaU = categoria.getText();
					String anopublicacao = ano_publicacao.getText();
					String modalidade =String.valueOf(modali.getSelectedItem());
					String precoU = preco.getText();
					try{
					int retorno = new DadosAPI().cadastrarEstante(autorU, tituloU, categoriaU, anopublicacao, modalidade, precoU,Urls.estantes);
					if(retorno>=201 && retorno<=203){
						titulo.setText("");
						categoria.setText("");
						ano_publicacao.setText("");
						preco.setText("");
					}
						Util u = new Util();
						String mens = u.tratamentoErro(retorno);
						u.mensagem(mens);
						
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "código 403: \nvocê fez um logof, não é possível efetuar essa ação, faça o login novamente");
					}}
				});
				okButton.setActionCommand("Cadastrar");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CadastroLivro.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	private String[] lista(List<Autor> lista){
		int k=0;
	String [] autores = new String[lista.size()];
	 for(Autor autor:lista){
		 
		 System.out.println(autor.getAutor());
		 autores[k]=autor.getAutor();
		 k++;
		 
	 }
		
		
		return autores;
		
	}
}
