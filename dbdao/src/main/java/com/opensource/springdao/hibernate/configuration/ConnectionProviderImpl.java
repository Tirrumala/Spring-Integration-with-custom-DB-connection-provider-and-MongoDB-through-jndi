package com.opensource.springdao.hibernate.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.springframework.stereotype.Repository;

import com.metricstream.systemi.server.common.Controller;

@Repository(value = "springDaoConnectionProvider")
public class ConnectionProviderImpl implements ConnectionProvider {
	private static final long serialVersionUID = 1L;

	@Override
	public <T> T unwrap(Class<T> arg0) {
		return null;
	}

	@Override
	public synchronized void closeConnection(Connection con) throws SQLException {
		if(con != null)
		{
			con.close();
		}
	}

	@Override
	public synchronized Connection getConnection() throws SQLException {

			return DriverManager.getConnection("url", "user", "password");
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

	@Override
	public boolean isUnwrappableAs(@SuppressWarnings("rawtypes") Class arg0) {
		return false;
	}
}
