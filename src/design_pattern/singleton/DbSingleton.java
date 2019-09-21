package design_pattern.singleton;

import java.sql.Connection;


public class DbSingleton {

    private static volatile DbSingleton instance = null;
    private static volatile Connection conn = null;

    private DbSingleton() {
        if(instance != null) {
            // prevent from reflection
            throw new RuntimeException("Use getInstance() method to create");
        }
    }

    public static DbSingleton getInstance() {
        if(instance == null) {
            synchronized(DbSingleton.class) {
                if(instance == null) {
                    instance = new DbSingleton();
                }
            }
        }

        return instance;
    }


}
