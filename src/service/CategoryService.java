package service;

import entity.Category;
import entity.Record;
import util.CategoryDAO;
import util.RecordDAO;

import java.util.Iterator;
import java.util.List;

public class CategoryService {

    CategoryDAO categoryDAO = new CategoryDAO();
    RecordDAO  recordDAO = new RecordDAO();

    public List<Category> list(){
        List<Category> categories = categoryDAO.list();
        for(Category c : categories){
            List<Record> records = recordDAO.list(c.id);
            c.recordNumber = records.size();
        }
        return categories;
    }

    public void add(Category category){
        categoryDAO.add(category);
    }

    public void update(Category category){
        categoryDAO.update(category);
    }

    public void delete(int id){
        List<Category> categories = categoryDAO.list();
        for (Iterator it = categories.iterator(); it.hasNext();){
            Category category = (Category) it.next();
            if(category.id == id)
                categoryDAO.delete(id);
        }
    }
}
