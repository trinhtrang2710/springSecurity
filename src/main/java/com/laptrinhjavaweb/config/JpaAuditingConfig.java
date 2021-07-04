package com.laptrinhjavaweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

//like JPA Config; we need @Configuration, turn on JPAAuditing: @EnableJapAuditing
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {

	@Bean
	public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}
	//get username from spring security to createDate,createBy, lastModify.. automatically
	public static class AuditorAwareImpl implements AuditorAware<String> {

		@Override
		public String getCurrentAuditor() {
			//get username from spring security
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			//when data null or not login
			if (authentication == null) {
				return null;
			}
			return authentication.getName();
		}
	}
}
