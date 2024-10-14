package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Video;

public interface IVideoDao {

	List<Video> searchByTitle(String title);

	Video findByTitle(String title);

	List<Video> findAll();

	Video findById(String videoId);

	void delete(String videoId) throws Exception;

	void update(Video video);

	void insert(Video video);

}
