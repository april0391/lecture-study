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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team1 = new Team();
            team1.setName("맨유");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("맨시티");
            em.persist(team2);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(team2);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(team2);
            em.persist(member3);

            String jpql = "SELECT m FROM Member m WHERE m.team = :team";
            int i = em.createQuery("UPDATE Member m SET m.age = 20")
                    .executeUpdate();

//            em.clear();

            Member findMember = em.find(Member.class, member1.getId());

            System.out.println("findMember = " + findMember.getAge());


//            for (Member r : resultList) {
//                System.out.println("r = " + r);
//            }

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
