package com.shopping.controller.mall;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.controller.product.ProductListController;
import com.shopping.model.dao.ProductDao;
import com.shopping.model.mall.CartItem;

// 나의 카트에 들어있는 품목을 보여줍니다.
public class MallListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		if(super.logInfo == null) {
			super.youNeededLogin();
			return;
		}
		
		int cartItemSize = super.mycart.getCartItemSize();
		System.out.println("카트 품목 개수 : " + cartItemSize);
		
		if(cartItemSize == 0) {
			String message = "카트 상품 목록이 존재하지 않아서 상품 목록 페이지로 이동합니다.";
			super.setAlertMessage(message);
			new ProductListController().doGet(request, response);
			
		}else {
			// cartItemList는 나의 카트 내의 품목 리스트 입니다.
			Map<Integer, Integer> cartItemList = super.mycart.getAllCartList();
			
			// cartWishList는 카트에 담은 품목들의 세부 정보를 저장하고 있는 컬렉션 입니다.
			List<CartItem> cartWishList = new ArrayList<CartItem>();
			
			ProductDao dao = new ProductDao();
			
			for(Integer pnum : cartItemList.keySet()) { // pnum은 상품 번호
				Integer qty = cartItemList.get(pnum); // 구매 수량
				
				CartItem item = dao.getCartItem(pnum, qty);
				cartWishList.add(item);
			}
			
			// session 영역에 cartWishList 정보를 바인딩합니다.
			super.session.setAttribute("cartWishList", cartWishList);
			super.goToPage("mall/maList.jsp");
		}
		
	}
}
