package com.example.ticketReservationSystem.web;

import com.example.ticketReservationSystem.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import org.springframework.security.core.userdetails.UserDetails;


@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException{

        Collection<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        
        String redirectURL = request.getContextPath();

        if(roles.contains("ROLE_ADMIN")){
            redirectURL = "/admin";
        }
        else{
            redirectURL = "/user";
        }
        response.sendRedirect(redirectURL);

    }

}
