package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import be.vdab.entities.Voorstellingen;

public class VoorstellingenRepository extends AbstractRepository {
	private static final String BEGIN_SELECT = "select id, titel, uitvoerders, datum, genreid, prijs, vrijeplaatsen from voorstellingen ";
	private static final String FIND_ALL = BEGIN_SELECT + "order by datum";
	private static final String FIND_BY_GENRE = BEGIN_SELECT + "where genreid = ?";
	private static final String FIND_ONE = BEGIN_SELECT + "where id=?";
	private static final String FIND_IN_MANDJE = BEGIN_SELECT + "where id in (";

	public List<Voorstellingen> findAll(){
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
			List<Voorstellingen> voorstellingen = new ArrayList<>();
			while (resultSet.next()) {
				voorstellingen.add(resultSetRijNaarVoorstellingen(resultSet));
			}
			return voorstellingen;
		
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
		}
		
	public List<Voorstellingen> findGenre(long id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_GENRE)) {
			List<Voorstellingen> voorstellingen = new ArrayList<>();
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					voorstellingen.add(resultSetRijNaarVoorstellingen(resultSet));
				}
				return voorstellingen;
			}
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	
	public List<Voorstellingen> readMandje(Set<Long> ids) { //requires set of ids contained in the session variable
		StringBuilder sql = new StringBuilder(FIND_IN_MANDJE);
		ids.forEach(id -> sql.append("?,")); //append ? for each id
		sql.deleteCharAt(sql.length() - 1); //remove final comma
		sql.append(')'); //close sql statement
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql.toString())) {
			int index = 1;
			for (long id : ids) {
			statement.setLong(index++, id);
			}
			try (ResultSet resultSet = statement.executeQuery()) {
				List<Voorstellingen> voorstellingen = new ArrayList<>();
				while (resultSet.next()) {
					voorstellingen.add(resultSetRijNaarVoorstellingen(resultSet));
				}
				return voorstellingen;
			}
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	
	public Optional<Voorstellingen> read(long id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ONE)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return Optional.of(resultSetRijNaarVoorstellingen(resultSet));
				}
				return Optional.empty();
			}
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	

	
	
	private Voorstellingen resultSetRijNaarVoorstellingen(ResultSet resultSet) throws SQLException {
		return new Voorstellingen(resultSet.getLong("id"),resultSet.getString("titel"),resultSet.getString("uitvoerders"),resultSet.getTimestamp("datum"),resultSet.getLong("genreid"),
				resultSet.getBigDecimal("prijs"),resultSet.getLong("vrijeplaatsen"));
	}
	
	
	}