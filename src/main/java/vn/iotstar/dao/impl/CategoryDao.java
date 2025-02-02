package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.config.JpaConfig;
import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.entity.Category;

public class CategoryDao implements ICategoryDao{
	@Override
	public void insert(Category category) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Category category) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void delete(int cateId) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			Category cate = enma.find(Category.class, cateId);
			if (cate != null)
				enma.remove(cate);
			else
				throw new Exception("Không tìm thấy");

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public Category findById(int cateId) {
		EntityManager enma = JpaConfig.getEntityManager();
		return enma.find(Category.class, cateId);
	}

	@Override
	public List<Category> findAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		return query.getResultList();
	}

	@Override
	public Category findByCategoryname(String name) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Category c WHERE c.categoryname =:catename";
		try {
			TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
			query.setParameter("catename", name);
			Category category = query.getSingleResult();
			if (category == null) {
				throw new Exception("Category Name đã tồn tại");
			}
			return category;
		} finally {
			enma.close();
		}
	}

	@Override
	public List<Category> searchByName(String catname) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Category c WHERE c.catename like :catname";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter("catename", "%" + catname + "%");
		return query.getResultList();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public int count() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM Category c";
		Query query = enma.createQuery(jpql);
		return ((Long) query.getSingleResult()).intValue();
	}
}
