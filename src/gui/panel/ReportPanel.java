package gui.panel;

import entity.Record;
import service.ReportService;
import util.ChartUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReportPanel extends WorkingPanel {
    public static ReportPanel instance = new ReportPanel();

    JLabel l = new JLabel();

    public ReportPanel(){
        this.setLayout(new BorderLayout());
        List<Record> records = new ReportService().listThisMonthRecords();
        Image image = ChartUtil.getImage(records, 400, 400);
        ImageIcon icon = new ImageIcon(image);
        l.setIcon(icon);
        this.add(l);
        addListener();
    }

    public static void main(String[] args){
        GUIUtil.showPanel(ReportPanel.instance);
    }
    @Override
    public void updateData() {
        List<Record> records = new ReportService().listThisMonthRecords();
        Image image = ChartUtil.getImage(records, 350, 250);
        ImageIcon icon = new ImageIcon(image);
        l.setIcon(icon);
    }

    @Override
    public void addListener() {

    }
}
