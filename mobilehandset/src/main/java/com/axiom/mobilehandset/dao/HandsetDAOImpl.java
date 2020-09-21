package com.axiom.mobilehandset.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.axiom.mobilehandset.model.Handset;
import java.util.NoSuchElementException;



/**
 * Service interface
 *
 * @author Sivakumar Panchu
 * @version 1.0
 * @since 21/09/2020
 */
@Transactional
@Repository
public class HandsetDAOImpl implements HandsetDAO{

	public static final Logger logger = LoggerFactory.getLogger(HandsetDAOImpl.class);

	private static final String SQL = "select * from account where account_number=?";

	private final JdbcTemplate template;

	@Autowired
	public HandsetDAOImpl(JdbcTemplate template) {
		this.template = template;
	}
	
	/**
	 * this method returns account object
     * @param accountNumber
     * @return
     */
	@Override
	public Handset findByAccountNumber(String accountNumber) {
		try{
			RowMapper<Handset> rowMapper = new BeanPropertyRowMapper<>(Handset.class);
			return template.queryForObject(SQL, rowMapper, accountNumber);
		}catch(EmptyResultDataAccessException e){
			logger.info("Handset does not found: {}", e.getLocalizedMessage());
			throw new NoSuchElementException("Handset does not found");
		}catch(Exception e) {
			throw e;
		}
	}
}
