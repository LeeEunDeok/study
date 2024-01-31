package com.shopping.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// SuperClass의 상위 인터페이스이며 구현하고자 하는 모든 기능들의 명세(spec)들을 정의합니다.
public interface SuperController {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
