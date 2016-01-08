package aplication.client.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

import aplication.IMonitor;
import aplication.Medicao;
import aplication.TipoGrandeza;
import aplication.client.datamodel.MedicaoTableModel;
import aplication.exceptions.ServerNotFoundException;
import commonservices.naming.NamingProxy;
import distribution.clientproxy.ClientProxy;
import distribution.clientproxy.IMonitorProxy;
import distribution.clientproxy.MonitorProxy;

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

				//				try{
				NamingProxy namingProxy = new NamingProxy("localhost", 1313);
																				
				
				try {
					
					IMonitor monitor = (IMonitor) namingProxy.lookup("Monitor");
					
					Medicao m = null;
				
					if( (Math.random()*10) > 5){

						m = monitor.getMedicao(TipoGrandeza.TEMPERATURA);

					} else{

						m = monitor.getMedicao(TipoGrandeza.UMIDADE);
					}
														
					tableModel.inserir(m);
									
				} catch (ServerNotFoundException e1) {
					
					JOptionPane
					.showMessageDialog(null, e1.getMessage());
				}

//				Object m = null;
//				
//				System.out.println("B");
//				
//				if (monitor instanceof MonitorProxy){
//					System.out.println("A");
//					if( (Math.random()*10) > 5){
//						
//						m = ((MonitorProxy)monitor).getMedicao(TipoGrandeza.TEMPERATURA);
//						
//					} else{
//						
//						m = ((MonitorProxy)monitor).getMedicao(TipoGrandeza.UMIDADE);
//					}
//					
//					System.out.println(m.getClass());
//					//Erro, está tentando dar um cast de ServerNotFoundException Para medição
//					tableModel.inserir(((Medicao)m));
//
//				}else{
//					System.out.println(monitor.getClass());
//					System.out.println(monitor.getClass());
//					JOptionPane
//					.showMessageDialog(null, ((ServerNotFoundException)monitor).getMessage());
//				}

				//					IMonitor monitor = (IMonitor) namingProxy.lookup("Monitor");				

				//				} catch (RemoteException re){
				//					System.out.println(re.getMessage());
				//				} catch (NotBoundException nbe) {
				//					System.out.println(nbe.getMessage());
				//				} catch (IOException e1) {
				//					e1.printStackTrace();
				//				} catch (Throwable e1) {
				//					e1.printStackTrace();
				//				}
				//			}
			}});
		btnNewButton.setBounds(10, 11, 200, 23);
		frmMonitorTermico.getContentPane().add(btnNewButton);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Medi\u00E7\u00F5es Realizadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
