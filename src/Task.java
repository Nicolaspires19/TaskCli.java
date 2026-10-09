package src;
import java.time.LocalDateTime;

public class Task {
    private int id;
    private String description;
    private String status; // "pending", "in-progress", "done"
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = "pending";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public Task(int id, String description, String status, String createdAt, String updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = LocalDateTime.parse(createdAt);
        this.updatedAt = LocalDateTime.parse(updatedAt);
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public int setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Método para converter o objeto Java em texto JSON
    public String toJson() {
        return "{\n" +
               "  \"id\": " + id + ",\n" +
               "  \"description\": \"" + description + "\",\n" +
               "  \"status\": \"" + status + "\",\n" +
               "  \"createdAt\": \"" + createdAt.toString() + "\",\n" +
               "  \"updatedAt\": \"" + updatedAt.toString() + "\"\n" +
               "}";
    }
}


