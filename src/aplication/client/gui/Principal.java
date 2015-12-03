package aplication.client.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import aplication.Medicao;
import aplication.client.model.MedicaoTableModel;

public class Principal {

	private JFrame frmMonitorTermico;
	private JTable table;
	private MedicaoTableModel tableModel;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmMonitorTermico.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMonitorTermico = new JFrame();
		frmMonitorTermico.setTitle("Monitor T\u00E9rmico");
		frmMonitorTermico.setBounds(100, 100, 515, 343);
		frmMonitorTermico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMonitorTermico.getContentPane().setLayout(null);
		
		//add table model
		this.tableModel = new MedicaoTableModel();
		
		JButton btnNewButton = new JButton("Realizar Medi\u00E7\u00E3o");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: pegar o codigo do ClientMonitor para inserir na acao do botao 
				Medicao m1 = new Medicao();
				if(Math.random() > 0.5){
					m1.setGrandeza("Temperatura");
					m1.setUnidade("Celsius");
				} else {
					m1.setGrandeza("Umidade");
					m1.setUnidade("kg/cm³");					
				}
				m1.setValue((float) (Math.round(Math.random()*100)));
				tableModel.inserir(m1);
			}
		});
		btnNewButton.setBounds(10, 11, 200, 23);
		frmMonitorTermico.getContentPane().add(btnNewButton);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Medições Realizadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(4, 35, 410, 210);
		frmMonitorTermico.getContentPane().add(panel);
		panel.setLayout(null);
		
		//add table
		table = new JTable();
		table.setBounds(6, 16, 398, 187);
		panel.add(table);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setModel(tableModel);
	}
}
