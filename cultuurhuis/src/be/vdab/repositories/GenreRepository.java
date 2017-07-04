package be.vdab.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.*;


public class GenreRepository extends AbstractRepository {
	private static final String BEGIN_SELECT = "select id, naam from genres ";
	private static final String FIND_ALL = BEGIN_SELECT + "order by naam";
	
	
	
	
	public List<Genres> findAll() {
		try (Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
			List<Genres> genres = new ArrayList<>();
			while (resultSet.next()) {
				genres.add(resultSetRijNaarGenre(resultSet));
			}
			return genres;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	
	private Genres resultSetRijNaarGenre(ResultSet resultSet) throws SQLException {
		return new Genres(resultSet.getLong("id"), resultSet.getString("naam"));
	}		
		

	

}
