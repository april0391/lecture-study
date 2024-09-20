package hellojpa;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            /*List<Member> members = em.createQuery("select m from Member m where m.username like '%kim%'", Member.class)
                    .getResultList();*/


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void criteria(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);
        Root<Member> m = query.from(Member.class);
        CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
        List<Member> members = em.createQuery(cq).getResultList();
    }

    private static void extracted3(EntityManager em) {
        Member member = new Member();
        member.setUsername("member1");
        member.setHomeAddress(new Address("homeCity", "street", "10000"));

        member.getFavoriteFoods().add("치킨");
        member.getFavoriteFoods().add("족발");
        member.getFavoriteFoods().add("피자");

        member.getAddressHistory().add(new Address("old1", "street", "10000"));
        member.getAddressHistory().add(new Address("old2", "street", "10000"));

        em.persist(member);
    }

    private static void extracted2(EntityManager em) {
        Address address = new Address("city", "street", "123");

        Member member = new Member();
        member.setUsername("member1");
        member.setHomeAddress(address);
        em.persist(member);

        Member member2 = new Member();
        member2.setUsername("member2");
        member2.setHomeAddress(address);
        em.persist(member2);

//            member.getHomeAddress().setCity("newCity");
    }

    private static void extracted1(EntityManager em) {
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
    }

    private static void extracted(EntityManager em) {
        Team team = new Team();
//        team.setName("teamA");
        em.persist(team);

        Member member = new Member();
        member.setUsername("member1");
//        member.setTeam(team);
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

}
