package com.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.DAO.DataSource;
import com.DAO.UsuarioDAO;
import com.model.Usuario;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class TelaAdmin {
	static TelaLogin window;
	private JFrame frame;
	private JLabel lblJanelaAdmin;
	JTable jTable1;
	private JTextField textFieldSenha;
	private JTextField textFieldLogin;
	private JTextField textFieldCofirmPassw;
	JRadioButton rdbtnAdministrador;
	JRadioButton rdbtnUsuario;

	public void CloseWindow() {
		// window.frame.setVisible(false);
		window.frmLogin.dispose();
	}

	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			TelaAdmin window = new TelaAdmin();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public TelaAdmin() {
		initialize();
	}
		public boolean checkNewPasswrd(String senha1, String senha2) {
			int s1 = Integer.parseInt(senha1) ;
			int s2 = Integer.parseInt(senha2) ;
			
			
			if (s1 == -1) {
					return false;
				}if (s1 != s2 ) {
					return true;
				}
			return false;	
		}
	   public void readJtable(){
	        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
	        DataSource ds =  new DataSource();
	        UsuarioDAO U = new UsuarioDAO(ds);
	        
	        for (Usuario user: U.ListarLogin()) {
	          modelo.addRow(new Object[]{ 
	            user.getId(),
	            user.getLogin(),
	            user.getSenha(),
	            user.getTipo(),
	           });      
	        }
	    }
	   
	   public void clearJtable(){
	        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
	          modelo.setNumRows(0);     
	    }
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(TelaLogin.SCREEN_POSITION_X, TelaLogin.SCREEN_POSITION_Y, 667, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnInserirUsurio = new JButton("Inserir Usu\u00E1rio");
		btnInserirUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkNewPasswrd(textFieldSenha.getText(), textFieldCofirmPassw.getText()) == true) {
					JOptionPane.showMessageDialog(frame, "As Senhas não Conferem");
				}else {
					Usuario U =  new Usuario();
					DataSource ds = new DataSource();
					UsuarioDAO user =  new UsuarioDAO(ds);
					U.setLogin(textFieldLogin.getText());
					U.setSenha(Integer.parseInt(textFieldSenha.getText()));
					if (rdbtnAdministrador.isSelected()) {
						U.setTipo(1);
					}else {
						U.setTipo(2);						
					}
					user.create(U);
					textFieldLogin.setText("");
					textFieldSenha.setText("");
					textFieldCofirmPassw.setText("");
					rdbtnAdministrador.setSelected(false);
					rdbtnUsuario.setSelected(false);
					clearJtable();
					readJtable();
					}
			}
		});
		btnInserirUsurio.setBounds(36, 375, 138, 23);
		frame.getContentPane().add(btnInserirUsurio);

		JButton btnListarContas = new JButton("Listar Contas");
		btnListarContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataSource ds =  new DataSource();
				UsuarioDAO user =  new UsuarioDAO(ds);
				int row = 0;
				readJtable();					
				for (int i = 1; i < jTable1.getRowCount(); i++) {
				
				Object obj = jTable1.getValueAt(row, 0);
				int consultaid = Integer.parseInt(obj.toString()); 
				System.out.println(consultaid);
				user.orderId(consultaid, row );
				row ++;
				}
				
				clearJtable();
				readJtable();
			}
		});
		btnListarContas.setBounds(519, 375, 110, 23);
		frame.getContentPane().add(btnListarContas);
		
		jTable1 = new JTable();
		javax.swing.JScrollPane jScrollPane1 = new JScrollPane(jTable1);
		jScrollPane1.setSize(290, 268);
		jScrollPane1.setLocation(339, 59);

		lblJanelaAdmin = new JLabel("Janela Admin");
		lblJanelaAdmin.setBounds(115, 11, 167, 23);
		lblJanelaAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(jScrollPane1);
		frame.getContentPane().add(lblJanelaAdmin);
		
		
		
		jTable1.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	            },
	            new String [] {
	                "ID", "Login", "Senha", "tipo"
	            }
	        ) {
	            /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				boolean[] canEdit = new boolean [] {
	                false, false, true, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
	        jScrollPane1.setViewportView(jTable1);
	        
	        rdbtnUsuario = new JRadioButton("Usu\u00E1rio");
	        rdbtnAdministrador = new JRadioButton("Administrador");
	        textFieldSenha = new JTextField();
	        textFieldSenha.setBounds(36, 142, 179, 20);
	        frame.getContentPane().add(textFieldSenha);
	        textFieldSenha.setColumns(10);
	        
	        textFieldLogin = new JTextField();
	        textFieldLogin.setBounds(36, 95, 176, 20);
	        frame.getContentPane().add(textFieldLogin);
	        textFieldLogin.setColumns(10);
	        
	        rdbtnUsuario.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	rdbtnAdministrador.setSelected(false);
	        	}
	        });
	        rdbtnUsuario.setBounds(36, 258, 109, 23);
	        frame.getContentPane().add(rdbtnUsuario);
	        
	        rdbtnAdministrador.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		rdbtnUsuario.setSelected(false);
	        	}
	        });
	        rdbtnAdministrador.setBounds(36, 232, 109, 23);
	        frame.getContentPane().add(rdbtnAdministrador);
	        
	        JLabel lblLogin = new JLabel("Login");
	        lblLogin.setBounds(36, 80, 46, 14);
	        frame.getContentPane().add(lblLogin);
	        
	        textFieldCofirmPassw = new JTextField();
	        textFieldCofirmPassw.setBounds(36, 186, 179, 20);
	        frame.getContentPane().add(textFieldCofirmPassw);
	        textFieldCofirmPassw.setColumns(10);
	        
	        JLabel lblSenha = new JLabel("Senha");
	        lblSenha.setBounds(36, 126, 46, 14);
	        frame.getContentPane().add(lblSenha);
	        
	        JLabel lblConfirmeASenha = new JLabel("Confirme a Senha");
	        lblConfirmeASenha.setBounds(36, 173, 110, 14);
	        frame.getContentPane().add(lblConfirmeASenha);
	        
	        JButton btnRemoverUsurio = new JButton("Remover Usu\u00E1rio");
	        btnRemoverUsurio.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		DataSource ds =  new DataSource();
	        		Usuario user = new Usuario();
	        		UsuarioDAO U =  new UsuarioDAO(ds);
	        		Object coluna =  jTable1.getValueAt(jTable1.getSelectedRow(),0);
	        		int id =  Integer.parseInt(coluna.toString());
	        		System.out.println("Aqui o id"+id);
	        		user.setId(Integer.parseInt(coluna.toString()));
	        		U.delete(user);
	        		clearJtable();
	        		readJtable();
	        	}
	        });
	        btnRemoverUsurio.setBounds(342, 375, 138, 23);
	        frame.getContentPane().add(btnRemoverUsurio);
	}
}
