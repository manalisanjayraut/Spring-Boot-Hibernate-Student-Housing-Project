/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studenthousingmanagement.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author manal
 */


public class ErrorPageSessionInterceptor implements HandlerInterceptor{
    
    private static final Logger logger = LoggerFactory.getLogger(ErrorPageSessionInterceptor.class);

  
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Check if the response status indicates an error (4xx or 5xx)
        if (response.getStatus() >= 400 || response.getStatus() >= 500) {
            // Invalidate session
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
                logger.info("Session invalidated");
            }
        }
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            
             logger.info("Error Ocuured: "+ ex.getLocalizedMessage());
            // Invalidate session if an exception occurs during request processing
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
    }
}
