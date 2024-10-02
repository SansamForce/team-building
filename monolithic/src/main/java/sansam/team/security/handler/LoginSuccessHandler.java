package sansam.team.security.handler;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import sansam.team.security.jwt.JwtToken;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final Environment env;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("로그인 성공 : {}", authentication);

        byte[] keyBytes = Decoders.BASE64.decode(env.getProperty("JWT_SECRET_KEY"));
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

        //User loginInfo = (User) authentication.getPrincipal();

        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())  // userSeq를 subject로 설정
                //.claim("userId", loginInfo.getUserId())
                //.claim("userName", loginInfo.getUserName())
                .claim("auth", authorities)  // 권한 정보 추가
                .setIssuedAt(new Date())  // iat 추가 (발행 시간)
                .setExpiration(new Date((new Date()).getTime() + 86400000))  // 만료 시간 (1일)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성 (7일 만료)
        String refreshToken = Jwts.builder()
                .setIssuedAt(new Date())  // iat 추가 (발행 시간)
                .setExpiration(new Date((new Date()).getTime() + 604800000))  // 만료 시간 (7일)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        // JWT 토큰 생성
        JwtToken jwtToken = JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        response.setHeader("token", jwtToken.getAccessToken());
    }

}
