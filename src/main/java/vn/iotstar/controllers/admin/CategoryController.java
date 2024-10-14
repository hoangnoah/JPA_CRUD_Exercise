package vn.iotstar.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Category;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.impl.CategoryService;
import static vn.iotstar.utils.Constant.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search", })
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryService();

	public CategoryController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Xem, Xóa, Tìm kiếm chỉ có ở doget
		String url = request.getRequestURL().toString();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		if (url.contains("categories")) {
			List<Category> list = cateService.findAll();
			request.setAttribute("listcate", list);
			request.getRequestDispatcher("/views/admin/category-list.jsp").forward(request, response);
		} else if (url.contains("add")) {
			request.getRequestDispatcher("/views/admin/category-add.jsp").forward(request, response);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Category category = cateService.findById(id);

			request.setAttribute("cate", category);
			request.getRequestDispatcher("/views/admin/category-edit.jsp").forward(request, response);
		} else if (url.contains("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				cateService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/admin/categories");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		if (url.contains("insert")) {
			Category category = new Category();
			String catename = request.getParameter("categoryname");
			int status = Integer.parseInt(request.getParameter("status"));
			category.setCategoryname(catename);
			category.setStatus(status);
			String imagelink = request.getParameter("imagelink");

			String fname = "";
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = request.getPart("images");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// Đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// Upload file
					part.write(uploadPath + "/" + fname);
					// Ghi tên file vào data
					category.setImages(fname);
				} else if (imagelink != null) {
					category.setImages(imagelink);
				} else {
					category.setImages("avata.png");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			cateService.insert(category);
			response.sendRedirect(request.getContextPath() + "/admin/categories");
		} else if (url.contains("update")) {
			int categoryid = Integer.parseInt(request.getParameter("categoryid"));
			String catename = request.getParameter("categoryname");
			int status = Integer.parseInt(request.getParameter("status"));
			Category category = new Category();
			category.setCategoryid(categoryid);
			category.setCategoryname(catename);
			category.setStatus(status);

			// Lưu ảnh cũ
			Category cateold = cateService.findById(categoryid);
			String fileold = cateold.getImages();
			// Xử lí images
			String fname = "";
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = request.getPart("images");
				if (part.getSize() > 0) {
					if (!category.getImages().substring(0, 5).equals("https")) {
						deleteFile(uploadPath + "\\" + fileold);
					}

					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// Đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// Upload file
					part.write(uploadPath + "/" + fname);
					// Ghi tên file vào data
					category.setImages(fname);
				} else {
					category.setImages(fileold);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			cateService.update(category);
			response.sendRedirect(request.getContextPath() + "/admin/categories");
		}
	}

	public static void deleteFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		Files.delete(path);
	}

}
