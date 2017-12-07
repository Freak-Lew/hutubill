package service;

import entity.Config;
import util.ConfigDAO;

import java.util.List;

public class ConfigService {
    public static final String budget = "budget";
    public static final String mysqlPath = "mysqlPath";
    public static final String default_budget = "500";

    static ConfigDAO dao = new ConfigDAO();

    static{
        init();
    }

    public static void init(){
        init(budget,default_budget);
        init(mysqlPath,"");
    }

    private static void init(String key, String value){
        Config config = dao.getByKey(key);
        if(config == null){
            Config c = new Config();
            c.setKey(key);
            c.setValue(value);
            dao.add(c);
        }
    }

    public List<Config> list(){
        List<Config> configs = new ConfigService().list();
        return configs;
    }

    public void update(String key, String value){
        Config config = dao.getByKey(key);
        if(!config.getValue().equals(value)){
            config.setValue(value);
            dao.update(config);
        }
    }

    public static String get(String key){
        Config config = dao.getByKey(key);
        return config.getValue();
    }

    public static int getIntBudget(){
        return Integer.parseInt(get("budget"));
    }
}
