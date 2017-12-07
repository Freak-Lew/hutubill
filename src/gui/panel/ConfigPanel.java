package gui.panel;

import entity.Config;
import gui.listener.ConfigListener;
import service.ConfigService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ConfigPanel extends WorkingPanel {

    public static ConfigPanel instance = new ConfigPanel();

    public JLabel lBudget = new JLabel("本月预算(￥)");
    public JLabel lInstall = new JLabel("Mysql安装目录");
    public JTextField tBudget = new JTextField("");
    public JTextField tInstall = new JTextField("");
    public JButton bUpdate = new JButton("更新");

    private ConfigPanel(){
        JPanel pInput = new JPanel();
        JPanel pUpdate = new JPanel();

        pInput.setLayout(new GridLayout(4,1,20,45));

        pInput.add(lBudget);
        pInput.add(tBudget);
        pInput.add(lInstall);
        pInput.add(tInstall);

        pUpdate.add(bUpdate);

        this.setLayout(new BorderLayout());
        this .add(pInput, BorderLayout.NORTH);
        this.add(pUpdate, BorderLayout.CENTER);

        addListener();
    }

    public void updateData() {
        ConfigService configService = new ConfigService();
        tBudget.setText(configService.get("budget"));
        tInstall.setText(configService.get("mysqlPath"));
        tBudget.updateUI();
        tInstall.updateUI();
    }

    public void addListener(){
        ConfigListener cl = new ConfigListener();

        bUpdate.addActionListener(cl);
    }
}
