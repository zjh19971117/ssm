package service;

import entity.Catalog;

import java.util.List;

public interface CatalogService {
    List<Catalog> catalog(Integer id);

    List<Catalog> load(Integer id);

   int update(int c1, int gid);
}
