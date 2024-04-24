
package com.mycompany.studenthousingmanagement;

/**
 *
 * @author manal
 */

import com.mycompany.studenthousingmanagement.util.ErrorPageSessionInterceptor;
import com.mycompany.studenthousingmanagement.util.SessionInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class StudentHousingConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor())
                .addPathPatterns("/**") // Apply interceptor to all URLs
                .excludePathPatterns("/login", "/logout","/register","/error"); // Exclude login and logout URLs from interception
    
        registry.addInterceptor(new ErrorPageSessionInterceptor())
            .addPathPatterns("/**");
            //.excludePathPatterns("/login", "/logout", "/register");
    
    }
    
    
}
