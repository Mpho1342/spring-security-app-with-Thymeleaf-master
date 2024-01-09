package com.Learnig.security.springBootSecurity.errors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomeResponseEntityHandler {
    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Exception ex){

        ErrorEntity error = new ErrorEntity(ex.getMessage(), LocalDateTime.now());

        ModelAndView mv = new ModelAndView();
        mv.setViewName("errorr");
        mv.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        mv.addObject("error",error);

        return mv;
    }

}
