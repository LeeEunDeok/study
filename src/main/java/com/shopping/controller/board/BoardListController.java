package com.shopping.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.bean.Board;
import com.shopping.model.dao.BoardDao;
import com.shopping.utility.Paging;

public class BoardListController extends SuperClass {
	private final String PREFIX = "board/";
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		//페이징 처리를 위한 파라미터 목록들
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		BoardDao dao = new BoardDao();
		
		int totalCount = dao.getTotalRecordCount("boards", mode, keyword);
		String url = super.getUrlInformation("boList");
		boolean isGrid = false;
		
		Paging paging = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
		
		
		List<Board> dataList = dao.getDataList(paging);
		
		request.setAttribute("paging", paging); // 페이징 객체도 바인딩
		request.setAttribute("dataList", dataList);
		
		super.goToPage(PREFIX + "boList.jsp");
	}
}
