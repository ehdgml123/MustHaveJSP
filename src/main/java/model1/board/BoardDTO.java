package model1.board;

import java.sql.Date;

/*
 * CREATE TABLE board(
	num NUMBER PRIMARY KEY,
	title varchar2(200) NOT NULL,
	content varchar2(2000) NOT NULL,
	id varchar2(10) NOT NULL,
	postdate DATE DEFAULT sysdate NOT NULL,
	visitcount number(6)
);
 */
		
public class BoardDTO {
	
	private String num;
	private String title;
	private String content;
	private String id;
	private Date postdate;
	private String visitcount;
	
	private String name;   //Board db없고, Member db에 존재
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getPostdate() {
		return postdate;
	}
	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}
	public String getVisitcount() {
		return visitcount;
	}
	public void setVisitcount(String visitcount) {
		this.visitcount = visitcount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
