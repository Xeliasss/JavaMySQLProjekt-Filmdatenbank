package DatabaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    // Verbindung zur Datenbank aufbauen
    public static Connection connect() {
        Connection connection = null;
        try {
            // Versuche, eine Verbindung mit der Datenbank herzustellen
            String url = ;  // Passe die URL an
            String username = ; // Dein MySQL-Benutzername
            String password = ; // Dein MySQL-Passwort
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Verbindung erfolgreich!");
        } catch (SQLException e) {
            // Fehlerbehandlung: Drucke den Stack Trace
            System.out.println("Fehler bei der Verbindung zur Datenbank:");
            e.printStackTrace(); // Gibt den Stack Trace aus
        }
        return connection;
    }
}
