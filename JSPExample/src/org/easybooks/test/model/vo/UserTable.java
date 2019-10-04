package org.easybooks.test.model.vo;

public class UserTable {
	//Fields
	private Integer id;
	private String username;
	private String password;
	
	//Property accessors
	//属性 id 的 get/set 方法
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	//属性 username 的 get/set 方法
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		this.username=username;
	}
	//属性 password 的 get/set 方法
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password=password;
	}

}
