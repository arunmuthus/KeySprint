package Arunmuthu_keySprint.backend_keySprint.dao;

import Arunmuthu_keySprint.backend_keySprint.Entity.ScenarioQuestion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ScenarioQuestionDao {

    @PersistenceContext
    private EntityManager entityManager;

    public ScenarioQuestion getRandomQuestion() {
        return entityManager
                .createQuery(
                        "SELECT s FROM ScenarioQuestion s ORDER BY function('RAND')",
                        ScenarioQuestion.class
                )
                .setMaxResults(1)
                .getSingleResult();
    }
}
