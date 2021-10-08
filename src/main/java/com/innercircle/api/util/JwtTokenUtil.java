package com.innercircle.api.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;
import com.innercircle.api.model.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = -2550185165626007488L;

	@Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private int expirationMinute;
	//retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
    //for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	//check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	//generate token for user
	public String generateToken(User userDetails) {
        Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(currentDate); 
		calendar.add(Calendar.MINUTE, expirationMinute);
        return Jwts.builder()
        .setId(userDetails.getId().toString())
        .setSubject(userDetails.getName())
        .setIssuer("InnerCircle")
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(calendar.getTime())
        .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
    
	//validate token
	public Boolean validateToken(String token, User userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getName()) && !isTokenExpired(token));
	}
}