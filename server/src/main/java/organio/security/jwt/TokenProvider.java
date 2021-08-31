package organio.security.jwt;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface TokenProvider {

    String generateToken(Authentication authentication);

    void validate(String token);

    Optional<String> resolveToken(HttpServletRequest req);

    Authentication getAuthentication(String token);
}
