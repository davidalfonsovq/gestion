package com.gestion.api.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gestion.api.service.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The Class JwtRequestFilter.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	/** The jwt user details service. */
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	/** The jwt token util. */
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * Do filter internal.
	 *
	 * @param request the request
	 * @param response the response
	 * @param filterChain the filter chain
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {

	
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		
		try {
			
			final HttpServletRequest httpRequest = (HttpServletRequest) request;

			if (httpRequest.getRequestURI().startsWith("/swagger-ui")
					    || httpRequest.getRequestURI().startsWith("/swagger")
					    || httpRequest.getRequestURI().startsWith("/swagger-resources")
					    || httpRequest.getRequestURI().startsWith("/v3/api-docs")
					    || httpRequest.getRequestURI().startsWith("/h2-console")) {
					    filterChain.doFilter(request, response);
					    return;
			 }
			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				jwtToken = requestTokenHeader.substring(7);
				try {
					username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				} catch (IllegalArgumentException e) {
					logger.info("No se puede obtener el token JWT");
					sendUnauthorizedResponse(response, "No se puede obtener el token JWT");
				} catch (ExpiredJwtException e) {
					logger.info("El token JWT ha expirado");
					sendUnauthorizedResponse(response, "Token expirado");
				}
			}else {
				if(!request.getRequestURI().equalsIgnoreCase("/authenticate")) {
					sendUnauthorizedResponse(response, "Token inválido");
					logger.info("Token Invalido");
				}
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
				if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		} catch (Exception e) {
			logger.info("Token Invalido");
			sendUnauthorizedResponse(response, "Token inválido");
		}
		
		
		filterChain.doFilter(request, response);

	}
	
	/**
	 * Send unauthorized response.
	 *
	 * @param response the response
	 * @param message the message
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    response.setContentType("application/json");
	    response.getWriter().write("{\"error\": \"" + message + "\"}");
	}

}
