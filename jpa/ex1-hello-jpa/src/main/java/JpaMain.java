import domain.Member;
import domain.Team;
import domain.item.Item;
import domain.item.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.module.ModuleFinder;
import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setCreatedBy("kim");
            member.setCreatedDate(LocalDateTime.now());

            em.flush();
            em.clear();

            tx.commit();
        } catch (Exception e) {
            System.out.println("e = " + e);
            tx.rollback();
        }
        em.close();
        emf.close();
    }
}
