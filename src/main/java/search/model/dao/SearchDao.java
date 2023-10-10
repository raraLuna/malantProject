package search.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import search.model.vo.Plant;

public class SearchDao {

	public ArrayList<Plant> selectPlantList(Connection conn, String keyword, int startRow, int endRow) {
		ArrayList<Plant> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String[] keywords = keyword.split(" ");
		
		// 쿼리 생성
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select * ");
		queryBuilder.append("from (select rownum rnum, A.* ");
		queryBuilder.append("	from (select * from plant order by plant_name) A ");
		queryBuilder.append("	where 1=1 ");
		for(int i = 0; i < keywords.length; i++) {
			queryBuilder.append("and plant_name like ? ");
		}
		queryBuilder.append(") ");
		queryBuilder.append("where rnum >= ? and rnum <= ?");
		String query = queryBuilder.toString();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			int cnt = 1;
			for(int i = 0; i < keywords.length; i++) {
				pstmt.setString(cnt++, "%" + keywords[i] + "%");
			}
			pstmt.setInt(cnt++, startRow);
			pstmt.setInt(cnt++, endRow);
			
			rset = pstmt.executeQuery();

			while(rset.next()) {
				Plant plant = new Plant();
				
				plant.setEcology(rset.getString("ecology"));
				plant.setViewType(rset.getString("view_type"));
				plant.setSmell(rset.getString("smell"));
				plant.setToxicity(rset.getString("toxicity"));
				plant.setDifficulty(rset.getString("difficulty"));
				plant.setRequiredManagement(rset.getString("required_management"));
				plant.setGrowthRate(rset.getString("growth_rate"));
				plant.setTemperature(rset.getString("temperature"));
				plant.setWinterTemperature(rset.getString("winter_temperature"));
				plant.setLight(rset.getString("light"));
				plant.setHumidity(rset.getString("humidity"));
				plant.setFertilizer(rset.getString("fertilizer"));
				plant.setSoil(rset.getString("soil"));
				plant.setWateringSpring(rset.getString("watering_spring"));
				plant.setWateringSummer(rset.getString("watering_summer"));
				plant.setWateringAutumn(rset.getString("watering_autumn"));
				plant.setWateringWinter(rset.getString("watering_winter"));
				plant.setEffectPurification(rset.getString("effect_purification"));
				plant.setPlacement(rset.getString("placement"));
				plant.setSeasonBlooming(rset.getString("season_blooming"));
				plant.setSeasonFruiting(rset.getString("season_fruiting"));
				plant.setSeasonPropagation(rset.getString("season_propagation"));
				plant.setPropagationMethod(rset.getString("propagation_method"));
				plant.setManagingDiseasesPests(rset.getString("managing_diseases_pests"));
				plant.setUsefulInfo(rset.getString("useful_info"));
				plant.setManagingTips(rset.getString("managing_tips"));
				plant.setCreatedDate(rset.getDate("created_date"));
				plant.setUpdateDate(rset.getDate("update_date"));
				plant.setViewCount(rset.getInt("view_count"));
				plant.setPlantNo(rset.getInt("plant_no"));
				plant.setPlantName(rset.getString("plant_name"));
				plant.setScientificName(rset.getString("scientific_name"));
				plant.setEnglishName(rset.getString("english_name"));
				plant.setFamilyName(rset.getString("family_name"));
				plant.setOriginalHabitat(rset.getString("original_habitat"));
				plant.setPlantImg(rset.getString("plant_img"));
				plant.setPlantThumbnail(rset.getString("plant_thumbnail"));
				plant.setUsecase(rset.getString("usecase"));
				plant.setGrowthWidth(rset.getInt("growth_width"));
				plant.setGrowthHeight(rset.getInt("growth_height"));
				plant.setLeafShape(rset.getString("leaf_shape"));
				plant.setLeafPattern(rset.getString("leaf_pattern"));
				plant.setLeafColor(rset.getString("leaf_color"));
				plant.setRootShape(rset.getString("root_shape"));
				plant.setGrowthForm(rset.getString("growth_form"));
				plant.setFlowerColor(rset.getString("flower_color"));
				plant.setFruitColor(rset.getString("fruit_color"));
				
				list.add(plant);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public Plant selectPlant(Connection conn, int plantNo) {
		Plant plant = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from plant where plant_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, plantNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				plant = new Plant();
				
				plant.setEcology(rset.getString("ecology"));
				plant.setViewType(rset.getString("view_type"));
				plant.setSmell(rset.getString("smell"));
				plant.setToxicity(rset.getString("toxicity"));
				plant.setDifficulty(rset.getString("difficulty"));
				plant.setRequiredManagement(rset.getString("required_management"));
				plant.setGrowthRate(rset.getString("growth_rate"));
				plant.setTemperature(rset.getString("temperature"));
				plant.setWinterTemperature(rset.getString("winter_temperature"));
				plant.setLight(rset.getString("light"));
				plant.setHumidity(rset.getString("humidity"));
				plant.setFertilizer(rset.getString("fertilizer"));
				plant.setSoil(rset.getString("soil"));
				plant.setWateringSpring(rset.getString("watering_spring"));
				plant.setWateringSummer(rset.getString("watering_summer"));
				plant.setWateringAutumn(rset.getString("watering_autumn"));
				plant.setWateringWinter(rset.getString("watering_winter"));
				plant.setEffectPurification(rset.getString("effect_purification"));
				plant.setPlacement(rset.getString("placement"));
				plant.setSeasonBlooming(rset.getString("season_blooming"));
				plant.setSeasonFruiting(rset.getString("season_fruiting"));
				plant.setSeasonPropagation(rset.getString("season_propagation"));
				plant.setPropagationMethod(rset.getString("propagation_method"));
				plant.setManagingDiseasesPests(rset.getString("managing_diseases_pests"));
				plant.setUsefulInfo(rset.getString("useful_info"));
				plant.setManagingTips(rset.getString("managing_tips"));
				plant.setCreatedDate(rset.getDate("created_date"));
				plant.setUpdateDate(rset.getDate("update_date"));
				plant.setViewCount(rset.getInt("view_count"));
				plant.setPlantNo(rset.getInt("plant_no"));
				plant.setPlantName(rset.getString("plant_name"));
				plant.setScientificName(rset.getString("scientific_name"));
				plant.setEnglishName(rset.getString("english_name"));
				plant.setFamilyName(rset.getString("family_name"));
				plant.setOriginalHabitat(rset.getString("original_habitat"));
				plant.setPlantImg(rset.getString("plant_img"));
				plant.setPlantThumbnail(rset.getString("plant_thumbnail"));
				plant.setUsecase(rset.getString("usecase"));
				plant.setGrowthWidth(rset.getInt("growth_width"));
				plant.setGrowthHeight(rset.getInt("growth_height"));
				plant.setLeafShape(rset.getString("leaf_shape"));
				plant.setLeafPattern(rset.getString("leaf_pattern"));
				plant.setLeafColor(rset.getString("leaf_color"));
				plant.setRootShape(rset.getString("root_shape"));
				plant.setGrowthForm(rset.getString("growth_form"));
				plant.setFlowerColor(rset.getString("flower_color"));
				plant.setFruitColor(rset.getString("fruit_color"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return plant;
	}

	public int getListCount(Connection conn, String keyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String[] keywords = keyword.split(" ");
		
		// 쿼리 생성
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select count(*) from plant ");
		queryBuilder.append("where 1=1 ");
		for(int i = 0; i < keywords.length; i++) {
			queryBuilder.append("and plant_name like ? ");
		}
		String query = queryBuilder.toString();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			int cnt = 1;
			for(int i = 0; i < keywords.length; i++) {
				pstmt.setString(cnt++, "%" + keywords[i] + "%");
			}
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	
	public int insertPlant(Connection conn, Plant plant) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into plant values ("
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
				+ ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
				+ ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
				+ ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
				+ ", ?, ?, ?, ?, ?, ?, ?, ?"
				+ ")";

		try {
			pstmt = conn.prepareStatement(query);
			
			int cnt = 1;
			pstmt.setInt(cnt++, plant.getPlantNo());
			pstmt.setString(cnt++, plant.getPlantName());
			pstmt.setString(cnt++, plant.getScientificName());
			pstmt.setString(cnt++, plant.getEnglishName());
			pstmt.setString(cnt++, plant.getFamilyName());
			pstmt.setString(cnt++, plant.getOriginalHabitat());
			pstmt.setString(cnt++, plant.getPlantImg());
			pstmt.setString(cnt++, plant.getPlantThumbnail());
			pstmt.setString(cnt++, plant.getUsecase());
			pstmt.setInt(cnt++, plant.getGrowthWidth());
			pstmt.setInt(cnt++, plant.getGrowthHeight());
			pstmt.setString(cnt++, plant.getLeafShape());
			pstmt.setString(cnt++, plant.getLeafPattern());
			pstmt.setString(cnt++, plant.getLeafColor());
			pstmt.setString(cnt++, plant.getRootShape());
			pstmt.setString(cnt++, plant.getGrowthForm());
			pstmt.setString(cnt++, plant.getFlowerColor());
			pstmt.setString(cnt++, plant.getFruitColor());
			pstmt.setString(cnt++, plant.getEcology());
			pstmt.setString(cnt++, plant.getViewType());
			pstmt.setString(cnt++, plant.getSmell());
			pstmt.setString(cnt++, plant.getToxicity());
			pstmt.setString(cnt++, plant.getDifficulty());
			pstmt.setString(cnt++, plant.getRequiredManagement());
			pstmt.setString(cnt++, plant.getGrowthRate());
			pstmt.setString(cnt++, plant.getTemperature());
			pstmt.setString(cnt++, plant.getWinterTemperature());
			pstmt.setString(cnt++, plant.getLight());
			pstmt.setString(cnt++, plant.getHumidity());
			pstmt.setString(cnt++, plant.getFertilizer());
			pstmt.setString(cnt++, plant.getSoil());
			pstmt.setString(cnt++, plant.getWateringSpring());
			pstmt.setString(cnt++, plant.getWateringSummer());
			pstmt.setString(cnt++, plant.getWateringAutumn());
			pstmt.setString(cnt++, plant.getWateringWinter());
			pstmt.setString(cnt++, plant.getEffectPurification());
			pstmt.setString(cnt++, plant.getPlacement());
			pstmt.setString(cnt++, plant.getSeasonBlooming());
			pstmt.setString(cnt++, plant.getSeasonFruiting());
			pstmt.setString(cnt++, plant.getSeasonPropagation());
			pstmt.setString(cnt++, plant.getPropagationMethod());
			pstmt.setString(cnt++, plant.getManagingDiseasesPests());
			pstmt.setString(cnt++, plant.getUsefulInfo());
			pstmt.setString(cnt++, plant.getManagingTips());
			pstmt.setDate(cnt++, plant.getCreatedDate());
			pstmt.setDate(cnt++, plant.getUpdateDate());
			pstmt.setInt(cnt++, plant.getViewCount());
			pstmt.setString(cnt++, plant.getAdviceInfo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getListCountByFilter(Connection conn, Map<String, String> filters) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 쿼리 생성
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select count(*) from plant ");
		queryBuilder.append("where 1=1 ");
		
		int total = 0;
		for(String key : filters.keySet()) {
			if(!"all".equals(filters.get(key))) {
				queryBuilder.append("and " + key + " = ? ");
				total++;
			}
		}
		
		String query = queryBuilder.toString();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			int cnt = 1;
			for(String key : filters.keySet()) {
				if(!filters.get(key).equals("all")) {
					pstmt.setString(cnt++, filters.get(key));
				}
			}
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}

	public ArrayList<Plant> selectPlantListByFilter(Connection conn, Map<String, String> filters, int startRow, int endRow) {
		ArrayList<Plant> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 쿼리 생성
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select * ");
		queryBuilder.append("from (select rownum rnum, plant.* from plant where 1=1 ");
		for(String key : filters.keySet()) {
			if(!"all".equals(filters.get(key))) {
				queryBuilder.append("and " + key + " = ? ");
			}
		}
		queryBuilder.append(") ");
		queryBuilder.append("where rnum >= ? and rnum <= ?");
		
		String query = queryBuilder.toString();
		System.out.println(query);
		
		try {
			pstmt = conn.prepareStatement(query);
			
			int cnt = 1;
			for(String key : filters.keySet()) {
				if(!filters.get(key).equals("all")) {
					pstmt.setString(cnt++, filters.get(key));
				}
			}
			pstmt.setInt(cnt++, startRow);
			pstmt.setInt(cnt++, endRow);
			
			rset = pstmt.executeQuery();

			while(rset.next()) {
				Plant plant = new Plant();
				
				plant.setEcology(rset.getString("ecology"));
				plant.setViewType(rset.getString("view_type"));
				plant.setSmell(rset.getString("smell"));
				plant.setToxicity(rset.getString("toxicity"));
				plant.setDifficulty(rset.getString("difficulty"));
				plant.setRequiredManagement(rset.getString("required_management"));
				plant.setGrowthRate(rset.getString("growth_rate"));
				plant.setTemperature(rset.getString("temperature"));
				plant.setWinterTemperature(rset.getString("winter_temperature"));
				plant.setLight(rset.getString("light"));
				plant.setHumidity(rset.getString("humidity"));
				plant.setFertilizer(rset.getString("fertilizer"));
				plant.setSoil(rset.getString("soil"));
				plant.setWateringSpring(rset.getString("watering_spring"));
				plant.setWateringSummer(rset.getString("watering_summer"));
				plant.setWateringAutumn(rset.getString("watering_autumn"));
				plant.setWateringWinter(rset.getString("watering_winter"));
				plant.setEffectPurification(rset.getString("effect_purification"));
				plant.setPlacement(rset.getString("placement"));
				plant.setSeasonBlooming(rset.getString("season_blooming"));
				plant.setSeasonFruiting(rset.getString("season_fruiting"));
				plant.setSeasonPropagation(rset.getString("season_propagation"));
				plant.setPropagationMethod(rset.getString("propagation_method"));
				plant.setManagingDiseasesPests(rset.getString("managing_diseases_pests"));
				plant.setUsefulInfo(rset.getString("useful_info"));
				plant.setManagingTips(rset.getString("managing_tips"));
				plant.setCreatedDate(rset.getDate("created_date"));
				plant.setUpdateDate(rset.getDate("update_date"));
				plant.setViewCount(rset.getInt("view_count"));
				plant.setPlantNo(rset.getInt("plant_no"));
				plant.setPlantName(rset.getString("plant_name"));
				plant.setScientificName(rset.getString("scientific_name"));
				plant.setEnglishName(rset.getString("english_name"));
				plant.setFamilyName(rset.getString("family_name"));
				plant.setOriginalHabitat(rset.getString("original_habitat"));
				plant.setPlantImg(rset.getString("plant_img"));
				plant.setPlantThumbnail(rset.getString("plant_thumbnail"));
				plant.setUsecase(rset.getString("usecase"));
				plant.setGrowthWidth(rset.getInt("growth_width"));
				plant.setGrowthHeight(rset.getInt("growth_height"));
				plant.setLeafShape(rset.getString("leaf_shape"));
				plant.setLeafPattern(rset.getString("leaf_pattern"));
				plant.setLeafColor(rset.getString("leaf_color"));
				plant.setRootShape(rset.getString("root_shape"));
				plant.setGrowthForm(rset.getString("growth_form"));
				plant.setFlowerColor(rset.getString("flower_color"));
				plant.setFruitColor(rset.getString("fruit_color"));
				
				list.add(plant);
				System.out.println(plant); // 테스트용 로그
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

}
