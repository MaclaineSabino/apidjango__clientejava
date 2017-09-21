package formularios;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import componentes.AutorPaginacao;
import componentes.EstantePaginacao;
import componentes.LivroPaginacao;
import componentes.OperacaoPaginacao;
import entidades.Autor;
import entidades.Estante;
import entidades.EstanteDetail;
import entidades.Livro;
import entidades.LivroDetail;
import entidades.Operacao;
import metodos.DadosAPI;
import metodos.LoginSenha;
import metodos.Urls;
import metodos.Util;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BoxLayout;

public class FormPrincipal extends JFrame {

	private JPanel contentPane;
	JMenuItem loginItem;
	JMenuItem logofItem;
	
	JList list;
	JList list2;
	JList list3;
	JList list4;
	JList list5;
	JButton maisLista;
	JButton anterior;
	JScrollPane jscrool;
	JPasswordField password;
	JTextField username;
	Login login;
	
	private JLabel labelUsuario;
	
	CadastrarUsuario cadastrarUsuario;
	Image im = new ImageIcon ( getClass ( ).getResource ( "/imagens/livro.png" ) ).getImage ( );

	
	private DefaultListModel model;

	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPrincipal frame = new FormPrincipal();
					//frame.setUndecorated(true);
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					
				    
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormPrincipal() {
		
		labelUsuario = new JLabel("Usuário: Não logado");
		labelUsuario.setBounds(261, 11, 135, 14);;
		
		
		
		maisLista = new JButton(new javax.swing.ImageIcon(getClass().getResource("/imagens/direita.png")));
		maisLista.setBounds(386, 320, 89, 26);
		
		
		anterior = new JButton(new javax.swing.ImageIcon(getClass().getResource("/imagens/esquerda.png")));
		anterior.setBounds(212, 320, 89, 26);
		jscrool = new JScrollPane();
		jscrool.setBounds(84, 40, 500, 269);
		list = new JList();
		list.setBounds(84, 40, 500, 269);
		list2 = new JList();
		list2.setBounds(84, 40, 500, 269);
		list3 = new JList();
		list3.setBounds(84, 40, 500, 269);
		list4 = new JList();
		list4.setBounds(84, 40, 500, 269);
		list5 = new JList();
		list5.setBounds(84, 40, 500, 269);
		username =new JTextField(10);
		username.setSize(200, 100);
		username.setLocation(10, 5);
		password = new JPasswordField(10);
		password.setLocation(10, 20);
		
		model = new DefaultListModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 417);
		
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Usuário");
		loginItem = new JMenuItem("Login");
		logofItem = new JMenuItem("Logof");
		JMenuItem perfilItem = new JMenuItem("Cadastrar");
		menu.add(loginItem);
		menu.add(logofItem);
		menu.add(perfilItem);
		JMenu menu2 = new JMenu("Livros");
		JMenuItem autoresItem = new JMenuItem("Autores");
		JMenuItem cadastrarLivros = new JMenuItem("Cadastrar");
		JMenuItem listaLivros = new JMenuItem("Livros cadastrados");
		JMenuItem meusLivros = new JMenuItem("Meus livros");
		menu2.add(autoresItem);
		menu2.add(cadastrarLivros);
		menu2.add(listaLivros);
		menu2.add(meusLivros);
		JMenu menu3 = new JMenu("Operações");
		JMenuItem estantes = new JMenuItem("Estantes");
		JMenuItem transacoes = new JMenuItem("Minhas transações");
		
		
		menu3.add(estantes);
		menu3.add(transacoes);
		
		
		
		
		
		menuBar.add(menu);
		menuBar.add(menu2);
		menuBar.add(menu3);
		logofItem.setEnabled(false);
		contentPane = new JPanel();
		
