package DB.Board;

public class QBoard_DTO {
	private int idx ;
	private String id ;
	private String name;
	private String title;
	private String text;
	private String wdate;
	private String wtime;
	
	public QBoard_DTO() {
		idx = 0 ;
		id = null;
		name = null;
		title = null;
		text = null;
		wdate = null;
		wtime = null;
	}
	
	public QBoard_DTO(String id , String title , String text) {
		idx = 0 ;
		this.id = id;
		name = null;
		this.title = title;
		this.text = text;
		wdate = null;
		wtime = null;
	}

	public int getIdx() {
		return idx;
	}


	public void setIdx(int idx) {
		this.idx = idx;
	}


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


	@Override
	public String toString() {
		return "THRMS_Qboard : " + idx +", "+ id+", " + name+ ", "  +title + ", " 
						+ text +", " + wdate +", " + wtime ;
	}
	
}
