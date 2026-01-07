package Arunmuthu_keySprint.backend_keySprint.dao;

import Arunmuthu_keySprint.backend_keySprint.Entity.Score;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ScoreRepository {

    @PersistenceContext
    private EntityManager em;

    public Score findByUsername(String username) {

        List<Score> list = em.createQuery(
                        "SELECT s FROM Score s WHERE s.username = :u",
                        Score.class
                ).setParameter("u", username)
                .getResultList();

        return list.isEmpty() ? null : list.get(0);
    }

        public void save(Score score) {
        em.persist(score);
    }
}
