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
		getSession().clear();
		setVelocityLocation("/login.vm");
		return VELOCITY;
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

}
