package service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookiesService {
	
	public static Cookie createCookie(String name, String value, boolean persistent) {
		Cookie cookie = new Cookie(name, value);
		if(persistent) cookie.setMaxAge(60*60);
		return cookie;
	}
	
	public static Cookie createCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		return cookie;
	}
	
	public static Cookie[] getCookies(HttpServletRequest req) {
		return req.getCookies();
	}
	
	public static void eraseAll(HttpServletRequest req, HttpServletResponse res) {
		Cookie username = new Cookie("username","");
		username.setMaxAge(0);
		res.addCookie(username);
		
		Cookie password = new Cookie("password","");
		password.setMaxAge(0);
		res.addCookie(password);

		Cookie role = new Cookie("role","");
		role.setMaxAge(0);
		res.addCookie(role);
	}
	
	public static Cookie getCookie(HttpServletRequest req, String name) {
		Cookie[] cookies = req.getCookies();
		
		for(Cookie cookie: cookies) {
			if(cookie.getName().equalsIgnoreCase(name)) 
				return cookie;
		}
		
		return null;
	}
}
