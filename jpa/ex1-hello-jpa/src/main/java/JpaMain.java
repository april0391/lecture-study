import cascade.Child;
import cascade.Parent;
import domain.Address;
import domain.Member;
import domain.Team;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("hello");
            member.setHomeAddress(new Address("home", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("삼겹살");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("old city1", "old street", "100"));
            member.getAddressHistory().add(new Address("old city2", "old street", "100"));
            em.persist(member);
            em.flush();
            em.clear();

            System.out.println("============== START ==============");
            Member findMember = em.find(Member.class, member.getId());
            findMember.getAddressHistory().remove(new Address("old city1", "old street", "100"));
            findMember.getAddressHistory().add(new Address("new city1", "old street", "100"));

            tx.commit();
        } catch (Exception e) {
            System.out.println("e = " + e);
            tx.rollback();
        }

        em.close();
        emf.close();
    }

    private static void printMember(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

//        Team team = member.getTeam();
//        System.out.println("team = " + team);
    }
}
