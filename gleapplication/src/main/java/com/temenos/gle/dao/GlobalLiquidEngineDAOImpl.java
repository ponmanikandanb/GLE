package com.temenos.gle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.temenos.gle.constant.CommonConstants;
import com.temenos.gle.model.AccountDetails;
import com.temenos.gle.util.AES;

@Component
public class GlobalLiquidEngineDAOImpl implements GlobalLiquidEngineDAO {

	JdbcTemplate jdbcTemplate;
	DataSource dataSource;
	private static final String FOLICAL_KEY = CommonConstants.FOLICAL_KEY;
	
	@Autowired
	public GlobalLiquidEngineDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/***
	 * 
	 */
	@Override
	public boolean insertIntoCodexData(List<String> codexDetails) throws SQLException {
		String customerArray[] = null;
		String userDetails[] = null;
		String details = CommonConstants.EMPTY_STRING;
		for (String string : codexDetails) {
			customerArray = string.split(CommonConstants.DOLLAR_SYMBOL);
		}
		List<String> customerList = Arrays.asList(customerArray);
		for (int i = 0; i < customerList.size(); i++) {
			String contentArray[] = customerList.get(i).split(CommonConstants.AT_SYMBOL);
			userDetails = contentArray[0].split(CommonConstants.AMPERSAND_SYMBOL);
			details = contentArray[1];
			details = checkAccountExists(userDetails,details);
			addOrUpdateAccount(userDetails,details);
			insertCodexKey(userDetails,details);
		}
		return false;
	}

