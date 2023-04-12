package com.devopsinfinet.config;

import org.springframework.context.annotation.Bean;

public class SlethtConfig {

	@Bean
	public AlwaysSampler defaultSampler() {
	    return new AlwaysSampler();
	}
}
