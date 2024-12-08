package org.zerock.ex00.controller.advice;


import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2

public class CommonExceptionAdvice {



    @ExceptionHandler(NullPointerException.class)
    public String exceptNumber(Exception exception, Model model){
//String은 경로를 지정할 때 씀
        log.error("====================================");
        log.error(exception.getMessage());

        model.addAttribute("msg","Number Check");

        return "error_page";



    }



}
