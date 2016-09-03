package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

@Controller
public class LoginFilter implements Filter {   

    public void init(FilterConfig arg0) throws ServletException {}
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            HttpSession session = httpRequest.getSession();
            if(null == session) {
                httpResponse.sendRedirect("index.jsp");
            } else {
                chain.doFilter(request,response);
            }
         } catch (Exception ex) {
            request.setAttribute("Message", (ex.getMessage()).toString());
            request.getRequestDispatcher("index.jsp").forward(request, response);
         }
   }

    public void destroy() {}
}  
