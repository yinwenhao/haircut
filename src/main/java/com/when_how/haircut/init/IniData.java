package com.when_how.haircut.init;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IniData extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 这个是最后执行的，具体看日志
	 */
	@Override
	public void init() throws ServletException {
		log.info("initialize start");
		
		log.info("initialize end");
	}
	
}
