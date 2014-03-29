package ca.techguys.takemynotes.beans;

import java.io.Serializable;

public class Comment implements Serializable{
	private String cid;
	private String userid;
	private String commt;
	private String commetDate;
	private String NoteId;
	private String name;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCommt() {
		return commt;
	}
	public void setCommt(String commt) {
		this.commt = commt;
	}
	public String getCommetDate() {
		return commetDate;
	}
	public void setCommetDate(String commetDate) {
		this.commetDate = commetDate;
	}
	public String getNoteId() {
		return NoteId;
	}
	public void setNoteId(String noteId) {
		NoteId = noteId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", userid=" + userid + ", commt="
				+ commt + ", commetDate=" + commetDate + ", NoteId=" + NoteId
				+ ", name=" + name + "]";
	}
	
	
}
