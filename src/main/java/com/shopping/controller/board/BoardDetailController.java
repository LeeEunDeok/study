package com.shopping.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.bean.Board;
import com.shopping.model.bean.Emoticon;
import com.shopping.model.dao.BoardDao;
import com.shopping.model.dao.EmoticonDao;

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
			
			// 조회수 +1 정보가 없으면, 기본값은 "false"로 지정합니다.
			// 즉, 조회수를 +1 시키지 않겠습니다.
			if(readhitUpdate == null) {readhitUpdate = "false";}
			
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
			
			// 이모티콘 정보 읽어오기
			EmoticonDao edao = new EmoticonDao();
			Emoticon emoticon = edao.getEmoticon(no);
			
			// 이모티콘 정보가 없는 경우, 신규 emoticon 객체를 생성해줍니다.
			if(emoticon == null) {emoticon = new Emoticon();}
			
			// 상세 보기 페이지에서 보여주기 위해 바인딩합니다.
			request.setAttribute("emoticon", emoticon);
			
			request.setAttribute("bean", bean);
			super.goToPage(PREFIX + "boDetail.jsp");
		}
	}
}
