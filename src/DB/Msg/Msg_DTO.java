package DB.Msg;

public class Msg_DTO {
	private int idx = 0; // 쪽지번호
	private String recipient = null; // 받는사람
	private String title = null; // 제목
	private String text = null; // 내용
	private String wdate = null; // 보낸 날짜
	private String wtime = null; // 보낸 시간
	private String sender = null; // 받는 사람
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getWtime() {
		return wtime;
	}
	public void setWtime(String wtime) {
		this.wtime = wtime;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	@Override
		public String toString() {
			return "쪽지 번호 : " + idx +
					" 받는 사람 : " + recipient +
					" 제목 : " + title +
					" 내용  :" + text + 
					" 쓴 날짜 : " + wdate +
					" 쓴 시간 : " + wtime + 
					" 보내는사람 : " + sender;
		}
	
	
}
