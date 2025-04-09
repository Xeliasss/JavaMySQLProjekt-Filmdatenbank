package AnimeSearch;

import DatabaseConnector.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimeSearch {

    public static void searchAnime(String titel, int jahr) {
        String query = "SELECT * FROM anime WHERE titel LIKE ? AND jahr = ?";

        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + titel + "%");
            preparedStatement.setInt(2, jahr);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean found = false; // Flag um zu überprüfen, ob Anime gefunden wurden
                while (resultSet.next()) {
                    found = true;
                    // Daten aus den Spalten abrufen
                    String retrievedTitel = resultSet.getString("titel");
                    int retrievedJahr = resultSet.getInt("jahr");
                    String retrievedStudio = resultSet.getString("studio");
                    String retrievedSynchronisation = resultSet.getString("synchronisation");
                    int retrievedBewertung = resultSet.getInt("bewertung");

                    System.out.println("Titel: " + retrievedTitel);
                    System.out.println("Jahr: " + retrievedJahr);
                    System.out.println("Studio: " + retrievedStudio);
                    System.out.println("Synchronisation: " + retrievedSynchronisation);
                    System.out.println("Bewertung: " + retrievedBewertung);
                }

                if (!found) {
                    System.out.println("Kein Anime mit dem Titel \"" + titel + "\" gefunden.");
                }

            }

        } catch (SQLException e) {
            System.out.println("Datenbankfehler: " + e.getMessage());
        }
    }
}

