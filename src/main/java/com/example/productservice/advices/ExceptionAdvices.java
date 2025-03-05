package com.example.productservice.advices;

import com.example.productservice.dtos.ErrorResponseDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseDto handleRuntimeException(RuntimeException e){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setStatus("Error");
        errorResponseDto.setMessage(e.getMessage());
        return errorResponseDto;
    }

    @ExceptionHandler(Exception.class)
    public  String handleException(){
        return "Something went wrong";
    }
}
