package com.medcard.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebFilter("/*")
public class LanguageFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        String langParam = req.getParameter("lang");
        if (langParam != null) {
            session.setAttribute("lang", langParam);
        } else if (session.getAttribute("lang") == null) {
            session.setAttribute("lang", "en");
        }

        String lang = (String) session.getAttribute("lang");
        Locale locale = new Locale(lang);
        request.setAttribute("javax.servlet.jsp.jstl.fmt.locale.session", locale);

        chain.doFilter(request, response);
    }
}