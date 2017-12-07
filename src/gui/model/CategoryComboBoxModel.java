package gui.model;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import entity.Category;
import service.CategoryService;

public class CategoryComboBoxModel implements ComboBoxModel<Category>{

    public List<Category> categories = new CategoryService().list();
    public Category c;

    public CategoryComboBoxModel(){
        if(!categories.isEmpty())
            c=categories.get(0);
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return categories.size();
    }

    @Override
    public Category getElementAt(int index) {
        // TODO Auto-generated method stub
        return categories.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
    }

    @Override
    public Object getSelectedItem() {
        if(!categories.isEmpty())
            return c;
        else
            return null;

    }

}