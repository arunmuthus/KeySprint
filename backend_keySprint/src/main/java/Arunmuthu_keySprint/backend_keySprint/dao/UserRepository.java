package Arunmuthu_keySprint.backend_keySprint.dao;

import Arunmuthu_keySprint.backend_keySprint.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public User findByUsername(String username) {
        List<User> list = em.createQuery(
                        "FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    public void save(User user) {
        em.persist(user);
    }
}
