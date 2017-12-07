package util;

import entity.Config;
import entity.Record;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordDAO {
    public int getTotal(){
        int total = 0;
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        String sql = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            sql = "select count(*) from record";

            rs = s.executeQuery(sql);
            while (rs.next()){
                total = rs.getInt(1);
            }
            System.out.println("total:" + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(Record record){
        Connection c = null;
        PreparedStatement ps = null;
        String sql = null;

        try {
            c = DBUtil.getConnection();
            sql = "insert into record values(null,?,?,?,?)";
            ps = c.prepareStatement(sql);
            ps.setInt(1,record.spend);
            ps.setInt(2,record.cid);
            ps.setString(3,record.comment);
            ps.setDate(4,DateUtil.util2sql(record.date));
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                record.id = id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Record record){
        Connection c = null;
        PreparedStatement ps = null;
        String sql = null;

        try {
            c = DBUtil.getConnection();
            sql = "update record set spend= ?, cid= ?, commend= ?, date= ?, where id = ?";
            ps = c.prepareStatement(sql);
            ps.setInt(1,record.spend);
            ps.setInt(2,record.cid);
            ps.setString(3,record.comment);
            ps.setDate(4,DateUtil.util2sql(record.date));
            ps.setInt(5,record.id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        Connection c = null;
        Statement s = null;
        String sql = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            sql = "delete from record where id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Record get(int id){
        Record record = null;
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        String sql = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            sql = "select * from record where id = " + id;
            rs = s.executeQuery(sql);

            if(rs.next()){
                record = new Record();
                int spend = rs.getInt("spend");
                int cid = rs.getInt("cid");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                record.spend = spend;
                record.cid = cid;
                record.comment = comment;
                record.date = date;
                record.id =id;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    public List<Record> list(){
        return list(0,Short.MAX_VALUE);
    }

    public List<Record> list(int start, int count){
        List<Record> records = new ArrayList<Record>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;

        try {
            c = DBUtil.getConnection();
            sql = "select * from record order by id desc limit ?,? ";
            ps = c.prepareStatement(sql);
            ps.setInt(1,start);
            ps.setInt(2,count);
            rs = ps.executeQuery();

            while (rs.next()){
                Record record = new Record();
                int id = rs.getInt("id");
                int spend = rs.getInt("spend");
                int cid = rs.getInt("cid");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                record.spend = spend;
                record.cid = cid;
                record.comment = comment;
                record.date = date;
                record.id = id;
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public List<Record> list(int cid){
        List<Record> records = new ArrayList<Record>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;

        try {
            c = DBUtil.getConnection();
            sql = "select * from record where cid = ?";
            ps = c.prepareStatement(sql);
            ps.setInt(1, cid);
            rs = ps.executeQuery();

            while (rs.next()){
                Record record = new Record();
                int id = rs.getInt("id");
                int spend = rs.getInt("spend");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                record.spend = spend;
                record.cid = cid;
                record.comment = comment;
                record.date = date;
                record.id = id;
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public List<Record> listToday(){
        return list(DateUtil.util2sql(DateUtil.today()));
    }

    public List<Record> list(Date day){
        List<Record> records = new ArrayList<Record>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;

        try {
            c = DBUtil.getConnection();
            sql = "select * from record where date = ?";
            ps = c.prepareStatement(sql);
            ps.setDate(1, DateUtil.util2sql(day));
            rs = ps.executeQuery();

            while (rs.next()){
                Record record = new Record();
                int id = rs.getInt("id");
                int spend = rs.getInt("spend");
                int cid = rs.getInt("cid");
                String comment = rs.getString("comment");
                record.spend = spend;
                record.cid = cid;
                record.comment = comment;
                record.date = day;
                record.id = id;
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public List<Record> listThisMonth(){
        return list(DateUtil.util2sql(DateUtil.monthBegin()),DateUtil.util2sql(DateUtil.monthEnd()));
    }

    public List<Record> list(Date start,Date end){
        List<Record> records = new ArrayList<Record>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;

        try {
            c = DBUtil.getConnection();
            sql = "select * from record where date >=? and date <= ?";
            ps = c.prepareStatement(sql);
            ps.setDate(1, DateUtil.util2sql(start));
            ps.setDate(2, DateUtil.util2sql(end));
            rs = ps.executeQuery();

            while (rs.next()){
                Record record = new Record();
                int id = rs.getInt("id");
                int spend = rs.getInt("spend");
                int cid = rs.getInt("cid");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                record.spend = spend;
                record.cid = cid;
                record.comment = comment;
                record.date = date;
                record.id = id;
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}
