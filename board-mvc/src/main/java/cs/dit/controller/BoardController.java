package cs.dit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs.dit.service.BDeleteService;
import cs.dit.service.BInsertService;
import cs.dit.service.BListService;
import cs.dit.service.BSelectOneService;
import cs.dit.service.BUpdateService;
import cs.dit.service.BoardService;

@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/")+1, uri.indexOf(".do"));
		String viewPage = null;
		BoardService service = null;
		
		switch(com.trim()) {
			case "index":
				viewPage = "WEB-INF/view/index.jsp";
				break;
			case "insertForm":
				viewPage = "WEB-INF/view/insertForm.jsp";
				break;
			case "updateForm":
				service = new BSelectOneService();
				viewPage = "WEB-INF/view/updateForm.jsp";
				break;
			case "insert":
				service = new BInsertService();
				service.excute(request, response);
				viewPage = "list.do";
				break;
			case "delete":
				service = new BDeleteService();
				service.excute(request, response);
				viewPage = "list.do";
				break;
			case "update":
				service = new BUpdateService();
				service.excute(request, response);
				viewPage = "list.do";
				break;
			case "list":
				service = new BListService();
				service.excute(request, response);
				viewPage = "WEB-INF/view/list.jsp";
				break;
			
		}
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
