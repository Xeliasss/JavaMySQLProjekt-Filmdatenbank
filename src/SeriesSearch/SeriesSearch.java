package SeriesSearch;

import DatabaseConnector.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeriesSearch {

    public static void searchSeries(String titel, int staffelAnzahl, int episodenAnzahl) {
        String query = "SELECT * FROM serien WHERE titel LIKE ? AND staffel_anzahl = ? AND episoden_anzahl = ?";

        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + titel + "%");
            preparedStatement.setInt(2, staffelAnzahl);
            preparedStatement.setInt(3, episodenAnzahl);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean found = false; // Flag um zu überprüfen, ob Serien gefunden wurden
                while (resultSet.next()) {
                    found = true;
                    // Daten aus den Spalten abrufen
                    String retrievedTitel = resultSet.getString("titel");
                    int retrievedStaffelAnzahl = resultSet.getInt("staffel_anzahl");
                    int retrievedEpisodenAnzahl = resultSet.getInt("episoden_anzahl");
                    int retrievedBewertung = resultSet.getInt("bewertung");

                    System.out.println("Titel: " + retrievedTitel);
                    System.out.println("Staffeln: " + retrievedStaffelAnzahl + ", Episoden: " + retrievedEpisodenAnzahl);
                    System.out.println("Bewertung: " + retrievedBewertung);
                }

                if (!found) {
                    System.out.println("Keine Serie mit dem Titel \"" + titel + "\" gefunden.");
                }

            }

        } catch (SQLException e) {
            System.out.println("Datenbankfehler: " + e.getMessage());
        }
    }
}
