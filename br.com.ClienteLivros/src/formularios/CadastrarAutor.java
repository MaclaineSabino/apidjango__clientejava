package formularios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import metodos.DadosAPI;
import metodos.Urls;
import metodos.Util;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastrarAutor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nome_autor;
	
	FormPrincipal formprincipal;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public CadastrarAutor(FormPrincipal formprinc) {
		formprincipal = formprinc;
		
		setTitle("Cadastrar autor");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			nome_autor = new JTextField();
			nome_autor.setBounds(94, 99, 273, 20);
			contentPanel.add(nome_autor);
			nome_autor.setColumns(10);
		}
		{
			JLabel lblNome = new JLabel("Nome");
			lblNome.setBounds(29, 102, 46, 14);
			contentPanel.add(lblNome);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Cadastrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String nome = nome_autor.getText();
						
					int status =	new DadosAPI().cadastrarAutor(Urls.autores, nome);
					
					Util u = new Util();
					String resposta = u.tratamentoErro(status);
						u.mensagem(resposta);
						
						formprincipal.cadastrarLivro(formprincipal);
						
						CadastrarAutor.this.dispose();
						
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
						CadastrarAutor.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
