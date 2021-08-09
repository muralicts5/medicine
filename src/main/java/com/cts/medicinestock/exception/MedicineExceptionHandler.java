package com.cts.medicinestock.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MedicineExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorStatus> allExceptions(HttpServletRequest req, Exception e) {
		ErrorStatus error = new ErrorStatus(new Date(), e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorStatus>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
