package com.service.uesr;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class GetCookieService {
 private static final String Users = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

 public static void deleteUserCookie(HttpServletResponse response) {
		Cookie cookieUsers = new Cookie(Users, null);
		// 0 giây. (Cookie này sẽ hết hiệu lực ngay lập tức)
		cookieUsers.setMaxAge(0);
		response.addCookie(cookieUsers);
	}


}
