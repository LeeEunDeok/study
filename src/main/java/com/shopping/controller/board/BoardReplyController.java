package com.shopping.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.bean.Board;
import com.shopping.model.dao.BoardDao;

public class BoardReplyController extends SuperClass {
	private final String PREFIX = "board/";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		super.goToPage(PREFIX + "boInsertForm.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		BoardDao dao = new BoardDao();
		Board bean = new Board();
		
		/* 다음 컬럼들은 고려하지 않아도 됩니다.
		 * no : 시퀀스로 처리함
		 * readhit, regdate, groupno, orderno, detph : 기본값으로 처리함
		 * remark : 게시물 삭제 시 '비고' 항목을 작성하기 위한 컬럼 
		 */
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");
		
		bean.setId(id);
		bean.setPassword(password);
		bean.setSubject(subject);
		bean.setContents(contents);
		
		
		int cnt = -1;
		cnt = dao.insertData(bean);
		String message = "";
		
		if(cnt == 1){ // 인서트 성공
			new BoardListController().doGet(request, response);
		}else{ // 인서트 실패
			new BoardInsertController().doGet(request, response);
		}
		
		
		super.goToPage(PREFIX + "boInsertForm.jsp");
	}
}