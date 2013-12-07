package CustMgtSys_1;

import javax.swing.table.DefaultTableModel;

/**
 * This class extends DefaultTableModel, overrides the isCellEditable method to always 
 * return false. 
 * @author ofalcon
 *
 */

public class CustomTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;	
	
	
	public CustomTableModel(Object[] columnNames, int rowCount){
		super(columnNames,rowCount);
	}

	@Override
	public boolean isCellEditable(int row,int column){
		return false;
	}
}