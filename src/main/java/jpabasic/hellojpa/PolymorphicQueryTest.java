package jpabasic.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class PolymorphicQueryTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Movie movie = new Movie();
            movie.setDirector("director");
            movie.setActor("actor");
            movie.setName("바람과함께사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            Book book = new Book();
            book.setAuthor("김영한");
            book.setName("JPA");
            book.setIsbn("0000");
            book.setPrice(30000);

            em.persist(book);

            Album album = new Album();
            album.setName("Music");
            album.setArtist("Unknown");

            em.persist(album);

            em.flush();
            em.clear();

            // TYPE
            // "select * from item i where i.dtype in ('B', 'M')
            List<Item> items = em.createQuery("select i from Item i where type(i) in (Book, Movie)", Item.class)
                    .getResultList();
            for (Item item : items) {
                System.out.println(item.getName());
            }


            // TREAT
            // "select * from item i where i.dtype = 'A' and i.artist = 'Unknown'
            items = em.createQuery("select i from Item i where treat(i as Album).artist = 'Unknown'", Item.class)
                    .getResultList();

            for (Item item : items) {
                System.out.println(item.getName());
            }

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
