package organio.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import organio.domain.User;
import organio.service.MongoUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

import static organio.constants.SecurityConstants.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider implements TokenProvider {

    public static final String USERNAME = "username";
    private final MongoUserService userService;

    @Override
    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        String userID = user.getId();

        return JWT.create()
                .withSubject(userID)
                .withClaim("id", userID)
                .withClaim(USERNAME, user.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC512(SECRET));
    }

    @Override
    public void validate(String token) {
        try {
            JWT.require(Algorithm.HMAC512(SECRET))
                    .withClaimPresence("id")
                    .withClaimPresence(USERNAME)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException | IllegalArgumentException exception) {
            String msg = "Json Web Toke is INVALID";
            log.error(msg, exception);
            throw new JWTVerificationException(msg, exception);
        }

    }

    public Optional<String> resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTH_HEADER);
        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }

    @Override
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return JWT.decode(token).getClaim(USERNAME).asString();
    }
}
