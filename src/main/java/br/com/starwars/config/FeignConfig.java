package br.com.starwars.config;

import br.com.starwars.adapters.external.handler.FeignExceptionHandler;
import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableFeignClients("br.com.starwars.adapters.external.client")
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignExceptionHandler();
    }

    @Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100L, TimeUnit.SECONDS.toMillis(2L), 2);
    }
}
