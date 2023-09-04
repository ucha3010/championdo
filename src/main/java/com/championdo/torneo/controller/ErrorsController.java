package com.championdo.torneo.controller;

import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class ErrorsController {
	
	public static final String ADE_VIEW = "error/403";
	public static final String ISE_VIEW = "error/500";

	@ExceptionHandler(AccessDeniedException.class)
	public String showAccessDeniedException(AccessDeniedException e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		LoggerMapper.log(Level.ERROR,"showInternalServerError", sw.toString(), getClass());
		return ADE_VIEW;
	}
	
	@ExceptionHandler(Exception.class)
	public String showInternalServerError(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		LoggerMapper.log(Level.ERROR,"showInternalServerError", sw.toString(), getClass());
		return ISE_VIEW;
	}

}
