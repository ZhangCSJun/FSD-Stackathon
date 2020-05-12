package cn.zj.cloud.user.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginInfo implements Serializable{
	
	private String id;
	private String userType;

}
