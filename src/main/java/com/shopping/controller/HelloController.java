package com.shopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;

public class HelloController extends SuperClass {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("헬로우 커맨드를 이용하여 Post 방식으로 요청됨");
	}
}
