package com.minws.wechat.entity.SYS;

import java.io.Serializable;
import java.util.List;

public class DataGrid implements Serializable {

	private static final long serialVersionUID = 1651525560155147152L;

	private String total;

	private List<?> rows;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public DataGrid() {

	}

	/**
	 * DataGrid
	 * 
	 * @param total
	 * @param rows
	 */
	public DataGrid(String total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
}
