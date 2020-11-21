package dao;

import entity.Catalog;

import java.util.List;

public interface CatalogDao {
    List<Catalog> catalog(Integer id);
    List<Catalog> selectAll(Integer id);
    int update(int c1, int gid);
}
