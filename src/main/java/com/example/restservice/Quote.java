package com.example.restservice;

public class Quote {

	private final String isin;
	private final String bid;
	private final String ask;

	public Quote(String isin, String bid, String ask) {
		this.isin = isin;
		this.bid = bid;
		this.ask = ask;
	}

	public String getIsin() {
		return isin;
	}

	public String getBid() {
		return bid;
	}

	public String getAsk() {
		return ask;
	}
}
