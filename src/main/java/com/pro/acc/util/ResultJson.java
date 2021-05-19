package com.pro.acc.util;

public class ResultJson<S, D> {

	private S status;

	private D data;

	public ResultJson(S status) {
		this.status = status;
	}

	public ResultJson(S status, D data) {
		this.status = status;
		this.data = data;
	}

	public S getStatus() {
		return status;
	}

	public void setStatus(S status) {
		this.status = status;
	}

	public D getData() {
		return data;
	}

	public void setData(D data) {
		this.data = data;
	}
}
