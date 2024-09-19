package hellojpa;

import jakarta.persistence.*;
import org.hibernate.jpa.internal.PersistenceUnitUtilImpl;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void extracted(EntityManager em) {
        Team team = new Team();
        team.setName("teamA");
        em.persist(team);

        Member member = new Member();
        member.setUsername("member1");
        member.setTeam(team);
        em.persist(member);

        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
                .getResultList();

//        Member findMember = em.find(Member.class, member.getId());
//        Member find = em.find(Member.class, member.getId());
//        Member reference = em.getReference(Member.class, member.getId());

//        System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(reference));

//        System.out.println("find == reference: " + (find == reference));
    }

    private static void basic(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member findMember = em.find(Member.class, 1L);
            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void persistenceContext(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 영속
//            Member member1 = new Member(151L, "A");
//            Member member2 = new Member(161L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//
//            System.out.println("===============");
//            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void detached(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.username = " + findMember.getUsername());

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void relationship(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            team.addMember(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

}
