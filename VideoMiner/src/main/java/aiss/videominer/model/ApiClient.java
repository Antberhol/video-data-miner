package aiss.videominer.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "api_clients")
public class ApiClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String token;

    public ApiClient() {}

    public ApiClient(String name) {
        this.name = name;
        this.token = UUID.randomUUID().toString();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}