package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.PageBean;
import entity.Users;
import factory.Factory;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao uo = Factory.getImpl(UserDaoImpl.class);
    @Override
    public List<Users> selectAll(String search,int page, int rowsPerPage) {
        return uo.selectAll(search,page,rowsPerPage);
    }

    @Override
    public Users selectOne(int id) {
        return uo.selectOne(id);
    }

    @Override
    public boolean update(Users u) {
        return uo.update(u)>0;
    }

    @Override
    public boolean delete(int id) {
        return uo.delete(id)>0;
    }

    @Override
    public boolean addUser(Users u) {
        return uo.addUser(u)>0;
    }

    @Override
    public int getCount(String search) {
        return uo.getCount(search);
    }

    @Override
    public Users select(int id,String... param) {
      return uo.select(id, param);
    }

    @Override
    public Users check1(String name) {
        return uo.check1(name);
    }


}


