package com.pwa.security.handler;

import com.pwa.common.constant.MimeType;
import com.pwa.common.constant.Status;
import com.pwa.common.constant.UserRoleCode;
import com.pwa.common.vo.BasicResponseEntityVO;
import com.pwa.model.User;
import com.pwa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.pwa.common.util.UrlUtil.constructAbsoluteUrl;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private IUserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {

        BasicResponseEntityVO basicResponseEntityVO = new BasicResponseEntityVO();

        User user = userService.findUserByUsername(authentication.getName());
        if (user != null) {

            List<GrantedAuthority> authenticationList = (List<GrantedAuthority>) authentication.getAuthorities();

            response.setContentType(MimeType.JSON);
            basicResponseEntityVO.setStatus(Status.SUCCESS);
            if (authenticationList.size() == 1) {
                String userRole = authenticationList.get(0).getAuthority();
                if (userRole.equals(UserRoleCode.ROLE_ADMIN.name())) {
                    basicResponseEntityVO.setUrl(constructAbsoluteUrl(request, "/admin"));
                } else {
                    basicResponseEntityVO.setStatus(Status.FAILURE);
                    basicResponseEntityVO.setMessage("Error Authenticate");
                }

            }

        } else {
            basicResponseEntityVO.setStatus(Status.FAILURE);
            basicResponseEntityVO.setMessage("Error Authenticate");
        }

        response.getWriter().write(basicResponseEntityVO.toString());
        response.getWriter().flush();
        clearAuthenticationAttributes(request);

    }

}
