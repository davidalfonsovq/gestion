package com.gestion.api.config;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * The Class JwtTokenUtil.
 */
@Component
public class JwtTokenUtil implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 464214880478737476L;

	/** The secret. */
	@Value("${jwt.secret}")
	private String secret;

	/**
	 * Generate token.
	 *
	 * @param userDetails the user details
	 * @return the string
	 */
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	/**
	 * Generate token.
	 *
	 * @param extraClaims the extra claims
	 * @param userDetails the user details
	 * @return the string
	 */
	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return buildToken(extraClaims, userDetails, 1000000);
	}

	/**
	 * Builds the token.
	 *
	 * @param extraClaims the extra claims
	 * @param userDetails the user details
	 * @param expiration the expiration
	 * @return the string
	 */
	private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	/**
	 * Gets the sign in key.
	 *
	 * @return the sign in key
	 */
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	/**
	 * Gets the username.
	 *
	 * @param token the token
	 * @return the username
	 */
	public String getUsername(String token) {
		return getClaim(token, Claims::getSubject);
	}
	
	/**
	 * Gets the claim.
	 *
	 * @param <T> the generic type
	 * @param token the token
	 * @param claimsResolver the claims resolver
	 * @return the claim
	 */
	public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * Checks if is token expired.
	 *
	 * @param token the token
	 * @return true, if is token expired
	 */
	private boolean isTokenExpired(String token) {
		return getExpiration(token).before(new Date());
	}

	/**
	 * Gets the expiration.
	 *
	 * @param token the token
	 * @return the expiration
	 */
	private Date getExpiration(String token) {
		return getClaim(token, Claims::getExpiration);
	}

	/**
	 * Gets the all claims.
	 *
	 * @param token the token
	 * @return the all claims
	 */
	private Claims getAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	/**
	 * Validate token.
	 *
	 * @param token the token
	 * @param userDetails the user details
	 * @return the boolean
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	/**
	 * Gets the username from token.
	 *
	 * @param token the token
	 * @return the username from token
	 */
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	/**
	 * Gets the claim from token.
	 *
	 * @param <T> the generic type
	 * @param token the token
	 * @param claimsResolver the claims resolver
	 * @return the claim from token
	 */
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}

}
