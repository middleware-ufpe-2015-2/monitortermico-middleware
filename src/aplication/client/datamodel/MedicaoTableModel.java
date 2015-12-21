package aplication.client.datamodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import aplication.Medicao;

public class MedicaoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -31831367358819164L;

	private List<Medicao> medicoes;
	
	public MedicaoTableModel() {
		this.setMedicoes(new ArrayList<Medicao>());
	}
	
	@Override
	public int getRowCount() {
		return this.medicoes.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}
	
	@Override  
    public String getColumnName(int column) {  
        if (column == 0) {  
            return "Valor";  
        } else if (column == 1) {  
            return "Unidade";  
        } else if (column == 2) {
        	return "Grandeza";
        } else if (column == 3) {
        	return "Data";
        }
        return "";  
    } 	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Medicao m = this.medicoes.get(rowIndex);
		
		if(columnIndex == 0){
			return m.getValue();
		} else if(columnIndex == 1){
			return m.getUnidade();
		} else if(columnIndex == 2){
			return m.getGrandeza();
		} else if(columnIndex == 3){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return sdf.format(m.getDataMedicao());
		}
		return "";
	}
	
	public void inserir(Medicao m){
		medicoes.add(m);
		fireTableDataChanged();
	}

	public List<Medicao> getMedicoes() {
		return medicoes;
	}

	public void setMedicoes(List<Medicao> medicoes) {
		this.medicoes = medicoes;
	}

}
