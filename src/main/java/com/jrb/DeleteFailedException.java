package com.jrb;

import org.springframework.dao.DataAccessException;

public class DeleteFailedException extends DataAccessException {
	public DeleteFailedException(String msg) {
		super(msg);
	}
}