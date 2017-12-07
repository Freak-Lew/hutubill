package service;

import entity.Category;
import entity.Record;
import util.RecordDAO;

import java.util.Date;
import java.util.List;


public class RecordService {
    public static final String spend = "spend";
    public static final String cid = "cid";
    public static final String comment = "comment";
    public static final String date = "date";

    static RecordDAO dao = new RecordDAO();



    public void add(int spend, Category c, String comment, Date date){
        Record record = new Record();
        record.spend = spend;
        record.cid = c.id;
        record.comment = comment;
        record.date = date;
        dao.add(record);
    }
}
