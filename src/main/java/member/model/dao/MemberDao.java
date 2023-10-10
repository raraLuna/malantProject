package member.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.print.attribute.PrintJobAttribute;

import member.model.vo.Admin;
import member.model.vo.Member;
import member.model.vo.Seller;

public class MemberDao {

	public Member selectLogin(Connection conn, String userId, String userPwd) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from member where user_id = ? and user_pwd = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				member = new Member();
				
				member.setUserNo(rset.getString("user_no"));
				member.setUserId(rset.getString("user_id"));
				member.setUserPwd(rset.getString("user_pwd"));
				member.setPwdUpdateDate(rset.getDate("pwd_update_date"));
				member.setEmail(rset.getString("email"));
				member.setNickname(rset.getString("nickname"));
				member.setProfileImg(rset.getString("profile_img"));
				member.setSignType(rset.getString("sign_type"));
				member.setAlarmYn(rset.getString("alarm_yn"));
				member.setNoticeYn(rset.getString("notice_yn"));
				member.setBlockedYn(rset.getString("blocked_yn"));
				member.setCreatedDate(rset.getDate("created_date"));
				member.setLastLoginDate(rset.getDate("last_login_date"));
				member.setDormantYn(rset.getString("dormant_yn"));
				member.setWithdrawalYn(rset.getString("withdrawal_yn"));
				member.setSellerYn(rset.getString("seller_yn"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}

	public int[] selectCheckDuplicate(Connection conn, String userId, String email) {
		int[] result = {0, 0};
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select (select count(user_id) from member where user_id = ?), "
				+ "(select count(email) from member where email = ?) from dual";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result[0] = rset.getInt(1);
				result[1] = rset.getInt(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into member (user_no, user_id, user_pwd, nickname, email, sign_type, alarm_yn, notice_yn) "
				+ "values (replace(? || '-' || decode((select to_char(max(to_number(substr(user_no, 5))) + 1, '000000') from member), null, '000001', (select to_char(max(to_number(substr(user_no, 5))) + 1, '000000') from member)), ' ', ''), "
				+ "?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getSignType().substring(0, 3).toUpperCase());
			pstmt.setString(2, member.getUserId());
			pstmt.setString(3, member.getUserPwd());
			pstmt.setString(4, member.getNickname());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getSignType());
			pstmt.setString(7, member.getAlarmYn());
			pstmt.setString(8, member.getNoticeYn());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertSeller(Connection conn, Seller seller) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into st_seller (seller_no, business_no, seller_id, seller_pwd, contact, email, store_name, address) "
				+ "values (replace(? || (select to_char(max(to_number(substr(seller_no, 4))) + 1, '0000000') from st_seller), ' ', ''), ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "SEL");
			pstmt.setString(2, seller.getBusinessNo());
			pstmt.setString(3, seller.getSellerId());
			pstmt.setString(4, seller.getSellerPwd());
			pstmt.setString(5, seller.getContact());
			pstmt.setString(6, seller.getEmail());
			pstmt.setString(7, seller.getStoreName());
			pstmt.setString(8, seller.getAddress());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member selectMember(Connection conn, String userId) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from member where user_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member();
				
				member.setUserNo(rset.getString("user_no"));
				member.setUserId(rset.getString("user_id"));
				member.setUserPwd(rset.getString("user_pwd"));
				member.setNickname(rset.getString("nickname"));
				member.setEmail(rset.getString("email"));
				member.setProfileImg(rset.getString("profile_img"));
				member.setSignType(rset.getString("sign_type"));
				member.setPwdUpdateDate(rset.getDate("pwd_update_date"));
				member.setAlarmYn(rset.getString("alarm_yn"));
				member.setNoticeYn(rset.getString("notice_yn"));
				member.setBlockedYn(rset.getString("blocked_yn"));
				member.setDormantYn(rset.getString("dormant_yn"));
				member.setWithdrawalYn(rset.getString("withdrawal_yn"));
				member.setCreatedDate(rset.getDate("created_date"));
				member.setLastLoginDate(rset.getDate("last_login_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}

	public int updateMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("update member ")
					.append("set info_update_date = sysdate")
					.append(", alarm_yn = ?")
					.append(", notice_yn = ?");
		
		if(member.getNickname() != null) {
			queryBuilder.append(", nickname = ?");
		}
		if(member.getEmail() != null) {
			queryBuilder.append(", email = ?");
		}
		if(member.getUserPwd() != null) {
			queryBuilder.append(", user_pwd = ?");
			queryBuilder.append(", pwd_update_date = ?");
		}
		if(member.getLastLoginDate() != null) {
			queryBuilder.append(", last_login_date = ?");
		}
		
		queryBuilder.append(" where user_id = ?");
		
		String query = queryBuilder.toString();
		
		try {
			int cnt = 1;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(cnt++, member.getAlarmYn());
			pstmt.setString(cnt++, member.getNoticeYn());
			if(member.getNickname() != null) {
				pstmt.setString(cnt++, member.getNickname());
			}
			if(member.getEmail() != null) {
				pstmt.setString(cnt++, member.getEmail());
			}
			if(member.getUserPwd() != null) {
				pstmt.setString(cnt++, member.getUserPwd());
				pstmt.setDate(cnt++, member.getPwdUpdateDate());
			}
			if(member.getLastLoginDate() != null) {
				pstmt.setDate(cnt++, member.getLastLoginDate());
			}
			pstmt.setString(cnt++, member.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member selectMemberByUserNo(Connection conn, String userNo) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from member where user_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member();
				
				member.setUserNo(rset.getString("user_no"));
				member.setUserId(rset.getString("user_id"));
				member.setUserPwd(rset.getString("user_pwd"));
				member.setNickname(rset.getString("nickname"));
				member.setEmail(rset.getString("email"));
				member.setProfileImg(rset.getString("profile_img"));
				member.setSignType(rset.getString("sign_type"));
				member.setPwdUpdateDate(rset.getDate("pwd_update_date"));
				member.setAlarmYn(rset.getString("alarm_yn"));
				member.setNoticeYn(rset.getString("notice_yn"));
				member.setBlockedYn(rset.getString("blocked_yn"));
				member.setDormantYn(rset.getString("dormant_yn"));
				member.setWithdrawalYn(rset.getString("withdrawal_yn"));
				member.setCreatedDate(rset.getDate("created_date"));
				member.setLastLoginDate(rset.getDate("last_login_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}

	public int updateWithdrawal(Connection conn, String userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update member "
				+ "set withdrawal_yn = decode((select WITHDRAWAL_YN from member where user_no = ?), 'Y', 'N', 'Y') "
				+ "where user_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNo);
			pstmt.setString(2, userNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertWithdrawal(Connection conn, String userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into mb_withdrawal "
				+ "values (?, sysdate, sysdate + 15)";				
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Date selectWithdrawalDate(Connection conn, String userNo) {
		Date withdrawalDate = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select withdrawal_date from mb_withdrawal "
				+ "where user_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				withdrawalDate = rset.getDate(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return withdrawalDate;		
	}

	public int deleteWithdrawal(Connection conn, String userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from mb_withdrawal "
				+ "where user_no = ?";				
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Admin selectAdminLogin(Connection conn, String userId, String userPwd) {
		Admin admin = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from admin "
				+ "where admin_id = ? and admin_pwd = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				admin = new Admin();
				
				admin.setAdminNo(rset.getString("admin_no"));
				admin.setAdminId(rset.getString("admin_id"));
				admin.setAdminPwd(rset.getString("admin_pwd"));
				admin.setName(rset.getString("name"));
				admin.setAdminType(rset.getString("admin_type"));
				admin.setCreatedDate(rset.getDate("created_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return admin;
	}

	public Seller selectSellerLogin(Connection conn, String userId, String userPwd) {
		Seller seller = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from st_seller "
				+ "where seller_id = ? and seller_pwd = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				seller = new Seller();
				
				seller.setSellerNo(rset.getString("seller_no"));
				seller.setBusinessNo(rset.getString("business_no"));
				seller.setSellerId(rset.getString("seller_id"));
				seller.setSellerPwd(rset.getString("seller_pwd"));
				seller.setStoreName(rset.getString("store_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return seller;
	}

	

}
