package aplication.client.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class PrincipalMenu {

	//private JFrame frmMonitorTermico;

	// Seguindo o modelo da outra tela, alterei o main.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new PrincipalMenu().go();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private void go() {

		JFrame frmMonitorTermico = new JFrame("Monitor Termico");

		JMenuBar menuBar = new JMenuBar();

		JMenu menuOpcoes = new JMenu("Opcoes");

		JMenuItem menuReaMed = new JMenuItem("Realizar Medições");
		JMenuItem menuHistMed = new JMenuItem("Historico de Medicoes");
		JMenuItem menuSair = new JMenuItem("Sair");

		menuSair.addActionListener(new MenuHandler());
		menuOpcoes.add(menuReaMed);
		menuOpcoes.add(menuHistMed);
		menuOpcoes.add(new JSeparator());
		menuOpcoes.add(menuSair);
		menuBar.add(menuOpcoes);

		menuReaMed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RealizaMed realizaMed = new RealizaMed();
				realizaMed.getFrmMonitorTermico().setVisible(true);
			}
		});

		menuHistMed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				HistoricoMed historicoMed = new HistoricoMed();
				historicoMed.getFrmHistoricoMed().setVisible(true);
			}

		});

		JMenu menuAjuda = new JMenu("Ajuda");
		JMenuItem menuSobre = new JMenuItem("Sobre");
		menuSobre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"Sistema Monitor Termico:"
										+ "\nO Monitor Termico é um sistema de controle"
										+ "\ne gestao onde os usuarios podem realizar e"
										+ "\nvisualizar as medicoes do equipamento."
										+ "\n"
										+ "\nDesenvolvimento:"
										+ "\nTurma de SI-Topicos5",
								"Informacoes", JOptionPane.INFORMATION_MESSAGE);
			}
		});
				
		menuAjuda.add(menuSobre);
		menuBar.add(menuAjuda);

		frmMonitorTermico.setJMenuBar(menuBar);
		frmMonitorTermico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMonitorTermico.setBounds(100, 100, 515, 343);
		frmMonitorTermico.setLocationRelativeTo(null);
		frmMonitorTermico.setVisible(true);

		ImageIcon icon = new ImageIcon(getClass().getResource("termometroSol2.png"));
		JLabel label = new JLabel("Monitor Termico", icon, SwingConstants.CENTER);
		label.setFont(new Font("Courier", Font.BOLD, 28));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.BOTTOM);

		frmMonitorTermico.add(label);

	}

	private class MenuHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}
}
