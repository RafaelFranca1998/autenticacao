package com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import com.DAO.DataSource;
import com.DAO.UsuarioDAO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;

public class TelaLogin {

	JFrame frmLogin;
	private JTextField textFielLogin;
	private JLabel lblLogin;
	private JLabel lblSenha;
	static TelaLogin window;
	private JPasswordField passwordFieldSenha;
	public static final int SCREEN_POSITION_X = 500;
	public static final int SCREEN_POSITION_Y = 250;

	// INICIA A APLICAÇÃO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new TelaLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// FUNÇÃO QUE FECHA A JANELA
	public void CloseWindow() {
		// window.frame.setVisible(false);
		window.frmLogin.dispose();
	}

	// CRIA E INICIA A APLICAÇÃO
	public TelaLogin() {
		initialize();
	}

	// INICIALIZA OS COMPONENTES DO FRAME (TELA)
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(SCREEN_POSITION_X, SCREEN_POSITION_Y, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);

		textFielLogin = new JTextField();
		textFielLogin.setBounds(147, 106, 166, 20);
		frmLogin.getContentPane().add(textFielLogin);
		textFielLogin.setColumns(10);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataSource datasource = new DataSource();
				UsuarioDAO dao = new UsuarioDAO(datasource);
				String login = textFielLogin.getText();
				int senha = Integer.parseInt(String.valueOf(passwordFieldSenha.getPassword()));
				if (dao.checkLogin(login, senha)) {
					int tipo = dao.checkTipo(login, senha);
					System.out.println(tipo);
					JOptionPane.showMessageDialog(frmLogin, "Bem vindo", "Menssagem", 2);
					if (tipo == 1) {
						TelaAdmin admin = new TelaAdmin();
						admin.run();
						TelaAdmin2 admin2 =  new TelaAdmin2();
					}
					if (tipo == 2) {
						TelaUser user = new TelaUser();
						user.run();
					} else {

					}
					CloseWindow();
				} else {
					JOptionPane.showMessageDialog(frmLogin, "Erro ao fazer Login", "ERRO!", 0);
				}

			}
		});
		btnEntrar.setBounds(224, 168, 89, 23);
		frmLogin.getContentPane().add(btnEntrar);

		lblLogin = new JLabel("Login");
		lblLogin.setBounds(101, 109, 46, 14);
		frmLogin.getContentPane().add(lblLogin);

		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(101, 140, 46, 14);
		frmLogin.getContentPane().add(lblSenha);

		JLabel lblAcessoAoSistema = new JLabel("Acesso ao Sistema");
		lblAcessoAoSistema.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		lblAcessoAoSistema.setBounds(147, 50, 196, 27);
		frmLogin.getContentPane().add(lblAcessoAoSistema);

		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(147, 137, 166, 20);
		frmLogin.getContentPane().add(passwordFieldSenha);
	}
}
