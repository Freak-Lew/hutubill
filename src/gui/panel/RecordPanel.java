package gui.panel;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import entity.Category;
import gui.listener.RecordListener;
import gui.model.CategoryComboBoxModel;
import org.jdesktop.swingx.JXDatePicker;
import service.CategoryService;
import util.CategoryDAO;
import util.GUIUtil;

public class RecordPanel extends WorkingPanel{
    public static RecordPanel instance = new RecordPanel();

    public JLabel lSpend = new JLabel("花费（￥）");
    public JLabel lCategory = new JLabel("分类");
    public JLabel lRemarks = new JLabel("备注");
    public JLabel lDate = new JLabel("日期");
    public JTextField tSpend = new JTextField("");

    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox cbCategory = new JComboBox(cbModel);
    public JTextField tRemarks = new JTextField();
    public JXDatePicker jxDate = new JXDatePicker(new Date());
    public JButton bSubmit = new JButton("记一笔");

    private RecordPanel(){
        JPanel pInput = new JPanel();
        JPanel pSubmit = new JPanel();

        pInput.setLayout(new GridLayout(4,2,250,50));

        pInput.add(lSpend);
        pInput.add(tSpend);
        pInput.add(lCategory);
        pInput.add(cbCategory);
        pInput.add(lRemarks);
        pInput.add(tRemarks);
        pInput.add(lDate);
        pInput.add(jxDate);
        pSubmit.add(bSubmit);

        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.NORTH);
        this.add(pSubmit,BorderLayout.CENTER);

        addListener();
    }

    public Category getSelectedCategory(){
        return (Category) cbCategory.getSelectedItem();
    }

    public void updateData() {
        cbModel.categories = new CategoryService().list();
        cbCategory.updateUI();
        resetInput();
        tSpend.grabFocus();
    }

    public void resetInput(){
        tSpend.setText("");
        tRemarks.setText("");
        if(0 != cbModel.categories.size())
            cbCategory.setSelectedIndex(0);
        jxDate.setDate(new Date());
    }

    public void addListener(){
        RecordListener listener = new RecordListener();
        bSubmit.addActionListener(listener);
    }
}
