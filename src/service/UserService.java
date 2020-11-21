package service;

import entity.PageBean;
import entity.Users;

import java.util.List;

public interface UserService {
    List<Users> selectAll(String search,int page,int rowsPerPage);

    Users selectOne(int id);

    boolean update(Users u);

    boolean delete(int id);

    boolean addUser(Users u);

    int getCount(String search);

    Users select(int id,String...param);

    Users check1(String name);
}
