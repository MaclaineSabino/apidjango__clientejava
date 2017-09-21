package formularios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import metodos.DadosAPI;
import metodos.LoginSenha;
import metodos.Urls;
import metodos.Util;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastrarUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nome;
	private JTextField endereco;
	private JTextField cidade;
	private JTextField email;
	private JTextField username;
	private JPasswordField senha;
	private JPasswordField confirmaSenha;
	private JComboBox estado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastrarUsuario dialog = new CadastrarUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastrarUsuario() {
		String[] estados = {"ES","MG","RJ","SP","AL","BA","CE","MA","PB","PE","PI","RN","SE","DF","GO","MT","MS","AC","AP"
				,"AM","PA","RO","RR","TO","PR","SC","RS"};
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Cadastro de usuário");
		setBounds(100, 100, 580, 419);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNome = new JLabel("Nome:");
			lblNome.setBounds(91, 53, 46, 14);
			contentPanel.add(lblNome);
		}
		{
			nome = new JTextField();
			nome.setBounds(209, 50, 290, 20);
			contentPanel.add(nome);
			nome.setColumns(10);
		}
		{
			JLabel lblEndereo = new JLabel("Endere\u00E7o");
			lblEndereo.setBounds(91, 84, 60, 14);
			contentPanel.add(lblEndereo);
		}
		{
			endereco = new JTextField();
			endereco.setBounds(209, 81, 290, 20);
			contentPanel.add(endereco);
			endereco.setColumns(10);
		}
		{
			JLabel lblCidade = new JLabel("Cidade");
			lblCidade.setBounds(91, 115, 46, 14);
			contentPanel.add(lblCidade);
		}
		{
			cidade = new JTextField();
			cidade.setBounds(209, 112, 290, 20);
			contentPanel.add(cidade);
			cidade.setColumns(10);
		}
		{
			JLabel lblEstado = new JLabel("Estado");
			lblEstado.setBounds(91, 146, 46, 14);
			contentPanel.add(lblEstado);
		}
		{
			estado = new JComboBox(estados);
			estado.setBounds(209, 143, 290, 20);
			contentPanel.add(estado);
		}
		{
			JLabel lblEmail = new JLabel("Email");
			lblEmail.setBounds(91, 180, 46, 14);
			contentPanel.add(lblEmail);
		}
		{
			email = new JTextField();
			email.setBounds(209, 174, 290, 20);
			contentPanel.add(email);
			email.setColumns(10);
		}
		{
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setBounds(91, 211, 75, 14);
			contentPanel.add(lblUsername);
		}
		{
			username = new JTextField();
			username.setBounds(209, 208, 290, 20);
			contentPanel.add(username);
			username.setColumns(10);
		}
		{
			senha = new JPasswordField();
			senha.setBounds(209, 239, 290, 20);
			contentPanel.add(senha);
		}
		{
			JLabel lblSenha = new JLabel("Senha");
			lblSenha.setBounds(91, 242, 46, 14);
			contentPanel.add(lblSenha);
		}
		{
			JLabel lblConfirmaSenha = new JLabel("Confirma senha");
			lblConfirmaSenha.setBounds(91, 273, 116, 14);
			contentPanel.add(lblConfirmaSenha);
		}
		{
			confirmaSenha = new JPasswordField();
			confirmaSenha.setBounds(209, 270, 290, 20);
			contentPanel.add(confirmaSenha);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cadastrar = new JButton("Cadastrar");
				cadastrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(String.valueOf(senha.getPassword()).equals(String.valueOf(confirmaSenha.getPassword()))){
						/*
						 * 
						 * 'user':{'email':'marcelo@hotmail.com',
                        'username':'renato',
                        'password':'renato123'},
                        'nome':'Marcelo',
                        'endereco':'rua altos 123',
                        'cidade':'Teresina',
                        'estado':'PI'
						 */
							
							String emailU = email.getText();
							String usernameU = username.getText();
							String senhaU = String.valueOf(senha.getPassword());
							String nomeU = nome.getText();
							String enderecoU = endereco.getText();
							String cidadeU = cidade.getText();
							String estadoU = String.valueOf(estado.getSelectedItem());
							
							int retorno = new DadosAPI().cadastrarUsuario(emailU, usernameU, senhaU, nomeU, enderecoU, cidadeU, estadoU,Urls.usuarios);
							
							Util util =  new Util();
							String resposta = util.tratamentoErro(retorno);
							util.mensagem(resposta);
							
							if (retorno>=200 && retorno<=203){
								LoginSenha.user=usernameU;
								LoginSenha.senha=senhaU;
							}
							email.setText("");
							username.setText("");
							senha.setText("");
							nome.setText("");
							endereco.setText("");
							cidade.setText("");
							
							CadastrarUsuario.this.dispose();
							
							
							
						}else{
							
							JOptionPane.showMessageDialog(null,"a confirmação de senha não confere");
						}
						
						
						
					}
				});
				cadastrar.setActionCommand("OK");
				buttonPane.add(cadastrar);
				getRootPane().setDefaultButton(cadastrar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						CadastrarUsuario.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
