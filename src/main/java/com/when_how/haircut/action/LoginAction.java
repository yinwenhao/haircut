/**
 * 
 */
package com.when_how.haircut.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.when_how.haircut.common.BaseAction;
import com.when_how.haircut.service.UserService;

/**
 * @author when_how
 * 
 */
//@Namespace("/login")
public class LoginAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String account;
	
	private String password;
	
	private long uid;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登陆
	 * 
	 * @return
	 */
	@Action("/login")
	public String login() {
		setResponse(userService.login(getAccount(), getPassword()));
		return SUCCESS;
	}
	
	/**
	 * 退出登陆
	 * 
	 * @return
	 */
	@Action("/logout")
	public String logout() {
		setResponse(userService.logout(getUid()));
		return SUCCESS;
	}
	
	@Action("/test")
	public String test() {
		setResponse(userService.test());
		return SUCCESS;
	}

	@Action("/test1")
	public String test1() {
		setResponse(userService.test1(getAccount(), getPassword()));
		return SUCCESS;
	}

	@Action("/test2")
	public String test2() {
		setResponse(userService.test2(getAccount()));
		return SUCCESS;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

}
