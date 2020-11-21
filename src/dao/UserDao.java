package dao;

import entity.PageBean;
import entity.Users;

import java.util.List;

public interface UserDao {
    List<Users> selectAll(String search,int page,int rowsPerPage);
    Users selectOne(int id);
    int update (Users u);
    int delete(int id);
    int addUser(Users u);
    int getCount(String search);
    Users select(int id,String... param);
    Users check1(String name);
}
