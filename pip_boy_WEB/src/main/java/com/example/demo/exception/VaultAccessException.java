package com.example.demo.exception;

public class VaultAccessException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VaultAccessException(String message) {
        super("Vault access denied: " + message);
    }
}