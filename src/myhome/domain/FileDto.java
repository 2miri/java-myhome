package myhome.domain;

/**
 * 
 * @author miri
 * 파일 DTO
 *
 */
public class FileDto implements Dto {
	private int no;
	private String filename;
	private String filepath;
	private int uploaderNo;
	private int count;
	private String regdate;
	
	private String uploaderNickname;
	
	public FileDto() {}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public int getUploaderNo() {
		return uploaderNo;
	}

	public void setUploaderNo(int uploaderNo) {
		this.uploaderNo = uploaderNo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getUploaderNickname() {
		return uploaderNickname;
	}

	public void setUploaderNickname(String uploaderNickname) {
		this.uploaderNickname = uploaderNickname;
	}
	
	
}
