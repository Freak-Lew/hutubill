package gui.panel;

import entity.Category;
import gui.listener.CategoryListener;
import gui.model.CategoryModel;
import service.CategoryService;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends JPanel{
    public static CategoryPanel instance = new CategoryPanel();
    public JButton bInsert = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");
    public CategoryModel ctm= new CategoryModel();
    public JTable t = new JTable(ctm);

    private CategoryPanel(){
        JScrollPane sTable = new JScrollPane(t);
        JPanel pSubmit = new JPanel();

        sTable.setPreferredSize(new Dimension(220,230));

        pSubmit.add(bInsert);
        pSubmit.add(bEdit);
        pSubmit.add(bDelete);

        setLayout(new BorderLayout());
        add(sTable,BorderLayout.NORTH);
        add(pSubmit,BorderLayout.CENTER);

        addListener();
    }
    public Category getSelectedCategory() {
        int index = t.getSelectedRow();
        return ctm.categories.get(index);
    }

    public void updateData() {
        ctm.categories = new CategoryService().list();
        t.updateUI();
        t.getSelectionModel().setSelectionInterval(0, 0);

        if(0==ctm.categories.size()){
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
            bInsert.setEnabled(false);
        }
        else{
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
            bInsert.setEnabled(true);
        }
    }

    private void addListener(){
        CategoryListener listener = new CategoryListener();
        bInsert.addActionListener(listener);
        bEdit.addActionListener(listener);
        bDelete.addActionListener(listener);
    }
}
