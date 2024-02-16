package com.shopping.controller.mall;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.controller.product.ProductListController;
import com.shopping.model.bean.Order;
import com.shopping.model.dao.MallDao;

public class MallHistoryController extends SuperClass {
	@Override // 과거 구매했던 나의 쇼핑 내역들을 최근 날짜부터 보여줍니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		if(super.logInfo == null) {
			super.youNeededLogin();
			return;
		}else {
			MallDao dao = new MallDao();
			
			List<Order> orderList = dao.getHistory(super.logInfo.getId());
			
			if(orderList.size() == 0) {
				String message = "결제 내역이 존재하지 않습니다.";
				super.setAlertMessage(message);
				new ProductListController().doGet(request, response);
				
			}else {
				request.setAttribute("orderList", orderList);
				super.goToPage("mall/maHistory.jsp");
			}
		}
	}
}
