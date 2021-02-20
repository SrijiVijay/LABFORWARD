package com.kgisl.main;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Discovery service class which exposes endpoint details of the
 * requested service to establish the connection.
 * 
 * @author sriji.n
 * @version 1.0
 * @since 19-02-2021
 */
@EnableEurekaServer
@SpringBootApplication(scanBasePackages = {"com.kgisl"}, exclude = { ManagementWebSecurityAutoConfiguration.class, SecurityAutoConfiguration.class })
@RestController
public class ServiceRegistryApplication {
	
	private Logger logger = Logger.getLogger(ServiceRegistryApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}
	
	@RequestMapping("/getproductdetails")
    public String getproductdetails() {
		logger.info(" inside getproductdetails  Micro service");
		return "Hello product";
    }
	

}