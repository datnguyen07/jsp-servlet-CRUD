package com.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DirectoryBookDAO;
import com.dao.imageBookDAO;
import com.models.DirectoryBook;
import com.models.imageBook;

@WebServlet("/image")
public class imageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public imageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		imageBookDAO im = new imageBookDAO();
		String id = request.getParameter("aid");
		String ids = request.getParameter("sid");
		if(!(ids == null || ids.equals(""))) {
			imageBook d = im.findId(id);
			request.setAttribute("dir", d);
		}
		List<imageBook> listImage = im.findByDirectoryBookId(id);
		request.setAttribute("listImage", listImage);
		request.getRequestDispatcher("/views/image.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String directorybook_id = request.getParameter("directorybook_id");
		String image = request.getParameter("image");
		imageBookDAO im = new imageBookDAO();
		if(id == null || id.equals("")) {
			im.save(new imageBook(directorybook_id, image));
		} else {
			im.update(new imageBook(directorybook_id, image), id);
		}
		
		response.sendRedirect(request.getContextPath() + "/image?aid=" + directorybook_id);
	}

}
