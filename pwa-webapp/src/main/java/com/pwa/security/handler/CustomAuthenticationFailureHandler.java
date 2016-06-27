package com.pwa.security.handler;

import com.pwa.common.constant.MimeType;
import com.pwa.common.constant.Status;
import com.pwa.common.vo.BasicResponseEntityVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
        throws IOException, ServletException {
        response.setContentType(MimeType.JSON);

        BasicResponseEntityVO basicResponseEntityVO = new BasicResponseEntityVO();
        basicResponseEntityVO.setStatus(Status.FAILURE);
        basicResponseEntityVO.setMessage("The email and password you entered don't match.");

        response.getWriter().write(basicResponseEntityVO.toString());
        response.getWriter().flush();
    }

}
