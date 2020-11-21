package util;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.util.Properties;

public class DBtools {
     private static DruidDataSource dataSource;
     static{
          Properties p = new Properties();
          try {
               p.load(DBtools.class.getClassLoader().getResourceAsStream("db.properties"));
               dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(p);
          } catch (Exception e) {
               e.printStackTrace();
          }
     }
public static DruidDataSource getDataSource(){
          return dataSource;
     }

}

