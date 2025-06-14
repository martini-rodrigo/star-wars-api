package br.com.starwars.adapters.external.handler;

import br.com.starwars.core.exception.BadRequestException;
import br.com.starwars.core.exception.NotFoundException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class FeignExceptionHandler implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException exception = FeignException.errorStatus(methodKey, response);
        log.error("Error occurred while calling {}: Error: {}", methodKey, exception.getMessage());

        return switch (HttpStatus.valueOf(response.status())) {
            case BAD_REQUEST -> new BadRequestException(exception.getMessage());
            case NOT_FOUND -> new NotFoundException(exception.getMessage());
            default -> exception;
        };
    }
}
