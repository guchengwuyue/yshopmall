package co.yixiang.common.web.vo;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "ID-NAME-VO")
public class CommonIdName {

	@Schema(description = "id")
	private String id;
	
	@Schema(description = "名称")
	private String name;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public CommonIdName(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		
	}


	public CommonIdName() {
		super();
	}


	@Override
	public String toString() {
		return "CommonIdName [id=" + id + ", name=" + name + "]";
	}


	
	
}
