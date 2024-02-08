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
		
		// 답글의 깊이를 제한합니다.
		Integer depth = Integer.parseInt(request.getParameter("depth"));
		
		// MAX_DEPTH 변수는 서블릿의 초기화 파라미터를 이용하여 전역적으로 처리할 수도 있습니다.
		final Integer MAX_DEPTH = 3; // 최대 글의 깊이
		
		if(depth == MAX_DEPTH) {
			String message = "답글의 깊이는 최대" + MAX_DEPTH + "까지 입니다.";
			super.setAlertMessage(message);
			new BoardListController().doGet(request, response);
			return;
		}
		
		final Integer MAX_GROUPNO_COUNT = 5; // 동일 그룹의 최대 허용 행(row) 개수
		
		BoardDao dao = new BoardDao();
		Integer groupno = Integer.parseInt(request.getParameter("groupno"));
		Integer replyCount = 0; // 총 답글의 개수
		replyCount = dao.getReplyCount(groupno);
		
		if(replyCount >= MAX_GROUPNO_COUNT) {
			String message = "최대 답글 개수 " + MAX_GROUPNO_COUNT + "를 초과하였습니다.";
			super.setAlertMessage(message);
			new BoardListController().doGet(request, response);
			
		}else {
			super.goToPage(PREFIX + "boReplyForm.jsp");
		}
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		
		/* 다음 컬럼들은 고려하지 않아도 됩니다.
		 * no : 시퀀스로 처리함
		 * readhit, regdate, groupno, orderno, detph : 기본값으로 처리함
		 * remark : 게시물 삭제 시 '비고' 항목을 작성하기 위한 컬럼 
		 */
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");
		
		// 답글을 위해 다음 파라미터는 꼭 챙겨야 합니다.
		Integer groupno = Integer.parseInt(request.getParameter("groupno"));
		Integer orderno = Integer.parseInt(request.getParameter("orderno"));
		Integer depth = Integer.parseInt(request.getParameter("depth"));
		
		Board bean = new Board();
		
		bean.setId(id);
		bean.setPassword(password);
		bean.setSubject(subject);
		bean.setContents(contents);
		
		bean.setGroupno(groupno); // 원글의 그룹 번호를 그래도 사용합니다.
		bean.setOrderno(orderno+1); // 순서 번호는 기존 값에 1을 증가시킵니다.
		bean.setDepth(depth+1); // 글의 깊이는 기존 값에 1을 증가시킵니다ㅏ.
		
		BoardDao dao = new BoardDao();
		
		int cnt = -1;
		cnt = dao.replyData(bean, orderno);
		
		if(cnt == 1){ // 답글 작성 성공
			new BoardListController().doGet(request, response);
		}else{ // 답글 작성 실패
			new BoardReplyController().doGet(request, response);
		}
		
	}
}