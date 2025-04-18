package security.spring.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import security.spring.services.MyUserService;
import security.spring.services.TokenService;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    ApplicationContext applicationContext;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username= null;
        String token=null;
        String authHeader=request.getHeader("Authorization");
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token= authHeader.substring(7);
            username= tokenService.extractUserName(token);

        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()== null){
            UserDetails userDetails=applicationContext.getBean(MyUserService.class).loadUserByUsername(username);
            if(tokenService.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken token1=
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                token1.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token1);
            }

        }
        filterChain.doFilter(request,response);
    }
}
