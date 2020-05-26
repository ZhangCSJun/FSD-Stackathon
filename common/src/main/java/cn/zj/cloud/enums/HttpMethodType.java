package cn.zj.cloud.enums;

public enum HttpMethodType {
	POST("POST"),GET("GET"),PUT("PUT"),DELETE("DELETE"),OPTIONS("OPTIONS");

	private String type;
	HttpMethodType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
}
