package vn.iotstar.config;

import jakarta.persistence.*;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;
import lombok.*;

public class Test {
	public static void main(String[] args) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		Category cate = new Category();
		cate.setCategoryname("Iphone");
		cate.setImages("abc.jpg");
		cate.setStatus(1);
		Video video = new Video();
		video.setVideoId("v01");
		video.setTitle("test");
		video.setCategory(cate);
		try {
			trans.begin();
			enma.persist(cate);
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
}