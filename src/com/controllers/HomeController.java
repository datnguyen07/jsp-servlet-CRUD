package com.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DirectoryBookDAO;
import com.models.DirectoryBook;

@WebServlet("/home")
public class HomeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DirectoryBookDAO dir = new DirectoryBookDAO();
		String id = request.getParameter("sid");
		if(!(id == null || id.equals(""))) {
			DirectoryBook d = dir.findId(id);
			request.setAttribute("dir", d);
		}
		int count = dir.countAll();
		int pageSize = 5;
		int endPage = 0;
		endPage = count / pageSize;
		if(count % pageSize != 0) {
			endPage++;
		}
		List<DirectoryBook> data = dir.search("", 1, pageSize);
		List<Integer> id_rank = dir.searchIdRank("", 1, pageSize);
		request.setAttribute("list", data);
		request.setAttribute("id_rank", id_rank);
		request.setAttribute("end", endPage);
		String txtSearch = request.getParameter("txtSearch");
		if(txtSearch != null) {
			try {
				String indexString = request.getParameter("index");
				if(indexString==null) {
					indexString="1";
				}
				int index = Integer.parseInt(indexString);
				DirectoryBookDAO dire = new DirectoryBookDAO();
				count = dire.count(txtSearch);
				endPage = 0;
				endPage = count / pageSize;
				if(count % pageSize != 0) {
					endPage++;
				}
				List<DirectoryBook> listSearch = dir.search(txtSearch, index, pageSize);
				id_rank = dir.searchIdRank(txtSearch, index, pageSize);
				request.setAttribute("end", endPage);
				request.setAttribute("id_rank", id_rank);
				request.setAttribute("txtSearch", txtSearch);
				request.setAttribute("list", listSearch);
				
				} catch(Exception e) {
				
			}
		}
		
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		DirectoryBookDAO dir = new DirectoryBookDAO();
		if(id == null || id.equals("")) {
			dir.save(new DirectoryBook(address, phone, email));
		} else {
			dir.update(new DirectoryBook(address, phone, email), id);
		}
		
		response.sendRedirect(request.getContextPath() + "/home");
	
	}
}