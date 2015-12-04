package aplication.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

import aplication.IMonitor;
import aplication.Medicao;
import aplication.TipoGrandeza;
import aplication.client.datamodel.MedicaoTableModel;

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
				try{
					//TODO: utilizar o servico de nomes a ser desenvolvido
					Registry registry = LocateRegistry.getRegistry("localhost", 8080);
					
					//TODO: subsituir pelo padrao lookup
					IMonitor monitor = (IMonitor) registry.lookup("Monitor");				
					
					Medicao m1 = monitor.getMedicao(TipoGrandeza.TEMPERATURA);
					System.out.println("Temperatura: "+m1.getValue()+ ", Unidade: "+m1.getUnidade());
					
					
					Medicao m2 = monitor.getMedicao(TipoGrandeza.UMIDADE);
					System.out.println("Temperatura: "+m2.getValue()+ ", Unidade: "+m2.getUnidade());
					
					tableModel.inserir(m1);
				} catch (RemoteException re){
					System.out.println(re.getMessage());
				} catch (NotBoundException nbe) {
					System.out.println(nbe.getMessage());
				}
			}
		});
		btnNewButton.setBounds(10, 11, 200, 23);
		frmMonitorTermico.getContentPane().add(btnNewButton);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Medições Realizadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(4, 35, 410, 210);
		frmMonitorTermico.getContentPane().add(panel);
		panel.setLayout(new BorderLayout());
		
		//add table
		table = new JTable();
		table.setBounds(6, 16, 398, 187);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		JTableHeader header = table.getTableHeader();
		table.setModel(tableModel);
		
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
	}
}
