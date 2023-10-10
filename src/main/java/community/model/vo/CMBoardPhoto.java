package community.model.vo;

public class CMBoardPhoto implements java.io.Serializable {

	private static final long serialVersionUID = -6773942650807110628L;

	private int photoNo;
	private int boardNo;
	private String filename;

	public CMBoardPhoto() {
		super();
	}

	public CMBoardPhoto(int photoNo, int boardNo, String filename) {
		super();
		this.photoNo = photoNo;
		this.boardNo = boardNo;
		this.filename = filename;
	}

	public int getPhotoNo() {
		return photoNo;
	}

	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CMBoardPhoto [photoNo=" + photoNo + ", boardNo=" + boardNo + ", filename=" + filename + "]";
	}

}
