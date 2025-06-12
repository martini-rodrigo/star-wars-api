package br.com.starwars.infrastructure.external.handler;

import br.com.starwars.core.domain.exception.BadRequestException;
import br.com.starwars.core.domain.exception.NotFoundException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class FeignExceptionHandler implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException exception = FeignException.errorStatus(methodKey, response);
        return switch (HttpStatus.valueOf(response.status())) {
            case BAD_REQUEST -> new BadRequestException(exception.getMessage());
            case NOT_FOUND -> new NotFoundException(exception.getMessage());
            default -> exception;
        };
    }
}
