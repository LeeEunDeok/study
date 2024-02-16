package com.shopping.controller.mall;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.bean.Order;
import com.shopping.model.dao.MallDao;
import com.shopping.model.mall.CartItem;

public class MallDetailController extends SuperClass {
	@Override // 과거 구매 내역 1건에 대한 세부 정보를 보여줍니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		if(super.logInfo == null) {
			super.youNeededLogin();
			return;
			
		}else {
			int oid = Integer.parseInt(request.getParameter("oid")); // 송장 번호
			
			MallDao dao = new MallDao();
			
			Map<String, Object> mapList = dao.getDetailHistory(oid);
			
			Order order = null; // 주문 정보
			List<CartItem> lists = null; // 쇼핑 정보
			
			for(String key : mapList.keySet()) {
				if(key.equals("order")) {
					order = (Order) mapList.get(key); // 강등
				}else if(key.equals("lists")){
					lists = (List<CartItem>) mapList.get(key); // 강등
				}
			}
			
			request.setAttribute("order", order);
			request.setAttribute("lists", lists);
			
			super.goToPage("mall/maDetail.jsp");
		}
	}
}
