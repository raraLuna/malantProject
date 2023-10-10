package community.model.vo;

public class CMHashtag implements java.io.Serializable {

	private static final long serialVersionUID = 3628751967069567914L;

	private int hashtagNo;
	private String hashtagContent;

	public CMHashtag() {
		super();
	}

	public CMHashtag(int hashtagNo, String hashtagContent) {
		super();
		this.hashtagNo = hashtagNo;
		this.hashtagContent = hashtagContent;
	}

	public int getHashtagNo() {
		return hashtagNo;
	}

	public void setHashtagNo(int hashtagNo) {
		this.hashtagNo = hashtagNo;
	}

	public String getHashtagContent() {
		return hashtagContent;
	}

	public void setHashtagContent(String hashtagContent) {
		this.hashtagContent = hashtagContent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CMHashtag [hashtagNo=" + hashtagNo + ", hashtagContent=" + hashtagContent + "]";
	}

}
