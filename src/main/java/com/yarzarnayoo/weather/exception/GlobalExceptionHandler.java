package com.yarzarnayoo.weather.exception;

import com.yarzarnayoo.weather.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WeatherApiException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleWeatherException(
            WeatherApiException ex
    ) {

        return new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
    }

}