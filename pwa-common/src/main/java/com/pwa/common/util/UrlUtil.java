package com.pwa.common.util;

import javax.servlet.http.HttpServletRequest;

public final class UrlUtil {

    private UrlUtil() {
    }

    public static String constructAbsoluteUrl(final HttpServletRequest request, final String url) {
        String finalUrl = request.getRequestURL().append(url).toString();
        return finalUrl.replace(request.getRequestURI(), "");
    }

}