		meusLivros.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paginacaoMeusLivros(Urls.meuslivros);
				
			}
		});
		
		transacoes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				paginacaoOperacao(Urls.transacoes);
			}
		});
		
		logofItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				logof();
				
				
			}
		});

		loginItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login = new Login(FormPrincipal.this);
				login.setVisible(true);
				
			}
		});
		perfilItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cadastrarUsuario = new CadastrarUsuario();
				cadastrarUsuario.setVisible(true);
				
			}
		});
		
		
		
		autoresItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				paginacaoAutores(Urls.autores);
				
			}
		});
		
		cadastrarLivros.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cadastrarLivro(FormPrincipal.this);
				
			}
		});
		
		listaLivros.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			paginacaoLivros(Urls.livros);
				
			}
		});
		
		
		estantes.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				paginacaoEstante(Urls.estantes);
				
			}
			
			
		});
		
		setJMenuBar(menuBar);
		
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		
		
		contentPane.add(labelUsuario);
		
		
		
	
		clicandoItem(list);
		clicandoItemEstante(list2);
		clicandoItemMeuLivro(list5);
		
	}
	
	private void clicandoItem(JList lista){
		
		lista.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				String pk = new Util().pegaPk(String.valueOf(lista.getSelectedValue()));
				System.out.println(pk);
				dadosLivro(pk);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
	}
	

private void clicandoItemMeuLivro(JList lista){
		
		lista.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				String pk = new Util().pegaPk(String.valueOf(lista.getSelectedValue()));
				
				dadosLivro2(pk);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
	}
	

	
