package gui.panel;

import gui.listener.RecoverListener;

import javax.swing.*;
import java.awt.*;

public class RecoverPanel extends JPanel {
    public static RecoverPanel instance = new RecoverPanel();

    public JButton bBackup = new JButton("恢复");
    public JPanel pBackup = new JPanel();

    private RecoverPanel(){

        pBackup.add(bBackup);

        this.setLayout(new BorderLayout());
        this.add(pBackup,BorderLayout.CENTER);

        addListener();
    }

    private void addListener(){
        RecoverListener listener = new RecoverListener();

        bBackup.addActionListener(listener);
    }
}
