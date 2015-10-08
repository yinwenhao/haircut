package com.when_how.haircut.mongo;

import org.bson.Document;
import org.springframework.beans.factory.FactoryBean;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

public class MongoCollectionFactory implements FactoryBean<MongoCollection<Document>> {
	
	private final MongoCollection<Document> collection;

	public MongoCollectionFactory(MongoClient client, String db, String collection) {
		super();
		this.collection = client.getDatabase(db).getCollection(collection);
	}
	
	public MongoCollection<Document> getCollection() {
		return this.collection;
	}

	@Override
	public MongoCollection<Document> getObject() throws Exception {
		return getCollection();
	}

	@Override
	public Class<?> getObjectType() {
		return MongoCollection.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