private void clicandoItemEstante(JList lista){
		
		lista.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				String pk = new Util().pegaPk(String.valueOf(lista.getSelectedValue()));
			
				dadosEstante(pk);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
	}

	
	
	
	public void dadosLivro(String pk){
		LivroDetail livro = new DadosAPI().getLivro("http://127.0.0.1:8000/livro/", pk);
		
		String dadosLivroLabel ="Detalhes do produto";
		String livro_label = "Livro";
		String titulo = livro.getTitulo();
		String autor = livro.getAutor().getAutor();
		String categoria =livro.getCategoria();
		int ano_publicacao = livro.getAno_publicacao();
		
		String dadosUsuarioLabel = "Usuário responsável";
		String nome = livro.getUsuario().getNome();
		String email = livro.getUsuario().getUser().getEmail();
		String cidade = livro.getUsuario().getCidade();
		String estado = livro.getUsuario().getEstado();
		
		String saida = "<html><div style='font-size:20px;'>"+dadosLivroLabel+"</div><br>"
				+ "<div style='font-size:15px;color:blue'>"+livro_label+"</div><br><br>"
						+ "<div style='font-size:12px;'>Titulo: "+titulo+"</div><br>"
								+ "<div style='font-size:12px;'>Autor: "+autor+"</div><br>"
										+ "<div style='font-size:12px;'>Categoria: "+categoria+"</div><br>"
												+ "<div style='font-size:12px;'>Ano de publicação: "+ano_publicacao+"</div><br><br>"
														+ "<div style='font-size:15px; color:blue'>"+dadosUsuarioLabel+"</div><br><br>"
																+ "<div style='font-size:12px;'>Nome: "+nome+"</div><br>"
																		+ "<div style='font-size:12px;'>Email: "+email+"</div><br>"
																				+ "<div style='font-size:12px;'>Cidade: "+cidade+"</div><br>"
																						+ "<div style='font-size:12px;'>Estado: "+estado+"</div><br></html>";
		
		
		
		
		JOptionPane.showMessageDialog(null,saida);
		
		
		
	}
	
	public void dadosLivro2(String pk){
		LivroDetail livro = new DadosAPI().getLivro("http://127.0.0.1:8000/livro/", pk);
		
		String dadosLivroLabel ="Detalhes do produto";
		String livro_label = "Livro";
		String titulo = livro.getTitulo();
		String autor = livro.getAutor().getAutor();
		String categoria =livro.getCategoria();
		int ano_publicacao = livro.getAno_publicacao();
		
		String dadosUsuarioLabel = "Usuário responsável";
		String nome = livro.getUsuario().getNome();
		String email = livro.getUsuario().getUser().getEmail();
		String cidade = livro.getUsuario().getCidade();
		String estado = livro.getUsuario().getEstado();
		
		String saida = "<html><div style='font-size:20px;'>"+dadosLivroLabel+"</div><br>"
				+ "<div style='font-size:15px;color:blue'>"+livro_label+"</div><br><br>"
						+ "<div style='font-size:12px;'>Titulo: "+titulo+"</div><br>"
								+ "<div style='font-size:12px;'>Autor: "+autor+"</div><br>"
										+ "<div style='font-size:12px;'>Categoria: "+categoria+"</div><br>"
												+ "<div style='font-size:12px;'>Ano de publicação: "+ano_publicacao+"</div><br><br>"
														+ "<div style='font-size:15px; color:blue'>"+dadosUsuarioLabel+"</div><br><br>"
																+ "<div style='font-size:12px;'>Nome: "+nome+"</div><br>"
																		+ "<div style='font-size:12px;'>Email: "+email+"</div><br>"
																				+ "<div style='font-size:12px;'>Cidade: "+cidade+"</div><br>"
																						+ "<div style='font-size:12px;'>Estado: "+estado+"</div><br></html>";
		
		
		
		
		
		int op = JOptionPane.showOptionDialog(null, 
			    saida, 
			    "Detalhes", 
			    JOptionPane.OK_CANCEL_OPTION, 
			    JOptionPane.PLAIN_MESSAGE, 
			    null, 
			    new String[]{"Excluir livro", "Cancelar"}, // this is the array
			    "default");
		if(op == JOptionPane.OK_OPTION){
			int resposta = new DadosAPI().deletarLivro(Urls.livros, pk);
			Util u = new Util();
			String mensa =u.tratamentoErro(resposta);
			u.mensagem(mensa);
			list5.repaint();
			
		}
		
		
		
	}
	
	
	
	
	
	private void dadosEstante(String pk){

		EstanteDetail estante = new DadosAPI().getEstante("http://127.0.0.1:8000/estante/", pk);
		
		String dadosEstanteLabel ="Estante";
		String livro_label = "Livro";
		String titulo = estante.getLivro().getTitulo();
		String autor = estante.getLivro().getAutor().getAutor();
		String categoria =estante.getLivro().getCategoria();
		int ano_publicacao = estante.getLivro().getAno_publicacao();
		String modalidade = estante.getModalidade_negocio();
		String preco = estante.getPreco();
		
		
		
		String dadosUsuarioLabel = "Usuário responsável";
		String nome = estante.getLivro().getUsuario().getNome();
		String email = estante.getLivro().getUsuario().getUser().getEmail();
		String cidade = estante.getLivro().getUsuario().getCidade();
		String estado = estante.getLivro().getUsuario().getEstado();
		
		String saida = "<html><div style='font-size:20px;'>"+dadosEstanteLabel+"</div><br>"
				+ "<div style='font-size:15px;color:blue'>"+livro_label+"</div><br><br>"
						+ "<div style='font-size:12px;'>Titulo: "+titulo+"</div><br>"
								+ "<div style='font-size:12px;'>Autor: "+autor+"</div><br>"
										+ "<div style='font-size:12px;'>Categoria: "+categoria+"</div><br>"
												+ "<div style='font-size:12px;'>Ano de publicação: "+ano_publicacao+"</div><br>"
														+ "<div style='font-size:12px;'>Modalidade: "+modalidade+"</div><br>"
																+ "<div style='font-size:12px;'>Preço: "+preco+"</div><br><br>"
														+ "<div style='font-size:15px; color:blue'>"+dadosUsuarioLabel+"</div><br><br>"
																+ "<div style='font-size:12px;'>Nome: "+nome+"</div><br>"
																		+ "<div style='font-size:12px;'>Email: "+email+"</div><br>"
																				+ "<div style='font-size:12px;'>Cidade: "+cidade+"</div><br>"
																						+ "<div style='font-size:12px;'>Estado: "+estado+"</div><br></html>";
		
		
		
		
		
		int op = JOptionPane.showOptionDialog(null, 
			    saida, 
			    "Detalhes", 
			    JOptionPane.OK_CANCEL_OPTION, 
			    JOptionPane.PLAIN_MESSAGE, 
			    null, 
			    new String[]{"Efetuar transação", "Agora não"}, // this is the array
			    "default");
		if(op == JOptionPane.OK_OPTION){
			int resposta = new DadosAPI().efetuarTransacao(estante.getPk(), "http://127.0.0.1:8000/operacao/");
			Util u = new Util();
			String mensa =u.tratamentoErro(resposta);
			u.mensagem(mensa);
			
		}
		
		
		
	}
	
	
	private DefaultListModel limparLista(DefaultListModel lista){
		
		if (lista!=null){
	      //lista.removeAllElements();
	      lista.clear();
		}
		return lista;
		
	
	}
	
	private void paginacaoLivros(String url){
	
		LivroPaginacao livros =	new DadosAPI().getLivros(url);
		String posterior = livros.getPaginacao().getAvancar();
		String regredir = livros.getPaginacao().getAnterior();
		limparLista(model);
		
		for(Livro obj :livros.getLivros()){
			
			
			
			
			
			model.addElement("<HTML><div><div style='color:blue; font-size:15px'>"+obj.getTitulo()+"<BR></div>"+"<HTML>Código: ["+obj.getPk()+"]<br>categoria: "+obj.getCategoria()+"<BR>"+"<HTML>ano de publicação: "+obj.getAno_publicacao()+"<BR><BR><BR></div><HTML>");
			
			
		}
			
			
			
			
			list.setModel(model);
			list.setVisible(true);
			
			
			jscrool.setViewportView(list);
			
			//contentPane.add(list, BorderLayout.CENTER);
			
			
			
			 
			
			contentPane.add(jscrool);
			contentPane.add(anterior);
			contentPane.add(maisLista);
			
			FormPrincipal.this.repaint();
			
			maisLista.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(livros.getPaginacao().getAvancar().contains("http:")){
					paginacaoLivros(posterior);
					}else{
						JOptionPane.showMessageDialog(null,"não há mais livros para serem exibidos");
					}
					
				}
			});
			
			anterior.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					paginacaoLivros(regredir);
					
				}
			});
			
			setVisible(true);
			// TODO Auto-generated method stub
	}
	
	
	
	
	private void paginacaoMeusLivros(String url){
		
		try{
		
		LivroPaginacao livros =	new DadosAPI().getMeusLivros(url);
		String posterior = livros.getPaginacao().getAvancar();
		String regredir = livros.getPaginacao().getAnterior();
		limparLista(model);
		
		for(Livro obj :livros.getLivros()){
			
			
			
			
			
			model.addElement("<HTML><div><div style='color:blue; font-size:15px'>"+obj.getTitulo()+"<BR></div>"+"<HTML>Código: ["+obj.getPk()+"]<br>categoria: "+obj.getCategoria()+"<BR>"+"<HTML>ano de publicação: "+obj.getAno_publicacao()+"<BR><BR><BR></div><HTML>");
			
			
		}
			
			
			
			
			list5.setModel(model);
			list5.setVisible(true);
			
			
			jscrool.setViewportView(list5);
			
			//contentPane.add(list, BorderLayout.CENTER);
			
			
			contentPane.add(anterior);
			contentPane.add(maisLista); 
			
			contentPane.add(jscrool);
			FormPrincipal.this.repaint();
			
			maisLista.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(livros.getPaginacao().getAvancar().contains("http:")){
					paginacaoLivros(posterior);
					}else{
						JOptionPane.showMessageDialog(null,"não há mais livros para serem exibidos");
					}
					
				}
			});
			
			anterior.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					paginacaoLivros(regredir);
					
				}
			});
		}catch(Exception ex){
			ex.printStackTrace();
			limparLista();
			
			
			
		}
			
			setVisible(true);
			// TODO Auto-generated method stub
	}
	
	
	
	
	
	
	
	private void paginacaoEstante(String url){
		
		EstantePaginacao estante =	new DadosAPI().getEstantes(url);
		limparLista(model);
		
		for(Estante obj :estante.getListaEstantes()){
			
			
			
			
			
			model.addElement("<HTML><div><div style='color:blue; font-size:15px'>"+obj.getLivro().getTitulo()+"<BR></div>"+"<HTML>Código: ["+obj.getPk()+"]<br>modalidade: "+obj.getModalidade_negocio()+"<BR>"+"<HTML>preço: "+obj.getPreco()+"<BR><BR><BR></div><HTML>");
			
			
		}
			
			 
			
			
			
			list2.setModel(model);
			list2.setVisible(true);
			
			
			jscrool.setViewportView(list2);
			
			contentPane.add(anterior);
			contentPane.add(maisLista); 
			
			contentPane.add(jscrool);
			FormPrincipal.this.repaint();
			
			maisLista.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					paginacaoEstante(estante.getPaginacao().getAvancar());
					
				}
			});
			
			anterior.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					paginacaoEstante(estante.getPaginacao().getAnterior());
					
				}
			});
			
			setVisible(true);
		
		
	}
	
	
	private void paginacaoAutores(String url){
		
		AutorPaginacao autores = new DadosAPI().getAutores(url);
		limparLista(model);
		
		for(Autor obj :autores.getListaAutores()){
			
			
			
			
			
			model.addElement("<HTML><div><div style='color:blue; font-size:15px'>"+obj.getAutor()+"<BR></div></html>");
			
			
		}
		
			list3.setSize(400, 300);
		
			list3.setModel(model);
			list3.setVisible(true);
		
		
		jscrool.setViewportView(list3);
		
		contentPane.add(anterior);
		contentPane.add(maisLista); 
		
		contentPane.add(jscrool);
		FormPrincipal.this.repaint();
		
		maisLista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paginacaoAutores(autores.getPaginacao().getAvancar());
				
			}
		});
		
		anterior.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paginacaoAutores(autores.getPaginacao().getAnterior());
				
			}
		});
		
		setVisible(true);
		
		
	}

	
	
	private void paginacaoOperacao(String url){
		
		try{
			OperacaoPaginacao operacoes = new DadosAPI().listarMinhasTransacoes(url);
		
		limparLista(model);
		
			for(Operacao obj :operacoes.getListaOperacoes()){
			
			    System.out.println(obj.toString());
			
			
			
			
				model.addElement("<HTML><div><div style='color:blue; font-size:15px'>"+obj.getEstante().getLivro().getTitulo()+"<BR></div>"+"<HTML>Código: ["+obj.getPk()+"]<br>modalidade: "+obj.getEstante().getLivro().getCategoria()+"<BR>"+"<HTML>preço: "+obj.getEstante().getLivro().getAno_publicacao()+"<BR><BR><BR></div><HTML>");
			
		}
		
			list4.setModel(model);
			list4.setVisible(true);
		
		
		jscrool.setViewportView(list4);
		
		contentPane.add(anterior);
		contentPane.add(maisLista); 
		
		contentPane.add(jscrool);
		FormPrincipal.this.repaint();
		maisLista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paginacaoOperacao(operacoes.getPaginacao().getAvancar());
				
			}
		});
		
		anterior.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paginacaoAutores(operacoes.getPaginacao().getAnterior());
				
			}
		});
		
		}catch(Exception e){
			limparLista();
			
			
			
			
			
			
		}
		setVisible(true);
		
		
	}
	

	
	private void limparLista(){
		model.clear();
		list.setVisible(false);
		list2.setVisible(false);
		list3.setVisible(false);
		list4.setVisible(false);
		
		
		if(LoginSenha.user.equals(" ")){
		JOptionPane.showMessageDialog(null,"Você não está logado para puder ver esses dados");
		}else{
			JOptionPane.showMessageDialog(null,"Você não tem dados registrados nessa operação");
		}
	
		
		
		
	}
	
	private void logof(){
		model.clear();

		list.setVisible(false);
		list2.setVisible(false);
		list3.setVisible(false);
		list4.setVisible(false);
		
		LoginSenha.user=" ";
		LoginSenha.senha=" ";
		loginItem.setEnabled(true);
		logofItem.setEnabled(false);
		JOptionPane.showMessageDialog(null, "Logof realizado com sucesso");
		labelUsuario("não logado");
		
	}
	
	
	public void labelUsuario(String usuario){
       labelUsuario.setText("Usuário: "+usuario);
		
		FormPrincipal.this.repaint();
	}
	
	public void cadastrarLivro(FormPrincipal formPrincipal){
		CadastroLivro cadastrarLivro = new CadastroLivro(formPrincipal);
		cadastrarLivro.setVisible(true);
	}

}
