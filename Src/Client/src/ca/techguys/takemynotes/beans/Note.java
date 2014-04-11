package ca.techguys.takemynotes.beans;

import java.io.Serializable;

public class Note implements Serializable {
	
	private String idNotes;
	private String NoteName;
	public String getIdNotes() {
		return idNotes;
	}
	public void setIdNotes(String idNotes) {
		this.idNotes = idNotes;
	}
	public String getNoteName() {
		return NoteName;
	}
	public void setNoteName(String noteName) {
		NoteName = noteName;
	}
	public String getCateId() {
		return CateId;
	}
	public void setCateId(String cateId) {
		CateId = cateId;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getPubUserId() {
		return pubUserId;
	}
	public void setPubUserId(String pubUserId) {
		this.pubUserId = pubUserId;
	}
	public String getDateCreate() {
		return DateCreate;
	}
	public void setDateCreate(String dateCreate) {
		DateCreate = dateCreate;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getNoteImg() {
		return NoteImg;
	}
	public void setNoteImg(String noteImg) {
		NoteImg = noteImg;
	}
	@Override
	public String toString() {
		return "Note [idNotes=" + idNotes + ", NoteName=" + NoteName
				+ ", CateId=" + CateId + ", Description=" + Description
				+ ", Price=" + Price + ", pubUserId=" + pubUserId
				+ ", DateCreate=" + DateCreate + ", Status=" + Status
				+ ", URL=" + URL + ", NoteImg=" + NoteImg + "]";
	}
	private String CateId;
	private String Description;
	private String Price;
	private String pubUserId;
	private String DateCreate;
	private String Status;
	private String URL;
	private String NoteImg;
}
