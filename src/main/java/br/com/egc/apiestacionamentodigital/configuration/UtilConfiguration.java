package br.com.egc.apiestacionamentodigital.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
