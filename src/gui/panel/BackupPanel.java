package gui.panel;

import gui.listener.BackupListener;
import util.CenterPanel;

import javax.swing.*;
import java.awt.*;

public class BackupPanel extends JPanel {
    public static BackupPanel instance = new BackupPanel();

    public JButton bBackup = new JButton("备份");
    public JPanel pBackup = new JPanel();

    private BackupPanel(){

        pBackup.add(bBackup);

        this.setLayout(new BorderLayout());
        this.add(pBackup,BorderLayout.CENTER);

        addListener();
    }

    private void addListener(){
        BackupListener listener = new BackupListener();

        bBackup.addActionListener(listener);
    }
}
