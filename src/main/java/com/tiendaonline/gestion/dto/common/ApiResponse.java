package com.tiendaonline.gestion.dto.common;

//<T> es un tipo genérico que permite que esta clase pueda manejar cualquier tipo de dato como respuesta, ya sea un objeto, una lista, etc.
public class ApiResponse<T> {

	private boolean success;
	private String message;
	private T data;

	public ApiResponse(boolean success, String message, T data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}
}
