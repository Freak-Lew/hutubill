package gui.listener;

import entity.Category;
import gui.panel.CategoryPanel;
import service.CategoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        CategoryPanel categoryPanel = CategoryPanel.instance;
        CategoryService categoryService = new CategoryService();
        JButton b = (JButton) e.getSource();
        if(b == categoryPanel.bInsert){
            Category category = new Category();
            category.name = JOptionPane.showInputDialog("请输入要新增的分类：");
            if(category.name != null) {
                categoryService.add(category);
                categoryPanel.updateData();
            }
            else
                return;
        }

        if(b == categoryPanel.bEdit){
            Category category = categoryPanel.getSelectedCategory();
            category.name = JOptionPane.showInputDialog("修改分类名称：");
            if(category.name != null) {
                categoryService.update(category);
                categoryPanel.updateData();
            }
            else
                return;

        }

        if(b == categoryPanel.bDelete){
            Category c = categoryPanel.getSelectedCategory();
            if(0 != c.recordNumber){
                JOptionPane.showMessageDialog(categoryPanel, "本分类下有消费记录存在，不能删除");
                return;
            }
            int n = JOptionPane.showConfirmDialog(categoryPanel, "是否删除该分类？", "删除",JOptionPane.YES_NO_OPTION);
            if( n == 0){
                Category category = categoryPanel.getSelectedCategory();
                categoryService.delete(category.id);
                categoryPanel.updateData();
            }
            if( n == 1){
                return;
            }

        }
    }
}
