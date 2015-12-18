package aplication.client.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

import aplication.Medicao;
import aplication.client.datamodel.MedicaoTableModel;

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
		frmHistoricoMed.setTitle("Histórico de Medições");
		frmHistoricoMed.setBounds(100, 100, 515, 343);
		frmHistoricoMed.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHistoricoMed.getContentPane().setLayout(null);
		
		//add table model
		this.tableModel = new MedicaoTableModel();
		
		//teste para verificar se está inserindo na table e mostrando na view
		for (int i = 0; i < 5; i++) {
			Medicao m = new Medicao();
			m.setValue((float) (Math.random() * 100));
			System.out.println("Temp: " + m.getValue());
			;
			m.setGrandeza("30G");
			System.out.println("Grand: " + m.getGrandeza());
			m.setUnidade("Celsius");
			System.out.println("Uni: " + m.getUnidade());

			tableModel.inserir(m);
		}
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Últimas Medições Realizadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
