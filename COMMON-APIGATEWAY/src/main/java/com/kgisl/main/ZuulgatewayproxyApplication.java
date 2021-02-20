package com.kgisl.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.filters.ErrorFilter;
import com.kgisl.filters.PostFilter;
import com.kgisl.filters.PreFilter;
import com.kgisl.filters.RouteFilter;

/**
 * API Gateway class which expose other micro services of the application for
 * external users through ZUUL.
 *
 * @author sriji.n
 * @version 1.0
 * @since 19-02-2021
 */
//scanBasePackageClasses = { CORSFilter.class },
@SpringBootApplication(scanBasePackages = { "com.kgisl" }, exclude = { ManagementWebSecurityAutoConfiguration.class,
		DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class })
@EnableDiscoveryClient
@EnableZuulProxy
@RestController
@RefreshScope
public class ZuulgatewayproxyApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ZuulgatewayproxyApplication.class, args);
	}

	@RequestMapping("/hellozulus")
	public String getAllCustomerss() {
		return "success";
	}
	
	/**
	 * Pre filter.
	 *
	 * @return the pre filter
	 */

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	/**
	 * Post filter.
	 *
	 * @return the post filter
	 */
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	/**
	 * Error filter.
	 *
	 * @return the error filter
	 */
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	/**
	 * Route filter.
	 *
	 * @return the route filter
	 */
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}

//	@Bean
//	CORSFilter corsFilter() {
//		CORSFilter filter = new CORSFilter();
//		return filter;
//	}


//	@Bean
//    public CorsFilter corsFilter() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        final CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("*", config);
//        return new CorsFilter(source);
//    }
}