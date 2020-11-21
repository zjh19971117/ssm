package service.impl;

import dao.CatalogDao;
import dao.impl.CatalogDaoImpl;
import entity.Catalog;
import service.CatalogService;

import java.util.List;

public class CatalogServiceImpl implements CatalogService {
    private CatalogDao catalogDao = new CatalogDaoImpl();
    @Override
    public List<Catalog> catalog(Integer id) {
        return catalogDao.catalog(id);
    }

    @Override
    public List<Catalog> load(Integer id) {
        return catalogDao.selectAll(id);
    }

    @Override
    public int update(int c1, int gid) {
        return catalogDao.update(c1,gid);
    }


}
