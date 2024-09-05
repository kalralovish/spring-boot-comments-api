package com.comments.api.commentsapiservice.util;

import javax.servlet.http.HttpServletRequest;

public class IpAddressUtil {

    public static String getClientIpAddress(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader == null) {
            return request.getRemoteAddr();
        }
        return xForwardedForHeader.split(",")[0];
    }
}