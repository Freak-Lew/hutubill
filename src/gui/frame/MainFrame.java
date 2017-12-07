package gui.frame;

import gui.panel.MainPanel;

import javax.swing.*;

public class MainFrame extends JFrame {
    public static MainFrame instance = new MainFrame();

    private MainFrame(){
        this.setTitle("一本糊涂账");
        this.setSize(500,450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(MainPanel.instance);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] agrs){
        instance.setVisible(true);
    }
}
