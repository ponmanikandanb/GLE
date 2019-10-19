package com.temenos.gle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.temenos.gle.model.AccountDetails;

@Component
public class GlobalLiquidEngineDAOImpl implements GlobalLiquidEngineDAO {

	JdbcTemplate jdbcTemplate;
	DataSource dataSource;

	/*
	 * private final String SQL_FIND_PERSON = "select * from people where id = ?";
	 * private final String SQL_DELETE_PERSON = "delete from people where id = ?";
	 * private final String SQL_UPDATE_PERSON =
	 * "update people set first_name = ?, last_name = ?, age  = ? where id = ?";
	 * private final String SQL_GET_ALL = "select * from people"; private final
	 * String SQL_INSERT_PERSON =
	 * "insert into people(id, first_name, last_name, age) values(?,?,?,?)";
	 */
	@Autowired
	public GlobalLiquidEngineDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String getAll() {
		return "Test JPA";
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
				details.setAccoutNumber(resultSet.getString("ACCOUNTNUMBER"));
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
			prepareStat = con.prepareStatement(
					"SELECT * FROM GROUP_ID_CONFIG where GROUPID = ? ");
			prepareStat.setString(1, groupId);
			ResultSet resultSet = prepareStat.executeQuery();
			while (resultSet != null && resultSet.next()) {
				AccountDetails details = new AccountDetails();
				details.setAccoutNumber(resultSet.getString("ACCOUNTNUMBER"));
				details.setBranchCode(resultSet.getString("BRANCH"));
				listAcctDetails.add(details);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listAcctDetails;
	}

}
