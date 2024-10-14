package vn.iotstar.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.IVideoService;
import vn.iotstar.services.impl.CategoryService;
import vn.iotstar.services.impl.VideoService;
import static vn.iotstar.utils.Constant.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig()
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/add", "/admin/video/insert", "/admin/video/edit",
		"/admin/video/update", "/admin/video/delete" })
public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public IVideoService videoService = new VideoService();
	public ICategoryService cateService = new CategoryService();
      
    public VideoController() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();

		if (url.contains("/admin/videos")) {
			String searchName = req.getParameter("search");

			List<Video> list;
			if (searchName != null && !searchName.isBlank())
				list = videoService.searchByTitle(searchName);
			else
				list = videoService.findAll();
			req.setAttribute("videos", list);
			req.getRequestDispatcher("/views/admin/videos.jsp").forward(req, resp);

		} else if (url.contains("/admin/video/add")) {
			List<Category> categories = cateService.findAll();
			req.setAttribute("categories", categories);
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);

		} else if (url.contains("/admin/video/edit")) {
			String id = req.getParameter("id");
			Video video = videoService.findById(id);
			List<Category> categories = cateService.findAll();
			req.setAttribute("video", video);
			req.setAttribute("categories", categories);
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);

		} else {
			String id = req.getParameter("id");
			try {
				videoService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();

		if (url.contains("/admin/video/insert")) {
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			int active = Integer.parseInt(req.getParameter("active"));
			int view = Integer.parseInt(req.getParameter("view"));
			String posterLink = req.getParameter("posterLink");
			int categoryId = Integer.parseInt(req.getParameter("categoryid"));

			Video video = new Video();
			video.setVideoId(id);
			video.setTitle(title);
			video.setActive(active);
			video.setDescription(description);
			video.setViews(view);
			video.setCategory(cateService.findById(categoryId));

			String fname = "";
			String uploadPath = UPLOAD_DIRECTORY; 
			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists())
				uploadDir.mkdir();
			try {

				Part part = req.getPart("posterUpload");

				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + fname);
					video.setPoster(fname);

				} else if (posterLink != null && !posterLink.isEmpty()) {
					video.setPoster(posterLink);

				} else {
					video.setPoster("dfVideoPoster.png");
				}

			} catch (FileNotFoundException fne) {
				fne.printStackTrace();
			}

			videoService.insert(video);

			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}

		if (url.contains("/admin/video/update")) {
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			int active = Integer.parseInt(req.getParameter("active"));
			int view = Integer.parseInt(req.getParameter("view"));
			String posterLink = req.getParameter("posterLink");
			int categoryId = Integer.parseInt(req.getParameter("categoryid"));

			Video video = videoService.findById(id);
			String oldPoster = video.getPoster();

			video.setTitle(title);
			video.setDescription(description);
			video.setActive(active);
			video.setViews(view);
			video.setCategory(cateService.findById(categoryId));

			String fname = "";
			String uploadPath = UPLOAD_DIRECTORY; 

			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try {
				Part part = req.getPart("posterUpload");
				if (part.getSize() > 0) {
					// xóa file cũ trên thư mục
					if (!oldPoster.substring(0, 5).equals("https")) {
						deleteFile(uploadPath + "\\" + oldPoster);
					}

					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + fname);
					video.setPoster(fname);

				} else if (posterLink != null && !posterLink.isEmpty()) {
					video.setPoster(posterLink);

				} else {
					video.setPoster("dfVideoPoster.png");

				}

			} catch (FileNotFoundException fne) {
				fne.printStackTrace();
			}

			videoService.update(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		} else {
			String id = req.getParameter("id");
			try {
				videoService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
	}
	
	public static void deleteFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		Files.delete(path);
	}

}
