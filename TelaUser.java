package com.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class TelaUser {

	private JFrame frmTelaUsurio;

	static TelaLogin window;

	public void CloseWindow() {
		// window.frame.setVisible(false);
		window.frmLogin.dispose();
	}

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			TelaUser window = new TelaUser();
			window.frmTelaUsurio.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public TelaUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTelaUsurio = new JFrame();
		frmTelaUsurio.setTitle("Tela Usu\u00E1rio");
		frmTelaUsurio.setBounds(TelaLogin.SCREEN_POSITION_X, TelaLogin.SCREEN_POSITION_Y, 450, 300);
		frmTelaUsurio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTelaUsurio.getContentPane().setLayout(null);

		JLabel lblJanelaUsurio = new JLabel("Janela Usu\u00E1rio");
		lblJanelaUsurio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblJanelaUsurio.setBounds(136, 11, 155, 36);
		frmTelaUsurio.getContentPane().add(lblJanelaUsurio);
	}

}
