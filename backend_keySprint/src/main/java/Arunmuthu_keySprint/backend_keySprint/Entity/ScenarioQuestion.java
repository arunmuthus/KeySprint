package Arunmuthu_keySprint.backend_keySprint.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "scenario_questions")
public class ScenarioQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String scenario;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getScenario() {
        return scenario;
    }

    @Override
    public String toString() {
        return "ScenarioQuestion{" +
                "id=" + id +
                ", scenario='" + scenario + '\'' +
                ", question='" + question + '\'' +
                '}';
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public ScenarioQuestion() {
    }

    public ScenarioQuestion(Long id, String scenario , String question) {
        this.id = id;
        this.scenario = scenario;
        this.question = question;
    }
}

