package formularios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.User;
import metodos.LoginSenha;
import metodos.Sessao;

import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JDialog {
	
	private FormPrincipal formPrincipal;

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private Sessao sessao;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public Login(FormPrincipal formPrincipal) {
		this.formPrincipal=formPrincipal;
		sessao = new Sessao();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("login");
		setBounds(100, 100, 381, 239);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextField textPane = new JTextField();
		textPane.setBounds(125, 48, 189, 20);
		contentPanel.add(textPane);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(125, 91, 189, 20);
		contentPanel.add(passwordField);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(23, 48, 86, 14);
		contentPanel.add(lblUsername);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(23, 94, 46, 14);
		contentPanel.add(lblSenha);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String username = textPane.getText();
						String senha = String.valueOf(passwordField.getPassword());
					
						User user = sessao.getUser(username, senha);
						
						if(user !=null){
						JOptionPane.showMessageDialog(null,"Login efetuado com sucesso");
						formPrincipal.logofItem.setEnabled(true);
						formPrincipal.loginItem.setEnabled(false);
						formPrincipal.labelUsuario(username);
						formPrincipal.repaint();
						
						
						LoginSenha.user=username;
						LoginSenha.senha=senha;
						textPane.setText("");
						passwordField.setText("");
						Login.this.dispose();
						}else{
							
						}
						
						
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Login.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
