package DatabaseConnector;

import AnimeSearch.AnimeSearch;
import FilmSearch.FilmSearch;
import SeriesSearch.SeriesSearch;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            // Eingabe des Titels
            System.out.print("Gib den Titel des Films/Anime/Serie ein (oder drücke Enter, um keinen Titel zu suchen): ");
            String title = scanner.nextLine();

            // Eingabe des Jahres
            System.out.print("Gib das Jahr des Films/Anime/Serie ein (oder 0 für kein Jahr): ");
            int year = scanner.nextInt();
            scanner.nextLine(); // zum Leeren des Eingabepuffers

            // Eingabe für Serien-Filter
            int seasonCount = 0;
            int episodeCount = 0;
            if (!title.isEmpty()) {  // Wenn ein Titel eingegeben wurde
                System.out.print("Gib die Anzahl der Staffeln ein (oder 0 für keine Angabe): ");
                seasonCount = scanner.nextInt();

                System.out.print("Gib die Anzahl der Episoden ein (oder 0 für keine Angabe): ");
                episodeCount = scanner.nextInt();
                scanner.nextLine(); // zum Leeren des Eingabepuffers
            }

            // Wähle die Art der Suche aus: Film, Serie oder Anime
            System.out.print("Wähle die Art der Suche (1 für Film, 2 für Serie, 3 für Anime): ");
            int searchType = scanner.nextInt();

            // Verwende switch-Expression (regelbasierter Switch)
            var result = switch (searchType) {
                case 1 -> { // Fall für Filme
                    FilmSearch.searchFilm(title, year);  // Aufruf ohne Genre
                    yield "Film gefunden"; // Optional, falls eine Rückgabe gewünscht wird
                }
                case 2 -> { // Fall für Serien
                    SeriesSearch.searchSeries(title, seasonCount, episodeCount); // Keine Genre-Abfrage mehr für Serien
                    yield "Serie gefunden"; // Optional
                }
                case 3 -> { // Fall für Animes
                    AnimeSearch.searchAnime(title, year);  // Keine Genre-Abfrage mehr für Animes
                    yield "Anime gefunden"; // Optional
                }
                default -> "Ungültige Auswahl!"; // Falls keine gültige Auswahl getroffen wird
            };

            // Optional: Ausgabe des Ergebnisses, falls benötigt
            System.out.println(result);

        }
    }
}
