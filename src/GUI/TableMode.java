package GUI;

import javax.swing.table.DefaultTableModel;

/**
 * Created by Konrad on 17.01.2016.
 */
public class TableMode extends DefaultTableModel {
    @Override
    public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
    }
}
