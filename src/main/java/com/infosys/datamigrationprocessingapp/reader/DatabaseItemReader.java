package com.infosys.datamigrationprocessingapp.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.infosys.datamigrationprocessingapp.modal.Employee;

@Component
public class DatabaseItemReader extends JdbcCursorItemReader<Employee> implements ItemReader<Employee>{
	
	public DatabaseItemReader(@Autowired DataSource dataSource) {
		setDataSource(dataSource);
		setSql("SELECT * FROM EMPLOYEE");
		setFetchSize(10);
		setRowMapper(new SaleRowMapper());
	}
	
	public class SaleRowMapper implements RowMapper<Employee> {
		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			System.out.println("==========RESULT SET======="+rs.getString("EMP_NO")+"=======rs"+rs.getString("NAME"));
			Employee emp  = new Employee();
			emp.setEmployeeNumber(rs.getString("EMP_NO"));
			emp.setName(rs.getString("NAME"));
			emp.setEmail(rs.getString("EMAIL"));
			emp.setPhoneNumber(rs.getString("CONTACT_NUMBER"));
			
			return emp;
		}
	}
}
	
