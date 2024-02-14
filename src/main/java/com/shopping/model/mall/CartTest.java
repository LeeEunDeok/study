package com.shopping.model.mall;

import java.util.Map;
import java.util.Scanner;

// CartManager 클래스를 위한 한시적 테스트용 클래스입니다.
public class CartTest {
	public static void main(String[] args) {
		CartManager manager = new CartManager(); // 카트 관리자 객체
		CartMap cartMap = new CartMap();
		
		System.out.println("매장 내에 어떠한 품목들이 있나요?");
		Map<Integer, CartItem> itemList = cartMap.getItemList();
		for(Integer key : itemList.keySet()) {
			CartItem item = itemList.get(key);
			System.out.println(item);
		}
		System.out.println("--------------------------------");
		
		Scanner scan = new Scanner(System.in);
		int pnum = 0, qty = 0; // 상품 번호와 구매 수량을 위한 변수
		
		while(true) {
			System.out.println("\n1.카트 목록, 2.추가, 3.삭제, 4.수정, 5.개수 확인, 6.비우기, 0.프로그램 종료");
			int menu = scan.nextInt();
			
			switch (menu) {
			case 1:
				System.out.println("구매 수량 정보");
				Map<Integer, Integer> cartList =  manager.getAllCartList();
				System.out.println(cartList);
				break;
				
			case 2:
				System.out.print("추가할 상품 번호 입력 : ");
				pnum = scan.nextInt();
				
				System.out.print("구매 수량 입력 : ");
				qty = scan.nextInt();
				manager.addCart(pnum, qty);
				break;
				
			case 3:
				System.out.print("삭제할 상품 번호 입력 : ");
				pnum = scan.nextInt();
				manager.deleteCart(pnum);
				break;
				
			case 4:
				System.out.println("이전 개수를 덮어쓰기 합니다.");
				System.out.print("수정할 상품 번호 입력 : ");
				pnum = scan.nextInt();
				System.out.print("구매 수량 입력 : ");
				qty = scan.nextInt();
				manager.editCart(pnum, qty);
				break;
				
			case 5:
				int size = manager.getCartItemSize();
				System.out.println("구매할 품목 개수 : " + size);
				break;
				
			case 6:
				System.out.println("모든 품목을 카트에서 비웁니다.");
				manager.removeAllCart();
				break;
				
			case 0:
				System.out.println("프로그램 종료");
				System.exit(0); // 정상적으로 종료가 되었음을 운영체제에 알립니다.
				break;
			}
		}
	}
}
