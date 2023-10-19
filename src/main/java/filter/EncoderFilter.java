package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;



@WebFilter(urlPatterns = "/*")
public class EncoderFilter implements Filter{

	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("여기는 doFilter메소드");
		
		request.setCharacterEncoding("utf-8");
		
		
		chain.doFilter(request, response);
		
 
	}

}
