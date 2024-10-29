package utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieManager {
	
	// 쿠키 생성
	public static void makeCookie(HttpServletResponse response, String cName, String cValue, int CTime) {
		Cookie cookie = new Cookie(cName, cValue);
		
		cookie.setPath("/");
		cookie.setMaxAge(CTime);
		response.addCookie(cookie);
	}
	
	//쿠기 -> key값으로 value 찾기(이름으로 값 찾기)
	public static String readCookie(HttpServletRequest request, String cName) {
		String cookieValue = "";
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals(cName)) {
					cookieValue = c.getValue();
				}
			}
		}
		
		return cookieValue;
	}
	
	//쿠기 삭제
	public static void deleteCookie(HttpServletResponse response, String cName) {
		makeCookie(response, cName, "", 0); //쿠기 삭제시에는 시간을 0값 주면 삭제가 된다.
	}
	
}
