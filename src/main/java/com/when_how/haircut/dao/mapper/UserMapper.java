package com.when_how.haircut.dao.mapper;

import java.util.List;

import com.when_how.haircut.dao.entity.User;

public interface UserMapper {
	
	public User getUser(long id);
	
	public User getUserByAccount(String account);
	
	public List<User> getUserList();
	
	public void insertUser(User user);
	
}
