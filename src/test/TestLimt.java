package test;

import dao.CatalogDao;
import dao.GoodsDao;
import dao.UserDao;
import dao.impl.CatalogDaoImpl;
import dao.impl.GoodsDaoImpl;
import dao.impl.UserDaoImpl;
import entity.PageBean;
import entity.Users;
import factory.Factory;
import org.junit.Test;
import service.UserService;
import service.impl.UserServiceImpl;

public class TestLimt {
    private GoodsDao go = new GoodsDaoImpl();
    private UserDao uo = Factory.getImpl(UserDaoImpl.class);
    private CatalogDao co = new CatalogDaoImpl();
//    private UserDao uo = new UserDaoImpl();
    private UserService userService = new UserServiceImpl();
    @Test
    public void selectOne(){
        System.out.println(uo.selectOne(1));
    }
    @Test
    public void update(){
        Users u = new Users(1,"傻逼","1.@","1");
        System.out.println(uo.update(u));
    }
    @Test
    public void delete(){
        System.out.println(uo.delete(7));
    }
    @Test
   public void add(){
        Users u = new Users(2,"2","2","2","2");
        System.out.println(uo.addUser(u));
    }
@Test
    public void limit(){
        PageBean p = new PageBean();
}
@Test
    public void go(){
    System.out.println(go.selectAll(1,5));
}
@Test
    public void co(){
    System.out.println(uo.selectAll("1",1,5));
}
@Test
    public void catalog(){
    System.out.println(co.catalog(null));
}
@Test
public void check1(){
    System.out.println(userService.check1("zhangsan"));
}
@Test
    public void nj(){
    System.out.println(co.update(1,1));
}
@Test
    public void mm(){
    System.out.println(go.updateC2(1,12));
}
}
