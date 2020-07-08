package io.renren.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class HttpContextUtils {

	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static String getDomain(){
		HttpServletRequest request = getHttpServletRequest();
		StringBuffer url = request.getRequestURL();
		return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
	}

	public static String getOrigin(){
		HttpServletRequest request = getHttpServletRequest();
		return request.getHeader("Origin");
	}

	/**
	 * 判断请求是PC 还是手机端
	 * @param request
	 * @return
	 */
	public static boolean isMobileDevice(HttpServletRequest request) {
		String requestHeader = request.getHeader("user-agent");
		String[] deviceArray = new String[]{"android", "mac os", "windows phone"};
		if (requestHeader == null) {
			return false;
		}
		requestHeader = requestHeader.toLowerCase();
		for (String device : deviceArray) {
			if (requestHeader.indexOf(device) != -1) {
				return true;
			}
		}
		return false;
	}
}
