package jpabasic.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class TypeTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");

            em.persist(book);

            List<Item> result = em.createQuery("select i from Item i where type(i) = Book", Item.class).getResultList();
            for (Item item : result) {
                System.out.println("item = " + item);
            }
            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }
        finally {
            em.close();
        }

        emf.close();
    }
}
