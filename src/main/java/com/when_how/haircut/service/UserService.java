package com.when_how.haircut.service;

import com.when_how.haircut.json.MyResponse;

public interface UserService {
	
	MyResponse test();
	
	MyResponse login(String account, String password);

	MyResponse logout(long uid);
	
	MyResponse test1(String account, String password);

	MyResponse test2(String account);

}
