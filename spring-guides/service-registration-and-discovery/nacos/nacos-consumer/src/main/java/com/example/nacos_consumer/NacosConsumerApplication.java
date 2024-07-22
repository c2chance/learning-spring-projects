package com.example.nacos_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.netflix.eureka.instance.ServiceInstance;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerApplication {

	@RestController
	public class NacosController {

		@Autowired
		private LoadBalancerClient loadBalancerClient;

		@Autowired
		private RestTemplate restTemplate;

		@Value("${spring.application.name}")
		private String appName;

		@GetMapping("/echo/app-name")
		public String echoAppName() {
			// use LoadBalanceClient & RestTemplate to access
			ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
			String url = String.format("http://%s:%s/echo/%s", serviceInstance.getPort(), appName);
			System.out.println("request url: " + url);
			return restTemplate.getForObject(url, String.class);
		}
	}

	// Instancing RestTemplate instance
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(NacosConsumerApplication.class, args);
	}

}
