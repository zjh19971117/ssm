package dao.impl;

import dao.UserDao;
import entity.PageBean;
import entity.Users;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.DBtools;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private QueryRunner qr = new QueryRunner(DBtools.getDataSource());
//    private UserDao uo = Factory.getImpl(UserDaoImpl.class);
    @Override
    public List<Users> selectAll(String search, int page,int rowsPerPage) {
        StringBuffer sql =new StringBuffer("select * from users");
        if(search != null){
            sql.append(" where uname like ?");
        }
        sql.append(" limit ?,?");
        try {
            if(search!=null)
                return qr.query(sql.toString(),new BeanListHandler<Users>(Users.class),"%"+search+"%",(page-1)*rowsPerPage,rowsPerPage);
            return qr.query(sql.toString(),new BeanListHandler<Users>(Users.class),(page - 1) * rowsPerPage,rowsPerPage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Users selectOne(int id) {
        String sql = "select * from users where uid=?";
        try {
            return qr.query(sql,new BeanHandler<Users>(Users.class),id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(Users u) {
        String sql= "update users set uname=?,uemail=?,ugender=? where uid=?";
        try {
            return qr.update(sql,u.getUname(),u.getUemail(),u.getUgender(),u.getUid());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        String sql = "delete from users where uid=?";
        try {
            return qr.update(sql,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int addUser(Users u) {
        String sql = "insert into users(uid,uname,upass,uemail,ugender) values(?,?,?,?,?)";
        try {
            return qr.update(sql,u.getUid(),u.getUname(),u.getUpass(),u.getUemail(),u.getUgender());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getCount(String search) {
        try {
        String sql = "select count(*) from users";
        Long res = 0L;
        if(search!=null){
            sql+=" where uname like ?";
            res=qr.query(sql,new ScalarHandler<>(),"%"+search+"%");
            return res.intValue();
        }
            res = qr.query(sql,new ScalarHandler<>());
            return res.intValue();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public Users select(int id,String... param) {
        try {
            StringBuffer sql = new StringBuffer("select * from users");
            if (param != null && param.length == 2) {
                sql.append(" where uname=? and upass=? ");
                return qr.query(sql.toString(), new BeanHandler<Users>(Users.class), param[0], param[1]);
            }
            sql.append(" where id=? ");

            return qr.query(sql.toString(), new BeanHandler<Users>(Users.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Users check1(String name) {
        String sql="select * from users where uname=?";
        try {
            return qr.query(sql,new BeanHandler<Users>(Users.class),name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return null;
    }


}