	@Override
	public void accountList(String clinetName) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AccountDetails> getAccountListByClinetName(String clinetName) {
		Connection con = null;
		PreparedStatement prepareStat = null;
		List<AccountDetails> listAcctDetails = new ArrayList<AccountDetails>();
		try {
			con = dataSource.getConnection();
			prepareStat = con.prepareStatement(
					"SELECT * FROM GLOBAL_BANK_ACCOUNTDETAILS where CLIENTNAME like '%" + clinetName + "%'");
			ResultSet resultSet = prepareStat.executeQuery();
			while (resultSet != null && resultSet.next()) {
				AccountDetails details = new AccountDetails();
				details.setAccountNumber(resultSet.getString("ACCOUNTNUMBER"));
				details.setBranchCode(resultSet.getString("BANCHCODE"));
				details.setCountry(resultSet.getString("COUNTRYCODE"));
				listAcctDetails.add(details);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
				prepareStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return listAcctDetails;
	}

	@Override
	public List<AccountDetails> getAccountListInGroup(String groupId) {
		Connection con = null;
		PreparedStatement prepareStat = null;
		List<AccountDetails> listAcctDetails = new ArrayList<AccountDetails>();
		try {
			con = dataSource.getConnection();
			prepareStat = con.prepareStatement("SELECT * FROM GROUP_ID_CONFIG where GROUPID = ? ");
			prepareStat.setString(1, groupId);
			ResultSet resultSet = prepareStat.executeQuery();
			while (resultSet != null && resultSet.next()) {
				AccountDetails details = new AccountDetails();
				details.setAccountNumber(resultSet.getString("ACCOUNTNUMBER"));
				details.setBranchCode(resultSet.getString("BRANCH"));
				listAcctDetails.add(details);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
				prepareStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return listAcctDetails;
	}

	@Override
	public void saveGoup(List<AccountDetails> accountDetails) {

		Connection con = null;
		PreparedStatement prepareStat = null;
		List<AccountDetails> listAcctDetails = new ArrayList<AccountDetails>();
		try {
			con = dataSource.getConnection();
			prepareStat = con.prepareStatement(
					"INSERT INTO GROUP_ID_CONFIG (GROUPID, GLECUSTOMERID, ACCOUNTNUMBER, BRANCH)values (?,?,?,?)");
			long groupId = System.currentTimeMillis();
			String groupIdString = String.valueOf(groupId);
			for (AccountDetails acctDet : accountDetails) {
				prepareStat.setString(1, groupIdString);
				prepareStat.setString(2, "1" + groupIdString + "1");
				prepareStat.setString(3, acctDet.getAccountNumber());
				prepareStat.setString(4, acctDet.getBranchCode());
				prepareStat.addBatch();
			}
			int[] resultSet = prepareStat.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
				prepareStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}


	/***
	 * 
	 * @param customerDetails
	 * @param details
	 */
	private void insertCodexKey(String[] customerDetails,String details) {
		PreparedStatement ps = null;
		Connection conn = null;
		String sql = "INSERT INTO CODEX_KEY (ID,CUSTOMERID,COMPANYID,KEY) VALUES(?,?,?,?)";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, customerDetails[0]);
			ps.setString(2, customerDetails[1]);
			ps.setString(3, customerDetails[3]);
			ps.setString(4, details);
			int count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/***
	 * 
	 * @param userDetails
	 * @param details
	 * @return
	 */
	private String  checkAccountExists(String[] userDetails,String details) {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		String hashValue = null;
		String actualDetails = details;
		String sql = "SELECT * FROM CODEX_KEY WHERE ID =? AND CUSTOMERID=? AND COMPANYID=?";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userDetails[0]);
			ps.setString(2, userDetails[1]);
			ps.setString(3, userDetails[3]);
			rs = ps.executeQuery();
			details = AES.encrypt(details, FOLICAL_KEY);
			while(rs.next()) {
				hashValue = rs.getString("KEY");
				details = AES.encrypt(actualDetails, hashValue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return details;
	}
	
	/***
	 * 
	 * @param userDetails
	 * @param details
	 */
	private void addOrUpdateAccount(String[] userDetails,String details) {
		PreparedStatement ps = null;
		Connection conn = null;
		String sql = "INSERT INTO CODEX_DATA (ACCOUNTNUMBER,CUSTOMERID,CUSTOMERNAME,COMPANYID,DETAILS,CREATEDTIME)values (?,?,?,?,?,?)";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userDetails[0]);
			ps.setString(2, userDetails[1]);
			ps.setString(3, userDetails[2]);
			ps.setString(4, userDetails[3]);
			ps.setString(5, details);
			ps.setTimestamp(6, calendarToSQLTimestamp(Calendar.getInstance()));
			int count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	/***
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	private void closeConnection(Connection conn) throws SQLException {
		conn.close();
	}
	
	/***
	 * 
	 * @return
	 */
	private Connection getConnection() {
		try {
			return jdbcTemplate.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/***
	 * 
	 * @param calendar
	 * @return
	 */
	public static Timestamp calendarToSQLTimestamp(Calendar calendar) {
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
		return timestamp;
	}
	
	/***
	 * 
	 */
	@Override
	public Double getBalance(String accountNumber, String companyId) {
		
		String sql = "SELECT * FROM CODEX_DATA WHERE ACCOUNTNUMBER='"+accountNumber+"' AND COMPANYID='"+companyId+"'";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = null;
		Double balance = null;
		String [] balanceSplit = null;
		String details = "";
		String decryptedDetails = "";
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs= ps.executeQuery();
			while(rs.next()) {
				details = rs.getString("DETAILS");
			}
			decryptedDetails = decryptDetails(details,accountNumber);
			balanceSplit = decryptedDetails.split(CommonConstants.AMPERSAND_SYMBOL);
			balance = Double.parseDouble(balanceSplit[2]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}

	/***
	 * 
	 * @param details
	 * @param accountNumber
	 * @return
	 */
	private String decryptDetails(String details,String accountNumber) {
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = null;
		String sql = "SELECT * FROM CODEX_KEY WHERE ID='"+accountNumber+"'";
		String key = "";
		String decryptedValue = ""; 
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			rs.last();
			int count = rs.getRow();
			if(count ==1) {
				decryptedValue = AES.decrypt(details, FOLICAL_KEY);
			} else {
				rs.beforeFirst();
				int findexactRowValue = 1;
				while(rs.next()) {
					if(findexactRowValue == count-1) {
						key = rs.getString("KEY");
						decryptedValue = AES.decrypt(details, key);
						break;
					}
					findexactRowValue++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return decryptedValue;
	}

}
