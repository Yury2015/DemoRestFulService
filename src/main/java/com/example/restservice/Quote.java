package com.example.restservice;

public class Quote {

	private final long id;
	private final String isin;
	private final String bid;
	private final String ask;

	public Quote(String isin, String bid, String ask) {
		this.id = -1;
		this.isin = isin;
		this.bid = bid;
		this.ask = ask;
	}

	public Quote(long id, String isin, String bid, String ask) {
		this.id = id;
		this.isin = isin;
		this.bid = bid;
		this.ask = ask;
	}

	public long getId() {
		return id;
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
