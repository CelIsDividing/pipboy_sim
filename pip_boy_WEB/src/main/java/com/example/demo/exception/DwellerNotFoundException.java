package com.example.demo.exception;

public class DwellerNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DwellerNotFoundException(int id) {
        super("[DWELLER_" + id + " :::: RECORD NOT FOUND]");
    }
}