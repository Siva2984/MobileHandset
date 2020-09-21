package com.axiom.mobilehandset.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;


/**
 * This class has method converts the row into an object via row mapper
 * @author Rajesh Majji
 * @version 1.0
 * @since 31/08/2020
 */
public class HandsetRowMapper implements RowMapper<Handset> {

    public static final Logger logger = LoggerFactory.getLogger(HandsetRowMapper.class);
    
    /**
   	 *Method to convert row into an Object
   	 *
   	 * @param ResultSet result from SPringJDBC query.
   	 * @param rownum.
   	 * @return returns account object.
   	*/
    @Override
    public Handset mapRow(ResultSet rs, int rowNum) {
        Handset handset = new Handset();

        try {
            handset.getRelease().setPriceEur(rs.getInt("priceEur"));
            handset.setSim(rs.getString("sim"));
            handset.getRelease().setAnnounceDate(rs.getString("announcedDate"));
        } catch (Exception e) {
            logger.error("Error while converting the row into an account object via row mapper", e);
        }

        return handset;
    }

}
