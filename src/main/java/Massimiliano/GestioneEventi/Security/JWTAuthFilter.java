package Massimiliano.GestioneEventi.Security;

import Massimiliano.GestioneEventi.Entities.Utente;
import Massimiliano.GestioneEventi.Exeptions.UnauthorizedException;
import Massimiliano.GestioneEventi.Service.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UtenteService usersService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Per favore passa il Bearer Token nell'Authorization header");
        } else {
            String token = authHeader.substring(7);
            System.out.println("TOKEN -> " + token);
            jwtTools.verifyToken(token);

            String id = jwtTools.extractIdFromToken(token);
            Utente currentUser = usersService.findById(Integer.parseInt(id));

            Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);


            filterChain.doFilter(request, response);

        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}