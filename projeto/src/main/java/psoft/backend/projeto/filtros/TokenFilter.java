package psoft.backend.projeto.filtros;

import io.jsonwebtoken.*;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends GenericFilterBean {

    public final static int TOKEN_INDEX = 7;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new ServletException("Token inexistente ou mal formatado!");
        }

        String token = header.substring(TOKEN_INDEX);

        try {
            Jwts.parser().setSigningKey("login do usuario").parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException | MalformedJwtException | PrematureJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());

            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
