import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class AirplaneTable extends JTable{
    private static String[] columnNames = {"Make", "Model"};
    private static DefaultTableModel tableModel = new DefaultTableModel(columnNames, 17);
    private int currentRow = 0;
    private int lastRow = 17;

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
    public AirplaneTable(String[][] data) {
        super(tableModel);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getTableHeader().setReorderingAllowed(false);
        for (String[] row : data) {
            addData(row);
        }
    }

    public void addData(String[] data) {
        tableModel.insertRow(currentRow, data);
        currentRow++;
        if(tableModel.getValueAt(lastRow, 0) == null) {
            tableModel.removeRow(lastRow);
        }
        if (lastRow < 17) {
            lastRow++;
        }
    }

    public void removeData(int row) {
        tableModel.removeRow(row);
        currentRow--;
        if (tableModel.getRowCount() < lastRow) {
            tableModel.addRow(new String[] {null, null});
        }
        if (lastRow > 17) {
            lastRow--;
        }
    }

    public int getSelected() {
        int currentSelectedRow = getSelectedRow();
        return currentSelectedRow;
    }

    public int getCurrentRow() {
        return currentRow;
    }
}
