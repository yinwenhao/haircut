package com.when_how.haircut.service;

import com.when_how.haircut.json.MyResponse;

public interface LocationService {
	
	MyResponse add(long id, double x, double y);

	MyResponse nearBy(double x, double y, int distance);

}
