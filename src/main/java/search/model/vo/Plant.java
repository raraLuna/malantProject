package search.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Plant implements Serializable {

	private static final long serialVersionUID = -4570280134544732042L;

	private int plantNo;
	private String plantName;
	private String scientificName;
	private String englishName;
	private String familyName;
	private String originalHabitat;
	private String plantImg;
	private String plantThumbnail;
	private String usecase;
	private int growthWidth;
	private int growthHeight;
	private String leafShape;
	private String leafPattern;
	private String leafColor;
	private String rootShape;
	private String growthForm;
	private String flowerColor;
	private String fruitColor;
	private String ecology;
	private String viewType;
	private String smell;
	private String toxicity;
	private String difficulty;
	private String requiredManagement;
	private String growthRate;
	private String temperature;
	private String winterTemperature;
	private String light;
	private String humidity;
	private String fertilizer;
	private String soil;
	private String wateringSpring;
	private String wateringSummer;
	private String wateringAutumn;
	private String wateringWinter;
	private String effectPurification;
	private String placement;
	private String seasonBlooming;
	private String seasonFruiting;
	private String seasonPropagation;
	private String propagationMethod;
	private String managingDiseasesPests;
	private String usefulInfo;
	private String managingTips;
	private Date createdDate;
	private Date updateDate;
	private int viewCount;
	private String adviceInfo;
	
	public Plant() {
	}

	public Plant(int plantNo, String plantName) {
		this.plantNo = plantNo;
		this.plantName = plantName;
	}

	public Plant(int plantNo, String plantName, String scientificName, String englishName, String familyName,
			String originalHabitat, String plantImg, String plantThumbnail, String usecase, int growthWidth,
			int growthHeight, String leafShape, String leafPattern, String leafColor, String rootShape,
			String growthForm, String flowerColor, String fruitColor, String ecology, String viewType, String smell,
			String toxicity, String difficulty, String requiredManagement, String growthRate, String temperature,
			String winterTemperature, String light, String humidity, String fertilizer, String soil,
			String wateringSpring, String wateringSummer, String wateringAutumn, String wateringWinter,
			String effectPurification, String placement, String seasonBlooming, String seasonFruiting,
			String seasonPropagation, String propagationMethod, String managingDiseasesPests, String usefulInfo,
			String managingTips, Date createdDate, Date updateDate, int viewCount, String adviceInfo) {
		this.plantNo = plantNo;
		this.plantName = plantName;
		this.scientificName = scientificName;
		this.englishName = englishName;
		this.familyName = familyName;
		this.originalHabitat = originalHabitat;
		this.plantImg = plantImg;
		this.plantThumbnail = plantThumbnail;
		this.usecase = usecase;
		this.growthWidth = growthWidth;
		this.growthHeight = growthHeight;
		this.leafShape = leafShape;
		this.leafPattern = leafPattern;
		this.leafColor = leafColor;
		this.rootShape = rootShape;
		this.growthForm = growthForm;
		this.flowerColor = flowerColor;
		this.fruitColor = fruitColor;
		this.ecology = ecology;
		this.viewType = viewType;
		this.smell = smell;
		this.toxicity = toxicity;
		this.difficulty = difficulty;
		this.requiredManagement = requiredManagement;
		this.growthRate = growthRate;
		this.temperature = temperature;
		this.winterTemperature = winterTemperature;
		this.light = light;
		this.humidity = humidity;
		this.fertilizer = fertilizer;
		this.soil = soil;
		this.wateringSpring = wateringSpring;
		this.wateringSummer = wateringSummer;
		this.wateringAutumn = wateringAutumn;
		this.wateringWinter = wateringWinter;
		this.effectPurification = effectPurification;
		this.placement = placement;
		this.seasonBlooming = seasonBlooming;
		this.seasonFruiting = seasonFruiting;
		this.seasonPropagation = seasonPropagation;
		this.propagationMethod = propagationMethod;
		this.managingDiseasesPests = managingDiseasesPests;
		this.usefulInfo = usefulInfo;
		this.managingTips = managingTips;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.viewCount = viewCount;
		this.adviceInfo = adviceInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getPlantNo() {
		return plantNo;
	}

	public String getPlantName() {
		return plantName;
	}

	public String getScientificName() {
		return scientificName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public String getOriginalHabitat() {
		return originalHabitat;
	}

	public String getPlantImg() {
		return plantImg;
	}

	public String getPlantThumbnail() {
		return plantThumbnail;
	}

	public String getUsecase() {
		return usecase;
	}

	public int getGrowthWidth() {
		return growthWidth;
	}

	public int getGrowthHeight() {
		return growthHeight;
	}

	public String getLeafShape() {
		return leafShape;
	}

	public String getLeafPattern() {
		return leafPattern;
	}

	public String getLeafColor() {
		return leafColor;
	}

	public String getRootShape() {
		return rootShape;
	}

	public String getGrowthForm() {
		return growthForm;
	}

	public String getFlowerColor() {
		return flowerColor;
	}

	public String getFruitColor() {
		return fruitColor;
	}

	public String getEcology() {
		return ecology;
	}

	public String getViewType() {
		return viewType;
	}

	public String getSmell() {
		return smell;
	}

	public String getToxicity() {
		return toxicity;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public String getRequiredManagement() {
		return requiredManagement;
	}

	public String getGrowthRate() {
		return growthRate;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getWinterTemperature() {
		return winterTemperature;
	}

	public String getLight() {
		return light;
	}

	public String getHumidity() {
		return humidity;
	}

	public String getFertilizer() {
		return fertilizer;
	}

	public String getSoil() {
		return soil;
	}

	public String getWateringSpring() {
		return wateringSpring;
	}

	public String getWateringSummer() {
		return wateringSummer;
	}

	public String getWateringAutumn() {
		return wateringAutumn;
	}

	public String getWateringWinter() {
		return wateringWinter;
	}

	public String getEffectPurification() {
		return effectPurification;
	}

	public String getPlacement() {
		return placement;
	}

	public String getSeasonBlooming() {
		return seasonBlooming;
	}

	public String getSeasonFruiting() {
		return seasonFruiting;
	}

	public String getSeasonPropagation() {
		return seasonPropagation;
	}

	public String getPropagationMethod() {
		return propagationMethod;
	}

	public String getManagingDiseasesPests() {
		return managingDiseasesPests;
	}

	public String getUsefulInfo() {
		return usefulInfo;
	}

	public String getManagingTips() {
		return managingTips;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public String getAdviceInfo() {
		return adviceInfo;
	}

	public void setPlantNo(int plantNo) {
		this.plantNo = plantNo;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public void setOriginalHabitat(String originalHabitat) {
		this.originalHabitat = originalHabitat;
	}

	public void setPlantImg(String plantImg) {
		this.plantImg = plantImg;
	}

	public void setPlantThumbnail(String plantThumbnail) {
		this.plantThumbnail = plantThumbnail;
	}

	public void setUsecase(String usecase) {
		this.usecase = usecase;
	}

	public void setGrowthWidth(int growthWidth) {
		this.growthWidth = growthWidth;
	}

	public void setGrowthHeight(int growthHeight) {
		this.growthHeight = growthHeight;
	}

	public void setLeafShape(String leafShape) {
		this.leafShape = leafShape;
	}

	public void setLeafPattern(String leafPattern) {
		this.leafPattern = leafPattern;
	}

	public void setLeafColor(String leafColor) {
		this.leafColor = leafColor;
	}

	public void setRootShape(String rootShape) {
		this.rootShape = rootShape;
	}

	public void setGrowthForm(String growthForm) {
		this.growthForm = growthForm;
	}

	public void setFlowerColor(String flowerColor) {
		this.flowerColor = flowerColor;
	}

	public void setFruitColor(String fruitColor) {
		this.fruitColor = fruitColor;
	}

	public void setEcology(String ecology) {
		this.ecology = ecology;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public void setSmell(String smell) {
		this.smell = smell;
	}

	public void setToxicity(String toxicity) {
		this.toxicity = toxicity;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public void setRequiredManagement(String requiredManagement) {
		this.requiredManagement = requiredManagement;
	}

	public void setGrowthRate(String growthRate) {
		this.growthRate = growthRate;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public void setWinterTemperature(String winterTemperature) {
		this.winterTemperature = winterTemperature;
	}

	public void setLight(String light) {
		this.light = light;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public void setFertilizer(String fertilizer) {
		this.fertilizer = fertilizer;
	}

	public void setSoil(String soil) {
		this.soil = soil;
	}

	public void setWateringSpring(String wateringSpring) {
		this.wateringSpring = wateringSpring;
	}

	public void setWateringSummer(String wateringSummer) {
		this.wateringSummer = wateringSummer;
	}

	public void setWateringAutumn(String wateringAutumn) {
		this.wateringAutumn = wateringAutumn;
	}

	public void setWateringWinter(String wateringWinter) {
		this.wateringWinter = wateringWinter;
	}

	public void setEffectPurification(String effectPurification) {
		this.effectPurification = effectPurification;
	}

	public void setPlacement(String placement) {
		this.placement = placement;
	}

	public void setSeasonBlooming(String seasonBlooming) {
		this.seasonBlooming = seasonBlooming;
	}

	public void setSeasonFruiting(String seasonFruiting) {
		this.seasonFruiting = seasonFruiting;
	}

	public void setSeasonPropagation(String seasonPropagation) {
		this.seasonPropagation = seasonPropagation;
	}

	public void setPropagationMethod(String propagationMethod) {
		this.propagationMethod = propagationMethod;
	}

	public void setManagingDiseasesPests(String managingDiseasesPests) {
		this.managingDiseasesPests = managingDiseasesPests;
	}

	public void setUsefulInfo(String usefulInfo) {
		this.usefulInfo = usefulInfo;
	}

	public void setManagingTips(String managingTips) {
		this.managingTips = managingTips;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public void setAdviceInfo(String adviceInfo) {
		this.adviceInfo = adviceInfo;
	}

	@Override
	public String toString() {
		return "Plant [plantNo=" + plantNo + ", plantName=" + plantName + ", scientificName=" + scientificName
				+ ", englishName=" + englishName + ", familyName=" + familyName + ", originalHabitat=" + originalHabitat
				+ ", plantImg=" + plantImg + ", plantThumbnail=" + plantThumbnail + ", usecase=" + usecase
				+ ", growthWidth=" + growthWidth + ", growthHeight=" + growthHeight + ", leafShape=" + leafShape
				+ ", leafPattern=" + leafPattern + ", leafColor=" + leafColor + ", rootShape=" + rootShape
				+ ", growthForm=" + growthForm + ", flowerColor=" + flowerColor + ", fruitColor=" + fruitColor
				+ ", ecology=" + ecology + ", viewType=" + viewType + ", smell=" + smell + ", toxicity=" + toxicity
				+ ", difficulty=" + difficulty + ", requiredManagement=" + requiredManagement + ", growthRate="
				+ growthRate + ", temperature=" + temperature + ", winterTemperature=" + winterTemperature + ", light="
				+ light + ", humidity=" + humidity + ", fertilizer=" + fertilizer + ", soil=" + soil
				+ ", wateringSpring=" + wateringSpring + ", wateringSummer=" + wateringSummer + ", wateringAutumn="
				+ wateringAutumn + ", wateringWinter=" + wateringWinter + ", effectPurification=" + effectPurification
				+ ", placement=" + placement + ", seasonBlooming=" + seasonBlooming + ", seasonFruiting="
				+ seasonFruiting + ", seasonPropagation=" + seasonPropagation + ", propagationMethod="
				+ propagationMethod + ", managingDiseasesPests=" + managingDiseasesPests + ", usefulInfo=" + usefulInfo
				+ ", managingTips=" + managingTips + ", createdDate=" + createdDate + ", updateDate=" + updateDate
				+ ", viewCount=" + viewCount + ", adviceInfo=" + adviceInfo + "]";
	}
	
}
