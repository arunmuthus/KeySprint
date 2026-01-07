package Arunmuthu_keySprint.backend_keySprint.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private int score;
    // 🔹 REQUIRED by JPA
    public Score() {
    }

    // 🔹 Optional – for manual creation
    public Score(String username, int score, User user) {
        this.username = username;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
// getters & setters
}
