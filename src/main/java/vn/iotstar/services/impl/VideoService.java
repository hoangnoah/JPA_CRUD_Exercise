package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.IVideoDao;
import vn.iotstar.dao.impl.VideoDao;
import vn.iotstar.entity.Video;
import vn.iotstar.services.IVideoService;

public class VideoService implements IVideoService {

	IVideoDao videoDao = new VideoDao();

	@Override
	public List<Video> searchByTitle(String title) {
		return videoDao.searchByTitle(title);
	}

	@Override
	public Video findByTitle(String title) {
		return videoDao.findByTitle(title);
	}

	@Override
	public List<Video> findAll() {
		return videoDao.findAll();
	}

	@Override
	public Video findById(String videoId) {
		return videoDao.findById(videoId);
	}

	@Override
	public void delete(String videoId) throws Exception {
		videoDao.delete(videoId);
	}

	@Override
	public void update(Video video) {
		videoDao.update(video);
	}

	@Override
	public void insert(Video video) {
		videoDao.insert(video);
	}
}
