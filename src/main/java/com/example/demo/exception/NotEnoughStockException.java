package com.example.demo.exception;

public class NotEnoughStockException extends RuntimeException {
	private String message;

	public NotEnoughStockException(String message) {
		this.message = message;
	}

	public NotEnoughStockException() {
		super();
	}

	public NotEnoughStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotEnoughStockException(Throwable cause) {
		super(cause);
	}
	
	
	
}
