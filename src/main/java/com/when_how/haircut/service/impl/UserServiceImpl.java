package com.when_how.haircut.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.when_how.haircut.dao.entity.User;
import com.when_how.haircut.dao.mapper.UserMapper;
import com.when_how.haircut.json.MyResponse;
import com.when_how.haircut.returncode.ReturnCode;
import com.when_how.haircut.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MongoCollection<Document> myMongoCollection;
	
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	public MyResponse test() {
		System.out.println("test ~");
		MyResponse result = new MyResponse(1);
		return result;
	}

	@Override
	public MyResponse test1(String account, String password) {
		Document r = myMongoCollection.find(
				new Document().append("user_id", "QRSTBWN")).first();

		MyResponse result = new MyResponse(1, r.getString("password"));
		return result;
	}

	@Override
	public MyResponse test2(String account) {
		log.error("~~~~~~~~~~~");
		List<Integer> loc = new ArrayList<Integer>();
		loc.add(100);
		loc.add(123);
		myMongoCollection.insertOne(new Document("user_id", account).append(
				"loc", loc));
		Document r = myMongoCollection.find(
				new Document().append("user_id", account)).first();
		List<?> cc = r.get("loc", List.class);
		for (int i = 0; i < cc.size(); i++) {
			System.out.println(cc.get(i));
		}
		MyResponse result = new MyResponse(1);
		return result;
	}

	@Override
	public MyResponse login(String account, String password) {
		User user = userMapper.getUserByAccount(account);
		if (user == null) {
			return new MyResponse(ReturnCode.ERROR, "wrong account or password");
		}
		if (!user.getPassword().equals(password)) {
			return new MyResponse(ReturnCode.ERROR, "wrong account or password");
		}
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		redisTemplate.opsForValue().set(String.valueOf(user.getId()), uuid);
		return new MyResponse(ReturnCode.SECCESS, uuid);
	}

	@Override
	public MyResponse logout(long uid) {
		redisTemplate.delete(String.valueOf(uid));
		return new MyResponse(ReturnCode.SECCESS);
	}

}
