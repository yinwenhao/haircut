package com.when_how.haircut.service;

import com.when_how.haircut.json.MyResponse;

public interface UserService {
	
	void test();
	
	MyResponse login(String account, String password);

}
