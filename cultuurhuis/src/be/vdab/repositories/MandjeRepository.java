package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import be.vdab.entities.Voorstellingen;

public class MandjeRepository extends AbstractRepository{
	private static final String BEGIN_SELECT = "select id, titel, uitvoerders, datum, genreid, prijs, vrijeplaatsen from voorstellingen ";
	private static final String FIND_IN_MANDJE = BEGIN_SELECT + "where id in (";
	private final static String DELETE = "delete from voorstellingen where id in (";	
	
	
	
	public List<Voorstellingen> readMandje(long id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_IN_MANDJE)) {
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
	
	public List<Voorstellingen> displayMandje(Set<Long> ids) {
	StringBuilder sql = new StringBuilder(FIND_IN_MANDJE);
	ids.forEach(id -> sql.append("?,"));
	sql.deleteCharAt(sql.length() - 1);
	sql.append(')');
	try (Connection connection = dataSource.getConnection();
	PreparedStatement statement =
	connection.prepareStatement(sql.toString())) {
		List<Voorstellingen> voorstellingen = new ArrayList<>();
	int index = 1;
	for (long id : ids) {
	statement.setLong(index++, id);
	}
	statement.executeUpdate();
	} catch (SQLException ex) {
	throw new RepositoryException(ex);
	}
	}	
	
	
//	public void delete(Set<Long> ids) {
//		StringBuilder sql = new StringBuilder(DELETE);
//		ids.forEach(id -> sql.append("?,"));
//		sql.deleteCharAt(sql.length() - 1);
//		sql.append(')');
//		try (Connection connection = dataSource.getConnection();
//		PreparedStatement statement =
//		connection.prepareStatement(sql.toString())) {
//		int index = 1;
//		for (long id : ids) {
//		statement.setLong(index++, id);
//		}
//		statement.executeUpdate();
//		} catch (SQLException ex) {
//		throw new RepositoryException(ex);
//		}
//		}
	
	
	
	
	private Voorstellingen resultSetRijNaarVoorstellingen(ResultSet resultSet) throws SQLException {
		return new Voorstellingen(resultSet.getLong("id"),resultSet.getString("titel"),resultSet.getString("uitvoerders"),resultSet.getTimestamp("datum"),resultSet.getLong("genreid"),
				resultSet.getBigDecimal("prijs"),resultSet.getLong("vrijeplaatsen"));
	}
	
}
