package com.telbastudio.springboot.backend.apirest.models.services;

public class ExportResult {
	private final byte[] exportedData;
	private final String fileName;

	public ExportResult(byte[] exportedData, String fileName) {
		this.exportedData = exportedData;
		this.fileName = fileName;
	}

	public byte[] getExportedData() {
		return exportedData;
	}

	public String getFileName() {
		return fileName;
	}
}