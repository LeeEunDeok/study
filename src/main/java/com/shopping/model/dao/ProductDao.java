package com.shopping.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.shopping.model.bean.Product;
import com.shopping.utility.Paging;

public class ProductDao extends SuperDao{
	public List<Product> getDataList(Paging paging){
		String sql = "select pnum, name, company, image01, image02, image03, stock, price, category, contents, point, inputdate";
		sql += " from (select rank() over(order by pnum desc) as ranking, pnum, name, company, image01, image02, image03, stock, price, category, contents, point, inputdate";
		sql += " from products";
		// 필드 검색을 위해 mode 변수로 분기 처리 하도록 합니다.
		String mode = paging.getMode();
		String keyword = paging.getKeyword();
		
		if(mode==null || mode.equals("all") || mode.equals("null") || mode.equals("")) {
			
		}else { // 전체모드가 아니면 
			sql += " where " + mode + " like '%" + keyword + "%'";
		}
		sql += ")";
		sql += " where ranking between ? and ?";
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Product> dataList = new ArrayList<Product>();
		
		super.conn = super.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, paging.getBeginRow());
			pstmt.setInt(2, paging.getEndRow());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Product bean = this.resultSet2Bean(rs);
				
				dataList.add(bean);
				
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
	
	public List<Product> getDataList(int beginRow, int endRow){
		String sql = "select pnum, name, company, image01, image02, image03, stock, price, category, contents, point, inputdate"
				+ " from (select rank() over(order by pnum asc) as ranking, pnum, name, company, image01, image02, image03, stock, price, category, contents, point, inputdate"
				+ " from products)"
				+ " where ranking between ? and ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Product> dataList = new ArrayList<Product>();
		
		super.conn = super.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Product bean = this.resultSet2Bean(rs);
				
				dataList.add(bean);
				
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
	
	private Product resultSet2Bean(ResultSet rs) {
		try {
			Product bean = new Product();
			
			bean.setCategory(rs.getString("category"));
			bean.setCompany(rs.getString("company"));
			bean.setContents(rs.getString("contents"));
			bean.setImage01(rs.getString("image01"));
			bean.setImage02(rs.getString("image02"));
			bean.setImage03(rs.getString("image03"));
			bean.setInputDate(String.valueOf(rs.getString("inputdate")));
			bean.setName(rs.getString("name"));
			bean.setPnum(rs.getInt("pnum"));
			bean.setPoint(rs.getInt("point"));
			bean.setPrice(rs.getInt("price"));
			bean.setStock(rs.getInt("stock"));
			
			return bean;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	


	public List<Product> getDataList02(){
		
		List<Product> dataList = new ArrayList<Product>();
		
		for (int i= 1; i<= 6; i++) {
			if(i % 2 == 0) {
				dataList.add(new Product(10 * i, "상품0" + i, null,
				"../image/croissant_0" + i + ".png", null, null, 0, 0, null, "이 상품은 너무 좋아여 엄청 맛있어요", 0, null));
			}else {
				dataList.add(new Product(10 * i, "상품0" + i, null,
						"../image/croissant_0" + i + ".png", null, null, 0, 0, null, "별로에요.", 0, null));
				
			}
		
		}
		return dataList;
	}
}
