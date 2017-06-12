package org.mine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyResource {
	private final static Logger LOGGER = LoggerFactory.getLogger(MyResource.class);
	public static void main(String[] args) {

		LOGGER.warn("Starting...");
		System.out.println("Hi..");
		LOGGER.error("Ending");
	}
}
