package DB.Msg;

public class Msg_DTO {
	private int idx = 0; // ������ȣ
	private String recipient = null; // �޴»��
	private String title = null; // ����
	private String text = null; // ����
	private String wdate = null; // ���� ��¥
	private String wtime = null; // ���� �ð�
	private String sender = null; // �޴� ���
	
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
			return "���� ��ȣ : " + idx +
					" �޴� ��� : " + recipient +
					" ���� : " + title +
					" ����  :" + text + 
					" �� ��¥ : " + wdate +
					" �� �ð� : " + wtime + 
					" �����»�� : " + sender;
		}
	
	
}
