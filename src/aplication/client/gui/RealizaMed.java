package aplication.client.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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

import commonservices.naming.NamingProxy;

public class RealizaMed {

	private JFrame frmMonitorTermico;
	private JTable table;
	private MedicaoTableModel tableModel;
	private JPanel panel;


	/**
	 * Create the application.
	 */
	public RealizaMed() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMonitorTermico = new JFrame();
		frmMonitorTermico.setTitle("Monitor T\u00E9rmico");
		frmMonitorTermico.setBounds(100, 100, 515, 343);
		frmMonitorTermico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMonitorTermico.getContentPane().setLayout(null);
		
		//add table model
		this.tableModel = new MedicaoTableModel();
		
		JButton btnNewButton = new JButton("Realizar Medi\u00E7\u00E3o");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//insercao centralizada 
//				Medicao m = new Medicao();
//				 m.setValue((float) (Math.random() * 100));
//				 System.out.println("Temp: " + m.getValue());
//				 
//				 m.setGrandeza("30G");
//				 System.out.println("Grand: " + m.getGrandeza());
//				 m.setUnidade("Celsius");
//				 System.out.println("Uni: " + m.getUnidade());
//				 
//				 tableModel.inserir(m);
				 
				try{
					NamingProxy namingProxy = new NamingProxy("localhost", 1313);
					
					IMonitor monitor = (IMonitor) namingProxy.lookup("Monitor");				
					
					Medicao m1 = monitor.getMedicao(TipoGrandeza.TEMPERATURA);
					System.out.println("Temperatura: "+m1.getValue()+ ", Unidade: "+m1.getUnidade());
					
					Medicao m2 = monitor.getMedicao(TipoGrandeza.UMIDADE);
					System.out.println("Umidade: "+m2.getValue()+ ", Unidade: "+m2.getUnidade());
					
					tableModel.inserir(m1);
					tableModel.inserir(m2);
				} catch (RemoteException re){
					System.out.println(re.getMessage());
				} catch (NotBoundException nbe) {
					System.out.println(nbe.getMessage());
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 11, 200, 23);
		frmMonitorTermico.getContentPane().add(btnNewButton);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Medi��es Realizadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

	public JFrame getFrmMonitorTermico() {
		return frmMonitorTermico;
	}

	public void setFrmMonitorTermico(JFrame frmMonitorTermico) {
		this.frmMonitorTermico = frmMonitorTermico;
	}
}
