package src;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    // Transforma a lista de tarefas do Java em um array JSON (texto)
    public static String listToJson(List<Task> tasks) {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < tasks.size(); i++) {
            json.append(tasks.get(i).toJson());
            if (i < tasks.size() - 1) {
                json.append(",\n"); // Adiciona vírgula entre os objetos
            } else {
                json.append("\n");
            }
        }
        json.append("]");
        return json.toString();
    }

    // Lê o texto JSON e reconstrói as tarefas no Java
    public static List<Task> parseJsonToList(String jsonString) {
        List<Task> tasks = new ArrayList<>();
        jsonString = jsonString.trim();

        // Se o arquivo estiver vazio ou contiver apenas "[]", retorna a lista vazia
        if (jsonString.length() <= 2) {
            return tasks; 
        }

        // Remove os colchetes [ e ] do início e do fim
        jsonString = jsonString.substring(1, jsonString.length() - 1).trim();

        // Divide o texto onde uma tarefa termina e a outra começa
        String[] jsonObjects = jsonString.split("\\},\\s*\\{");

        for (String obj : jsonObjects) {
            int id = extractIntValue(obj, "\"id\":");
            String description = extractStringValue(obj, "\"description\":");
            String status = extractStringValue(obj, "\"status\":");
            String createdAt = extractStringValue(obj, "\"createdAt\":");
            String updatedAt = extractStringValue(obj, "\"updatedAt\":");

            // Usa aquele segundo construtor que criamos na Fase 2 para remontar a tarefa
            tasks.add(new Task(id, description, status, createdAt, updatedAt));
        }

        return tasks;
    }

    // Busca o valor de um texto dentro do JSON usando as aspas como referência
    private static String extractStringValue(String json, String key) {
        int keyIndex = json.indexOf(key);
        int startIndex = json.indexOf("\"", keyIndex + key.length()) + 1;
        int endIndex = json.indexOf("\"", startIndex);
        return json.substring(startIndex, endIndex);
    }

    // Busca um número inteiro dentro do JSON ignorando caracteres especiais
    private static int extractIntValue(String json, String key) {
        int keyIndex = json.indexOf(key);
        int startIndex = keyIndex + key.length();
        int endIndex = json.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = json.length(); // Se for o último item antes da chave
        }
        // Extrai o trecho e remove qualquer coisa que não seja número
        String numberStr = json.substring(startIndex, endIndex).replaceAll("[^0-9]", "");
        return Integer.parseInt(numberStr);
    }
}