import domain.item.Album;
import domain.item.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaShopMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Item item = em.find(Item.class, 4L);
            item.setPrice(10000);

            tx.commit();
        } catch (Exception e) {
            System.out.println("e = " + e);
            tx.rollback();
        }
        em.close();
        emf.close();
    }
}
