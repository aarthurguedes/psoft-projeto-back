package psoft.backend.projeto.servicos;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;
import psoft.backend.projeto.entidades.Usuario;
import psoft.backend.projeto.filtros.TokenFilter;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.Optional;

@Service
public class JWTService {

    private UsuariosService usuariosService;
    private final String TOKEN_KEY = "login do usuario";

    public JWTService(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    public boolean usuarioExiste(String authorizationHeader) throws ServletException {
        String subject = getSujeitoDoToken(authorizationHeader);
        return usuariosService.getUsuario(subject).isPresent();
    }

    public boolean usuarioTemPermissao(String authorizationHeader, String email) throws ServletException {
        String subject = getSujeitoDoToken(authorizationHeader);
        Optional<Usuario> optionalUsuario = usuariosService.getUsuario(subject);

        return optionalUsuario.isPresent() && optionalUsuario.get().getEmail().equals(email);
    }

    public boolean usuarioTemPermissao(String authorizationHeader) throws ServletException {
        String subject = getSujeitoDoToken(authorizationHeader);
        Optional<Usuario> optionalUsuario = usuariosService.getUsuario(subject);

        return optionalUsuario.isPresent();
    }

    private String getSujeitoDoToken(String authorizationHeader) throws ServletException {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ServletException("Token inexistente ou mal formado!");
        }

        String token = authorizationHeader.substring(TokenFilter.TOKEN_INDEX);

        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey("login do usuario").parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new ServletException("Token invalido ou expirado!");
        }

        return subject;
    }

    public String geraToken(String email) {
        return Jwts.builder().setSubject(email).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000)).compact();
    }
}
