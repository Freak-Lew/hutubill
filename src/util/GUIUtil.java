package util;

import javax.swing.*;
import java.awt.*;

public class GUIUtil {
    public static void setImageIcon(JButton b, String fileName, String tip) {
        ImageIcon i = new ImageIcon("img/"+fileName);
        b.setIcon(i);
        b.setPreferredSize(new Dimension(61, 71));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }

    public static void setColor(Color color, JComponent... cs) {
        for (JComponent c : cs) {
            c.setForeground(color);
        }
    }

    public static boolean checkNumber(JTextField tf, String Input){
        String text = tf.getText().trim();
            for (int i = 0; i < text.length(); i++){
                if (!Character.isDigit(text.charAt(i))){
                    JOptionPane.showMessageDialog(null, Input + "必须输入数字");
                    tf.grabFocus();
                    return false;
                }
            }
            return true;
    }

    public static boolean checkZero(JTextField tf, String Input){
        String text = tf.getText().trim();
        if(text.equals("0")){
            JOptionPane.showMessageDialog(null, Input + "不能为零");
            tf.grabFocus();
            return false;
        }
        return true;
    }

    public static boolean checkEmpty(JTextField tf, String Input) {
        String text = tf.getText().trim();
        if(0 == text.length()){
            JOptionPane.showMessageDialog(null, Input + "不能为空");
            tf.grabFocus();
            return false;
        }
        return true;
    }

    public static void showPanel(JPanel p){
        showPanel(p, 0.85);
    }

    public static void showPanel(JPanel p,double strechRate){
        GUIUtil.useLNF();
        JFrame f = new JFrame();
        f.setSize(500,450);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(strechRate);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        cp.show(p);
    }

    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
