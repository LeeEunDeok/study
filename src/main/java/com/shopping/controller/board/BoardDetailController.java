package com.shopping.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.bean.Board;
import com.shopping.model.dao.BoardDao;

public class BoardDetailController extends SuperClass {
	private final String PREFIX = "board/";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		BoardDao dao = new BoardDao();
		int no = Integer.parseInt(request.getParameter("no"));
		Board bean = dao.getDataBean(no);
		
		if (bean == null) {
			super.setAlertMessage("잘못된 게시물 정보 입니다.");
			super.goToPage(PREFIX + "boList.jsp");
			
		} else {
			// 현재 로그인한 사람이 작성한 글이 아니면 조회수를 1씩 증가시킵니다.
			String readhitUpdate = request.getParameter("readhitUpdate");
			if(readhitUpdate.equals("true")) {
				dao.updateReadhit(no);
			}
			
			System.out.println("다음은 페이징 처리와 관련된 파라미터입니다.");
			System.out.println("상세 보기 페이지에서 '돌아가기' 버튼 클릭 시 함께 넘겨 주도록 합니다.");
			
			String paramList = "&pageNumber=" + request.getParameter("pageNumber");
			paramList += "&pageSize=" + request.getParameter("pageSize");
			paramList += "&mode=" + request.getParameter("mode");
			paramList += "&keyword=" + request.getParameter("keyword");
			request.setAttribute("paramList", paramList);
			
			request.setAttribute("bean", bean);
			super.goToPage(PREFIX + "boDetail.jsp");
		}
	}
}
