package src;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME = "tasks.json";
    private static final Path FILE_PATH = Paths.get(FILE_NAME);

    public static void ensureFileExists() {
        try {
            if (!Files.exists(FILE_PATH)) {
                Files.writeString(FILE_PATH, "[]");
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo JSON: " + e.getMessage());
        }
    }

    public static List<Task> readTasks() {
        ensureFileExists();
        try {
            String jsonContent = Files.readString(FILE_PATH);
            return JsonUtils.parseJsonToList(jsonContent);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo JSON: " + e.getMessage());
            return new ArrayList<>(); // Retorna uma lista vazia caso dê erro
        }
    }

    public static void writeTasks(List<Task> tasks) {
        try {
            String jsonContent = JsonUtils.listToJson(tasks);
            Files.writeString(FILE_PATH, jsonContent);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo JSON: " + e.getMessage());
        }
    }
}