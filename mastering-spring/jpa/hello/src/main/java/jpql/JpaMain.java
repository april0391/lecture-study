package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            member.setTeam(team);
//            member.setType(MemberType.ADMIN);
//            em.persist(member);
//
//            em.flush();
//            em.clear();

            /*String jpql =
                    "SELECT " +
                    "   CASE WHEN m.age <= 10 THEN '학생요금' " +
                    "        WHEN m.age <= 60 THEN '경로요금' " +
                    "        ELSE '일반요금' " +
                    "   END " +
                    " FROM Member m";*/
            String jpql = "SELECT SIZE(t.members) FROM Team t";
            List<Integer> resultList = em.createQuery(jpql, Integer.class)
                    .getResultList();

            for (Integer s : resultList) {
                System.out.println("s = " + s);
            }

            tx.commit();
        } catch (Exception e) {
            System.out.println("e = " + e);
            tx.rollback();
        }

        em.close();
        emf.close();
    }
}
