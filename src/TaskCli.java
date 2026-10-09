package src;
import java.util.List;

public class TaskCli {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, forneça um comando. Exemplo: java TaskCli list");
            return;
        }

        String command = args[0];
        
        List<Task> tasks = FileManager.readTasks(); 

        switch (command) {
            case "add":
                if (args.length < 2) {
                    System.out.println("Erro: Forneça a descrição da tarefa. Exemplo: java TaskCli add \"Nova tarefa\"");
                } else {
                    int newId = generateNextId(tasks);
                    Task newTask = new Task(newId, args[1]);
                    tasks.add(newTask);
                    FileManager.writeTasks(tasks); 
                    System.out.println("Tarefa adicionada com sucesso (ID: " + newId + ")");
                }
                break;

            case "update":
                if (args.length < 3) {
                    System.out.println("Erro: Forneça o ID e a nova descrição.");
                } else {
                    int idToUpdate = Integer.parseInt(args[1]);
                    Task taskToUpdate = findTaskById(tasks, idToUpdate);
                    if (taskToUpdate != null) {
                        taskToUpdate.setDescription(args[2]);
                        FileManager.writeTasks(tasks);
                        System.out.println("Tarefa " + idToUpdate + " atualizada com sucesso.");
                    } else {
                        System.out.println("Tarefa não encontrada (ID: " + idToUpdate + ")");
                    }
                }
                break;

            case "delete":
                if (args.length < 2) {
                    System.out.println("Erro: Forneça o ID da tarefa.");
                } else {
                    int idToDelete = Integer.parseInt(args[1]);
                    // Tenta remover a tarefa. Se o ID existir, removeIf retorna true.
                    boolean removed = tasks.removeIf(t -> t.getId() == idToDelete);
                    if (removed) {
                        FileManager.writeTasks(tasks);
                        System.out.println("Tarefa " + idToDelete + " deletada com sucesso.");
                    } else {
                        System.out.println("Tarefa não encontrada (ID: " + idToDelete + ")");
                    }
                }
                break;

            case "mark-in-progress":
                updateTaskStatus(tasks, args, "in-progress");
                break;

            case "mark-done":
                updateTaskStatus(tasks, args, "done");
                break;

            case "list":
                String filterStatus = args.length == 2 ? args[1] : null;
                boolean found = false;
                
                for (Task t : tasks) {
                    if (filterStatus == null || t.getStatus().equals(filterStatus)) {
                        System.out.println("[ID: " + t.getId() + "] [" + t.getStatus() + "] " + t.getDescription());
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("Nenhuma tarefa encontrada.");
                }
                break;

            default:
                System.out.println("Comando desconhecido: " + command);
        }
    }


    private static int generateNextId(List<Task> tasks) {
        int maxId = 0;
        for (Task t : tasks) {
            if (t.getId() > maxId) {
                maxId = t.getId();
            }
        }
        return maxId + 1;
    }

    // Busca uma tarefa pelo ID
    private static Task findTaskById(List<Task> tasks, int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                return t; 
            }
        }
        return null;
    }

    private static void updateTaskStatus(List<Task> tasks, String[] args, String status) {
        if (args.length < 2) {
            System.out.println("Erro: Forneça o ID da tarefa.");
            return;
        }
        int id = Integer.parseInt(args[1]);
        Task task = findTaskById(tasks, id);
        if (task != null) {
            task.setStatus(status); 
            FileManager.writeTasks(tasks);
            System.out.println("Status da tarefa " + id + " alterado para '" + status + "'.");
        } else {
            System.out.println("Tarefa não encontrada (ID: " + id + ")");
        }
    }
}