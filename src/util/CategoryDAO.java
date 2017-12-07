package util;

import entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public int getTotal(){
        int total = 0;
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        String sql = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            sql = "select count(*) from category";

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

    public void add(Category category) {

        String sql = "insert into category values(null,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, category.name);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                category.id = id;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void update(Category category){
        Connection c = null;
        PreparedStatement ps = null;
        String sql = null;

        try {
            c = DBUtil.getConnection();
            sql = "update category set name = ? where id = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1,category.name);
            ps.setInt(2,category.id);
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
            sql = "delete from category where id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category get(int id){
        Category category = null;
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        String sql = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            sql = "select * from category where id = " + id;
            rs = s.executeQuery(sql);

            if(rs.next()){
                category = new Category();
                String name = rs.getString("name");
                category.name = name;
                category.id = id;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public List<Category> list(){
        return list(0,Short.MAX_VALUE);
    }

    public List<Category> list(int start, int count){
        List<Category> categories = new ArrayList<Category>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;

        try {
            c = DBUtil.getConnection();
            sql = "select * from category order by id desc limit ?,? ";
            ps = c.prepareStatement(sql);
            ps.setInt(1,start);
            ps.setInt(2,count);
            rs = ps.executeQuery();

            while (rs.next()){
                Category category = new Category();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                category.name = name;
                category.id = id;
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

}
