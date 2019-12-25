package concurs.repository.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import concurs.model.Participant;
import concurs.repository.ParticipantRepository;

public class ParticipantJdbcDAO extends JdbcDaoSupport implements ParticipantRepository {

	@Override
	public void save(Participant p) {
		System.out.println("save called "+p);
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("id", p.getID());
		params.addValue("nume", p.getNume());
		getJdbcTemplate().update("insert into participants(id, nume) values (:id, :nume)", params);

	}

	@Override
	public void update(String codePart, Participant p) {
		System.out.println("Update called "+codePart+' '+p);
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("code", p.getID());
		params.addValue("punctaj", p.getPunctaj());
		params.addValue("probe", p.getNrProbe());
		getJdbcTemplate().update("update participants set punctaj=:punctaj, probe=:probe  where id=:code", params);

	}

	@Override
	public List<Participant> getAll() {
		System.out.println("getall called");
		List<Participant> lp=getJdbcTemplate().query("select * from participants", new ParticipantMapper());
		return lp;
	}

	@Override
	public Participant findById(String code) {
		System.out.println("findById called");
		MapSqlParameterSource params=new MapSqlParameterSource("id", code);
		Participant p=getJdbcTemplate().queryForObject("select * from participants where id=:id", new ParticipantMapper(), params);
		return p;
	}

	@Override
	public List<Participant> getByPoints() {
		System.out.println("getByPoints called ");
		List<Participant> lp=getJdbcTemplate().query("select * from participants order by punctaj desc", new ParticipantMapper());
		return lp;
	}
	
	private class ParticipantMapper implements RowMapper<Participant>{

		@Override
		public Participant mapRow(ResultSet rs, int row) throws SQLException {
			String id=rs.getString("id");
			String nume=rs.getString("nume");
			Participant p=new Participant(id,nume);
		    p.setNrProbe(rs.getInt("probe"));
		    p.setPunctaj(rs.getInt("punctaj"));
			return p;
		}
		
	}

}
