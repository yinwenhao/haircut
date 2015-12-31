package com.when_how.haircut.interceptor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.when_how.haircut.common.BaseAction;

public class AuthInterceptor implements Interceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Logger log = LoggerFactory.getLogger(getClass());

	private static Map<String, Integer> map = new HashMap<String, Integer>();

	// 初始化Set
	static {
		// 不需要验证登陆的，数字为：0
		map.put("RegisterAction", 0);
		
		// 部分方法不需要验证登陆的，数字为：1
		map.put("LoginAction", 1);
		
		// 需要验证ip的，数字为：2
		
		// 部分方法需要验证ip的，数字为：3
		
	}
	
	/**
	 *  部分方法不需要验证登陆的
	 */
	private static Map<String, Set<String>> methodMap = new HashMap<String, Set<String>>();
	
	static {
		Set<String> s1 = new HashSet<String>();
		s1.add("login");
		methodMap.put("LoginAction", s1);
	}
	
	/**
	 *  部分方法需要验证ip的
	 */
//	private static Map<String, Set<String>> methodMapIp = new HashMap<String, Set<String>>();

	@Override
	public void destroy() {
		log.info("AuthInterceptor destroy");
	}

	@Override
	public void init() {
		log.info("AuthInterceptor init");
	}

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getAction().getClass().getSimpleName();
		String methodName = invocation.getProxy().getMethod();
//		Map<String, Object> session = invocation.getInvocationContext().getSession();
		if (map.containsKey(actionName)) {
			// 可能不需要验证登陆
			if (map.get(actionName) == 0) {
				// 不需要验证登陆
				return invocation.invoke();
			} else if (map.get(actionName) == 1) {
				// 部分方法不需要验证登陆的
				if (methodMap.get(actionName).contains(methodName)) {
					// 不需要验证登陆
					return invocation.invoke();
				}
			}
		} else {
			// 需要验证登陆
			Map<String, Object> param = invocation.getInvocationContext().getParameters();
			if (param.containsKey("uid") && param.containsKey("token")) {
				String uid = (String) param.get("uid");
				String token = (String) param.get("token");
				String uuid = redisTemplate.opsForValue().get(uid);
				if (token.equals(uuid)) {
					// 已经登陆，正常访问
					return invocation.invoke();
				}
			}
		}
		// 需要登陆，返回错误信息
		return BaseAction.ERROR;
	}

	/**
	 * 获得真实IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("Cdn-Src-Ip");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
}
