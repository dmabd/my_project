package org.easybooks.test.model.vo;
import java.sql.Date;
public class LyTable implements java.io.Serializable{
	//Fields
	private Integer id;
	private Integer userId;
	private Date date;
	private String title;
	private String content;
	
	//Property accessors
	//���� id �� get/set ����
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	//���� userId �� get/set ����
	public Integer getUserId(){
		return this.userId;
	}
	public void setUserId(Integer userId){
		this.userId=userId;
	}
	//���� date �� get/set ����
	public Date getDate(){
		return this.date;
	}
	public void setDate(Date date){
		this.date=date;
	}
	//���� title �� get/set ����
	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title=title;
	}
	//���� content �� get/set ����
	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content=content;
	}
}
