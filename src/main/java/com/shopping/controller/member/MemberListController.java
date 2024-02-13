package com.shopping.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.bean.Member;
import com.shopping.model.bean.FillItem;
import com.shopping.model.dao.MemberDao;
import com.shopping.model.dao.FillItemDao;
import com.shopping.utility.Paging;

public class MemberListController extends SuperClass {
	private final String PREFIX = "member/";
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		// 페이징 처리를 위한 파라미터 목록들
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		// 영문으로 되어 있는 취미를 한글로 바꾸기 위해 필요합니다.
		FillItemDao fdao = new FillItemDao();
		String module = "members";
		String field = "hobby";
		List<FillItem> hobbyList = fdao.getDataList(module, field);
		
		MemberDao dao = new MemberDao();
		
		int totalCount = dao.getTotalRecordCount("members", mode, keyword);
		String url = super.getUrlInformation("meList");
		boolean isGrid = false;
		
		Paging paging = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
		
		
		List<Member> dataList = dao.getDataList(paging);
		
		request.setAttribute("hobbyList", hobbyList);
		request.setAttribute("paging", paging); // 페이징 객체도 바인딩
		request.setAttribute("dataList", dataList);
		
		super.goToPage(PREFIX + "meList.jsp");
	}
}
