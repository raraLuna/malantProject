package diary.model.vo;

public class MyDiaryPhotoes implements java.io.Serializable{

	private static final long serialVersionUID = 1030389240374785158L;
	
	private int photoId;
	private int diaryId;
	private String fileName;
	
	
	public MyDiaryPhotoes() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MyDiaryPhotoes(int photoId, int diaryId, String fileName) {
		super();
		this.photoId = photoId;
		this.diaryId = diaryId;
		this.fileName = fileName;
	}


	@Override
	public String toString() {
		return "MyDiaryPhotoes [photoId=" + photoId + ", diaryId=" + diaryId + ", fileName=" + fileName + "]";
	}


	public int getPhotoId() {
		return photoId;
	}


	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}


	public int getDiaryId() {
		return diaryId;
	}


	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
