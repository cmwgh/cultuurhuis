package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Klant;

public class KlantRepository extends AbstractRepository{
	private static final String BEGIN_SELECT = "select id, voornaam, familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam, paswoord from klanten ";
	private static final String FIND_KLANT = BEGIN_SELECT + "where gebruikersnaam = ?";
	private static final String FIND_PW = "select paswoord from klanten where gebruikersnaam = ?";
	private final static String NIEUWE_KLANT = "insert into klanten(voornaam,familienaam,straat,huisnr,postcode,gemeente,gebruikersnaam,paswoord) values (?,?,?,?,?,?,?,?)";


	public List<Klant> findKlant(String gebruikersnaam){
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_KLANT)) {
			List<Klant> klant = new ArrayList<>();
			statement.setString(1, gebruikersnaam);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
				klant.add(resultSetRijNaarKlant(resultSet));
				}
			return klant;
			}
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
		}
	
	public void create(Klant entry) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(NIEUWE_KLANT)) {
			statement.setString(1, entry.getVoornaam());
			statement.setString(2, entry.getFamilienaam());
			statement.setString(3, entry.getStraat());
			statement.setString(4, entry.getHuisnr());
			statement.setString(5, entry.getPostcode());
			statement.setString(6, entry.getGemeente());
			statement.setString(7, entry.getGebruikersnaam());
			statement.setString(8, entry.getPaswoord());
			statement.executeUpdate();
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	
	private Klant resultSetRijNaarKlant(ResultSet resultSet) throws SQLException {
		return new Klant(resultSet.getInt("id"),
				resultSet.getString("voornaam"),
				resultSet.getString("familienaam"),
				resultSet.getString("straat"),
				resultSet.getString("huisnr"),
				resultSet.getString("postcode"),
				resultSet.getString("gemeente"),
				resultSet.getString("gebruikersnaam"),
				resultSet.getString("paswoord"));
	}

	public String findPw(String gebruikersnaam){
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_PW)) {
			String pw = "";
			statement.setString(1, gebruikersnaam);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
				 pw = resultSet.getString("paswoord");
				}
			return pw;
			}
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
		}

	
	

}
