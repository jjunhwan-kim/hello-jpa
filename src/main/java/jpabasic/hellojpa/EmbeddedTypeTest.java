package jpabasic.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmbeddedTypeTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Address homeAddress = new Address("city", "street", "10000");

            Member member1 = new Member();
            member1.setUsername("hello");
            member1.setHomeAddress(homeAddress);
            member1.setWorkPeriod(new Period());

            Member member2 = new Member();
            member2.setUsername("hello");
            member2.setHomeAddress(homeAddress);
            member2.setWorkPeriod(new Period());

            em.persist(member1);
            em.persist(member2);

            //member1.getHomeAddress().setCity("newCity");

            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }

        emf.close();
    }
}
