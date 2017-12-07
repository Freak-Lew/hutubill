package gui.listener;

import entity.Category;
import entity.Record;
import gui.panel.*;
import service.RecordService;
import util.CategoryDAO;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RecordListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        RecordPanel recoverPanel = RecordPanel.instance;

        if(!GUIUtil.checkEmpty(recoverPanel.tSpend,"花费") ||
           !GUIUtil.checkZero(recoverPanel.tSpend, "花费") ||
           !GUIUtil.checkNumber(recoverPanel.tSpend, "花费") ||
           !GUIUtil.checkEmpty(recoverPanel.tRemarks,"备注")){
            return;
        }
        if(recoverPanel.cbCategory.getItemCount() == 0){
            JOptionPane.showMessageDialog(recoverPanel, "无分类，请添加后记录");
            MainPanel.instance.workingPanel.show(CategoryPanel.instance);
        }

            int spend = Integer.parseInt(recoverPanel.tSpend.getText());
            Category c = recoverPanel.getSelectedCategory();
            String comment = recoverPanel.tRemarks.getText();
            Date date = recoverPanel.jxDate.getDate();

            RecordService recordService = new RecordService();
            recordService.add( spend, c, comment, date);

            JOptionPane.showMessageDialog(recoverPanel,"添加成功！");

            MainPanel.instance.workingPanel.show(SpendPanel.instance);
    }
}
