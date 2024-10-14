package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Category;

public interface ICategoryDao {

	int count();

	List<Category> findAll(int page, int pagesize);

	List<Category> searchByName(String catname);

	Category findByCategoryname(String name) throws Exception;

	List<Category> findAll();

	Category findById(int cateId);

	void delete(int cateId) throws Exception;

	void update(Category category);

	void insert(Category category);

}
