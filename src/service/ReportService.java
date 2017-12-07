package service;

import entity.Record;
import util.RecordDAO;

import java.util.List;

public class ReportService {
    RecordDAO dao = new RecordDAO();

    public List<Record> listThisMonthRecords(){
        return dao.listThisMonth();
    }
}
