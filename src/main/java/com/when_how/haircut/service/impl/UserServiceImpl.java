package com.when_how.haircut.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.when_how.haircut.dao.mapper.UserMapper;
import com.when_how.haircut.json.MyResponse;
import com.when_how.haircut.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public void test() {
		System.out.println("test ~");
	}

	@Override
	public MyResponse login(String account, String password) {
		MyResponse result = new MyResponse(1);
		return result;
	}
	
}
