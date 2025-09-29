import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApiClient apiClient = new ApiClient();
        String response = apiClient.fetchQuestions(5, "multiple", "easy");
        System.out.println(response);

        String playerName = "";
        int correctAnswers = 0;
        boolean used5050 = false;
        boolean usedAudience = false;

        PlayerStatistics newStats = new PlayerStatistics(
                playerName,
                correctAnswers,
                used5050,
                usedAudience
        );

        String fileName = "stats.json";
        Gson gson = new Gson();
        List<PlayerStatistics> allStats = new ArrayList<>();

        File file = new File(fileName);
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type listType = new TypeToken<List<PlayerStatistics>>() {}.getType();
                allStats = gson.fromJson(reader, listType);

                if (allStats == null) {
                    allStats = new ArrayList<>();
                }
            } catch (IOException e) {
                System.out.println("Errore durante la lettura del file JSON: " + e.getMessage());
            }
        }
        allStats.add(newStats);
        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(allStats, writer);
            System.out.println("Statistiche aggiornate correttamente in " + fileName);
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio delle statistiche: " + e.getMessage());
        }
    }
}
