package gui.model;


import entity.Category;
import service.CategoryService;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CategoryModel extends AbstractTableModel {

    String[] columnNames = new String[]{"分类名称","消费次数"};

    public List<Category> categories = new CategoryService().list();

    public int getRowCount() {
        return categories.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int columnIndex) {

        return columnNames[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Category c = categories.get(rowIndex);
        if(0 == columnIndex)
            return c.name;
        if(1 == columnIndex)
            return c.recordNumber;

        return null;
    }
}