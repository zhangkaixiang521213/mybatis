
package zhangkaix.mybatistt.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import core.domain.DomainBase;
import core.mybatis.MyBatisDomain;

@MyBatisDomain
public class Mytest extends DomainBase{
	
	private long id;
	private long para1;
	private long para3;
	private long para4;
	private float para2;
	private String para5;
	private Date mydatetime;

	public Mytest() {
		
	}
	
	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getPara1() {
		return para1;
	}



	public void setPara1(long para1) {
		this.para1 = para1;
	}



	public long getPara3() {
		return para3;
	}



	public void setPara3(long para3) {
		this.para3 = para3;
	}



	public long getPara4() {
		return para4;
	}



	public void setPara4(long para4) {
		this.para4 = para4;
	}



	public float getPara2() {
		return para2;
	}



	public void setPara2(float para2) {
		this.para2 = para2;
	}



	public String getPara5() {
		return para5;
	}



	public void setPara5(String para5) {
		this.para5 = para5;
	}



	public Date getMydatetime() {
		return mydatetime;
	}



	public void setMydatetime(Date mydatetime) {
		this.mydatetime = mydatetime;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}