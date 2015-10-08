/**
 * 
 */
package com.when_how.haircut.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.when_how.haircut.common.BaseAction;
import com.when_how.haircut.service.LocationService;

/**
 * @author when_how
 * 
 */
//@Namespace("/login")
public class LocationAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2L;

	private long id;
	
	private float x;
	
	private float y;
	
	@Autowired
	private LocationService locationService;

	/**
	 * 增加
	 * 
	 * @return
	 */
	@Action("/add")
	public String add() {
		setResponse(locationService.add(getId(), getX(), getY()));
		return SUCCESS;
	}

	/**
	 * 查询
	 * 
	 * @return
	 */
	@Action("/nearBy")
	public String nearBy() {
		setResponse(locationService.nearBy(getX(), getY(), 100));
		return SUCCESS;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
