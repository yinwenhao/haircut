package com.when_how.haircut.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.when_how.haircut.json.MyResponse;
import com.when_how.haircut.service.LocationService;

@Service("locationService")
public class LocationServiceImpl implements LocationService {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private MongoCollection<Document> myMongoCollection;
	
	@Override
	public MyResponse add(long id, double x, double y) {
		List<Double> loc = new ArrayList<Double>();
		loc.add(x);
		loc.add(y);
		myMongoCollection.insertOne(new Document("id", id).append(
				"loc", loc));
		Document r = myMongoCollection.find(
				new Document().append("id", id)).first();
		List<?> cc = r.get("loc", List.class);
		for (int i = 0; i < cc.size(); i++) {
			System.out.println(cc.get(i));
		}
		MyResponse result = new MyResponse(1);
		return result;
	}

	@Override
	public MyResponse nearBy(double x, double y, int distance) {
		List<Double> loc = new ArrayList<Double>();
		loc.add(x);
		loc.add(y);
		FindIterable<Document> re = myMongoCollection.find(new Document("loc", new Document("$near",loc))).limit(10);
		for (Document d : re) {
			System.out.println(d.get("id"));
		}
		MyResponse result = new MyResponse(1);
		return result;
	}

}
