package gui.listener;

import gui.panel.ConfigPanel;
import service.ConfigService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ConfigListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        ConfigPanel configPanel = ConfigPanel.instance;

        String mysqlpath = configPanel.tInstall.getText();

        if(!GUIUtil.checkNumber(configPanel.tBudget, "预算"))
            return;

        File file = new File(mysqlpath,"bin/mysql.exe");
        if(!file.exists()){
            JOptionPane.showMessageDialog(configPanel,"必须输入正确路径");
            configPanel.grabFocus();
            return;
        }

        ConfigService configService  = new ConfigService();
        configService.update(ConfigService.budget,configPanel.tBudget.getText());
        configService.update(ConfigService.mysqlPath,mysqlpath);

        JOptionPane.showMessageDialog(configPanel,"修改成功");
    }
}
