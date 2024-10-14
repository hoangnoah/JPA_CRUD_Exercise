package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.config.JpaConfig;
import vn.iotstar.dao.IVideoDao;
import vn.iotstar.entity.Video;

public class VideoDao implements IVideoDao{
	
	@Override
	public void insert(Video video) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			enma.persist(video);
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
	public void update(Video video) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			enma.merge(video);
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
	public void delete(String videoId) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			Video video = enma.find(Video.class, videoId);
			if (video != null)
				enma.remove(video);
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
	public Video findById(String videoId) {
		EntityManager enma = JpaConfig.getEntityManager();
		Video video = enma.find(Video.class, videoId);
		return video;
	}
	
	@Override
	public List<Video> findAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		return query.getResultList();
	}
	
	@Override
	public Video findByTitle(String title) {
		EntityManager enma = JpaConfig.getEntityManager();
		String sql = "SELECT v FROM Video v WHERE v.title like =:videoTitle";
		try {
			TypedQuery<Video> query = enma.createQuery(sql, Video.class);
			query.setParameter("videoTitle", title);
			return query.getSingleResult();
		} finally {
			enma.close();
		}
	}
	
	@Override
	public List<Video> searchByTitle(String title) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Video c WHERE c.title like :videoTitle";
		TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
		query.setParameter("videoTitle", "%" + title + "%");
		return query.getResultList();
	}
}
