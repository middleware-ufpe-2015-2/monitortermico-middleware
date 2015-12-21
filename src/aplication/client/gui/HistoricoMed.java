package aplication.client.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

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

import javax.swing.JButton;

import commonservices.naming.NamingProxy;

import java.awt.event.ActionListener;
import java.util.List;

public class HistoricoMed {
	private JFrame frmHistoricoMed;
	private JTable table;
	private MedicaoTableModel tableModel;
	private JPanel panel;

	/**
	 * Create the application.
	 */
	public HistoricoMed() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		frmHistoricoMed = new JFrame();
		frmHistoricoMed.setTitle("Hist\u00f3rico de Medi\u00E7\u00F5es");
		frmHistoricoMed.setBounds(100, 100, 515, 343);
		frmHistoricoMed.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHistoricoMed.getContentPane().setLayout(null);
		
		//add table model
		this.tableModel = new MedicaoTableModel();
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u00daltimas Medi\u00E7\u00F5es Realizadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(4, 35, 410, 210);
		frmHistoricoMed.getContentPane().add(panel);
		panel.setLayout(new BorderLayout());
		
		//add table
		table = new JTable();
		table.setBounds(6, 16, 398, 187);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		JTableHeader header = table.getTableHeader();
		table.setModel(tableModel);
		
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		
		JButton btnRecuperarMedicoes = new JButton("Recuperar Medi\u00E7\u00F5es");
		btnRecuperarMedicoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NamingProxy namingProxy = new NamingProxy("localhost", 1313);
				try{
					IMonitor monitor = (IMonitor) namingProxy.lookup("Monitor");
					List<Medicao> lista = monitor.getCincoUltimasMedicoes();
					for(Medicao m: lista){
						tableModel.inserir(m);
					}
				} catch(Throwable e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
			}
		});
		btnRecuperarMedicoes.setBounds(131, 256, 129, 23);
		frmHistoricoMed.getContentPane().add(btnRecuperarMedicoes);
		
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public MedicaoTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(MedicaoTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JFrame getFrmHistoricoMed() {
		return frmHistoricoMed;
	}

	public void setFrmHistoricoMed(JFrame frmHistoricoMed) {
		this.frmHistoricoMed = frmHistoricoMed;
	}
}
