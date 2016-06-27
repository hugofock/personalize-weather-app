package com.pwa.common.spring;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class UserContext {

    private static final String SPRING_ANNONYMOUS_USER = "anonymousUser";

    private UserContext() {
    }

    public static String getUsername() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String result = null;
        if (auth != null) {
            final Object principal = auth.getPrincipal();

            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            }

            result = principal.toString();
        }

        if (StringUtils.isEmpty(result) || SPRING_ANNONYMOUS_USER.equals(result)) {
            result = "SYSTEM";
        }

        return result;
    }

}
