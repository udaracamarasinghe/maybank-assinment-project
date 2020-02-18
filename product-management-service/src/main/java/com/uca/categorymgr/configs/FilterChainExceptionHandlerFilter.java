package com.uca.categorymgr.configs;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uca.categorymgr.utili.apis.BaseRestAPIRespose;
import com.uca.categorymgr.utili.apis.ErrorDetails;

public class FilterChainExceptionHandlerFilter extends OncePerRequestFilter {

	private static final Logger logger = Logger.getLogger(FilterChainExceptionHandlerFilter.class.getName());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (Exception ex) {
			ErrorDetails errorD = new ErrorDetails(new Date(), ex.getMessage(), ex.getMessage());

			ObjectMapper mapper = new ObjectMapper();
			String errorResponse = mapper.writeValueAsString(BaseRestAPIRespose
					.createBaseRestAPIRespose(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), errorD));

			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.getWriter().write(errorResponse);

			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}

	}

}
