package net.lezhang.spring.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("offerDao")
public class OfferDAO {
    
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
        System.out.println("Autowired dataSource: " + ((dataSource == null) ? "null" : "ok"));
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public List<Offer> getOffers() {
        return jdbcTemplate.query("select * from offers", new RowMapper<Offer>(){
            public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Offer offer = new Offer();
                offer.setId(rs.getInt("id"));
                offer.setName(rs.getString("name"));
                offer.setEmail(rs.getString("email"));
                offer.setOffer(rs.getString("text"));
                return offer;
            }
        });
    }
    
    public List<Offer> getOffers(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        
        return namedParameterJdbcTemplate.query("select * from offers where name = :name", params, new RowMapper<Offer>(){
            public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Offer offer = new Offer();
                offer.setId(rs.getInt("id"));
                offer.setName(rs.getString("name"));
                offer.setEmail(rs.getString("email"));
                offer.setOffer(rs.getString("text"));
                return offer;
            }
        });
    }

    /**
     * This returns a single object from DB.
     * @param id
     * @return
     */
    public Offer getOffer(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        
        Offer result = null;
        try {
        result = namedParameterJdbcTemplate.queryForObject("select * from offers where id = :id", params, new RowMapper<Offer>(){
            public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Offer offer = new Offer();
                offer.setId(rs.getInt("id"));
                offer.setName(rs.getString("name"));
                offer.setEmail(rs.getString("email"));
                offer.setOffer(rs.getString("text"));
                return offer;
            }
        });
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }        
        return result;
    }
    
    public int deleteOffer(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.update("delete from offers where id = :id", params);

    }

    public int insertOffer(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.update("insert into offers (id, name, email, text) "
                + "values (:id, 'Le Zhang', 'newemail@email.com', 'revived from deletion...')", params);
    }
    
    public int insertOfferUsingObject(Offer offer) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
        
        return namedParameterJdbcTemplate.update("insert into offers (name, email, text)"
                + "values (:name, :email, :offer)", params);
        // note the param name need to match the POJO property getter name
    }

}
