package com.championdo.torneo.controller;

import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorsController {
	
	public static final String ISE_VIEW = "error/500";
	
	@ExceptionHandler(Exception.class)
	public String showInternalServerError(Exception e) {
		LoggerMapper.log(Level.ERROR,"showInternalServerError", e.getMessage(), getClass());
		return ISE_VIEW;
	}

}
