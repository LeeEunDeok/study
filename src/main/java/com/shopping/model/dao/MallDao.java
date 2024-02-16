package com.shopping.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shopping.model.bean.Member;
import com.shopping.model.bean.Order;
import com.shopping.model.bean.WishList;
import com.shopping.model.mall.CartItem;

public class MallDao extends SuperDao {
	public Map<String, Object> getDetailHistory(int oid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		Map<String, Object> mapList = new HashMap<String, Object>();
		
		conn = super.getConnection();
		
		try {
			// step01 : 송장 번호에 대한 주문 정보 객체(Order)를 구합니다.
			Order order = null;
			sql = "select * from orders where oid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				order = this.makeOrderBean(rs);
			}
			if(rs != null) {rs.close();}
			if(pstmt != null) {pstmt.close();}
			
			// step02 : 송장 번호에 대한 상세 주문 내역을 컬렉션 List<CartItem>으로 구합니다.
			List<CartItem> lists = new ArrayList<CartItem>();
			sql = " select p.pnum, p.name pname, od.qty, p.price, p.point, p.image01 " ;
	        sql += " from (orders o inner join orderdetails od " ;
	        sql += " on o.oid = od.oid) inner join products p  " ;
	        sql += " on od.pnum = p.pnum and o.oid = ?  " ;
	        sql += " order by od.odid desc " ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lists.add(this.makeCartItemBean(rs));
			}
			
			// step03 : step01, step02의 결과를 Map 컬렉션에 담아서 반환합니다.
			mapList.put("order", order); // Order 타입이 Object 타입으로 승급
			mapList.put("lists", lists); // List<CartItem> 타입이 Object 타입으로 승급
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				super.closeConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return mapList;
	}
	
	private CartItem makeCartItemBean(ResultSet rs) {
		CartItem bean = new CartItem();
		
		try {
			bean.setImage01(rs.getString("image01"));
			bean.setPname(rs.getString("pname"));
			bean.setPnum(rs.getInt("pnum"));
			bean.setPoint(rs.getInt("point"));
			bean.setPrice(rs.getInt("price"));
			bean.setQty(rs.getInt("qty"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bean;
	}

	public List<Order> getHistory(String id) {
		// 과거 나의 쇼핑 내역을 최근 날짜부터 정렬하여 반환해 줍니다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from orders where id = ? order by orderdate desc, oid desc";
		
		List<Order> dataList = new ArrayList<Order>();
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dataList.add(this.makeOrderBean(rs));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				super.closeConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dataList;
	}
	
	private Order makeOrderBean(ResultSet rs) {
		Order bean = new Order();
		
		try {
			bean.setId(rs.getString("id"));
			bean.setOid(rs.getInt("oid"));
			bean.setOrderdate(String.valueOf(rs.getDate("orderdate")));
			bean.setRemark(rs.getString("remark"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bean;
	}

	public List<WishList> getWishList(String id) {
		// 나의 찜목록(WishList)을 컬렉션으로 반환해 줍니다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from wishlist where id = ?";
		
		List<WishList> dataList = new ArrayList<WishList>();
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dataList.add(this.makeWishListBean(rs));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				super.closeConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dataList;
	}

	
	private WishList makeWishListBean(ResultSet rs) {
		// 찜목록 1개에 대한 WishList bean 정보를 반환해 줍니다.
		WishList bean = new WishList();
		
		try {
			bean.setId(rs.getString("id"));
			bean.setPnum(rs.getInt("pnum"));
			bean.setQty(rs.getInt("qty"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bean;
	}


	public void insertWishList(String id, Map<Integer, Integer> wishList) {
		// 로그인 한 사람의 찜목록(WishList) 정보를 DB에 추가합니다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = -1;
		String sql = "";
		
		try {
			conn = super.getConnection();
			conn.setAutoCommit(false);
			
			// step01 : 과거 나의 찜목록을 삭제합니다.
			sql = "delete from wishlist where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			cnt = pstmt.executeUpdate();
			if(pstmt != null) {pstmt.close();}
			
			// step02 : 현재 세션 정보내에 들어있는 찜목록 정보를 테이블에 추가합니다.
			sql = "insert into wishlist(id, pnum, qty)";
			sql += " values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			for(Integer pnum : wishList.keySet()) {
				pstmt.setString(1, id);
				pstmt.setInt(2, pnum); // 상품 번호
				pstmt.setInt(3, wishList.get(pnum)); // 구매하고자 하는 수량
				cnt = pstmt.executeUpdate();
			}
			
			if(pstmt != null) {pstmt.close();}
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				super.closeConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void calculate(Member payer, Map<Integer, Integer> cartList) {
		// payer : 계산을 하는 사람(포인트 적립 대상자)
		// cartList : 카트에 담겨 있는 '상품 번호'와 '구매 수량' 정보
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = -1;
		String sql = "";
		
		try {
			conn = super.getConnection();
			conn.setAutoCommit(false);
			
			// step01 : 주문 테이블(orders)에 매출 1건 입력
			sql = "insert into orders(oid, id, orderdate)";
			sql += " values(seqorder.nextval, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, payer.getId());
			cnt = pstmt.executeUpdate();
			if(pstmt != null) {pstmt.close();}
			
			// step02 : 'step01'에서 방금 추가된 송장 번호 읽기
			sql = "select max(oid) as invoice from orders";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int invoice = 0; // 송장 번호
			if(rs.next()) {
				invoice = rs.getInt("invoice");
			}
			
			if(pstmt != null) {pstmt.close();}
			
			int totalPoint = 0; // 총 적립 포인트
			
			// 반복문을 사용하여
			for(Integer pnum : cartList.keySet()) {
				// step03 : 주문 상세 테이블(orderdetails)에 각각의 데이터 추가하기
				sql = "insert into orderdetails(odid, oid, pnum, qty)";
				sql += " values(seqorderdetail.nextval, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, invoice); // 송장 번호
				pstmt.setInt(2, pnum); // 상품 번호
				pstmt.setInt(3, cartList.get(pnum)); // 구매 수량
				cnt = pstmt.executeUpdate();
				if(pstmt != null) {pstmt.close();}
				
				// step04 : 각 상품들의 재고 수량 빼기(products)
				sql = "update products set stock = stock - ?";
				sql += " where pnum = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cartList.get(pnum)); // 구매 수량
				pstmt.setInt(2, pnum); // 상품 번호
				cnt = pstmt.executeUpdate();
				if(pstmt != null) {pstmt.close();}
				
				// 적립 포인트 = 구매수량 * 개당 적립 금액
				int point = cartList.get(pnum) * new ProductDao().getMileagePoint(pnum);
				totalPoint += point;
			} // 반복문 끝
			
			// step05 : 해당 회원의 적립 포인트 수정하기(members)
			sql = "update members set mpoint = mpoint + ?";
			sql += " where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, totalPoint); // 적립할 포인트 금액
			pstmt.setString(2, payer.getId()); // 회원 아이디
			cnt = pstmt.executeUpdate();
			if(pstmt != null) {pstmt.close();}
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				super.closeConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
