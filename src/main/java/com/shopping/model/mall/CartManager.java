package com.shopping.model.mall;

import java.util.HashMap;
import java.util.Map;

// 매장 내의 나의 카트를 관리하기 위한 유틸리티 클래스 입니다.
public class CartManager {
	// carts : 나의 카트에 담겨 있는 상품들의 번호와 수량 정보
	// 카트의 품목은 <상품 번호, 구매 수량> 형식 입니다.
	private Map<Integer, Integer> carts = null;
	
	public CartManager() {
		this.carts = new HashMap<Integer, Integer>();
	}
	
	// 카트에 들어있는 품목의 개수를 반환합니다.
	public int getCartItemSize() {
		return this.carts.size();
	}
	
	// 카트에 상품을 추가합니다.
	public void addCart(int pnum, int qty) {
		if(this.carts.containsKey(pnum)) { // 이미 카트에 품목이 들어있는 경우
			int newQty = this.carts.get(pnum) + qty;
			this.carts.put(pnum, newQty);
			
		}else {
			this.carts.put(pnum, qty);
		}
	}
	
	// 카트 내의 상품 수량을 변경합니다.(덮어쓰기_overwirte)
	public void editCart(int pnum, int qty) {
		this.carts.put(pnum, qty);
	}
	
	// 카트 내의 특정 상품을 목록에서 제거합니다.
	public void deleteCart(int pnum) {
		this.carts.remove(pnum);
	}
	
	// 카트 내의 모든 상품을 목록에서 제거합니다.(결제 시 사용 예정)
	public void removeAllCart() {
		this.carts.clear();
	}
	
	// 카트 내의 모든 상품 목록을 반환합니다.
	public Map<Integer, Integer> getAllCartList(){
		return this.carts;
	}
}
