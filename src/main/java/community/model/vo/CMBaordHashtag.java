package community.model.vo;

public class CMBaordHashtag implements java.io.Serializable {

	private static final long serialVersionUID = -4026441628172476335L;

	private int boardNo;
	private int hashtagNo;

	public CMBaordHashtag() {
		super();
	}

	public CMBaordHashtag(int boardNo, int hashtagNo) {
		super();
		this.boardNo = boardNo;
		this.hashtagNo = hashtagNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getHashtagNo() {
		return hashtagNo;
	}

	public void setHashtagNo(int hashtagNo) {
		this.hashtagNo = hashtagNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CMBaordHashtag [boardNo=" + boardNo + ", hashtagNo=" + hashtagNo + "]";
	}

}
