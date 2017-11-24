package se.atrosys.baking.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.stream.Collectors;

/**
 * TODO write documentation
 */
@Component
public class PassthroughFilter implements Filter {
	private final Logger logger  = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		final Enumeration<String> headerNames = httpServletRequest.getHeaderNames();

		logger.trace("{}", Collections.list(headerNames)
			.stream()
			.map(name -> String.format("%s: %s", name, httpServletRequest.getHeader(name)))
			.collect(Collectors.joining(", ")));
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}
