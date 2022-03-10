package repository;

import entity.Clerk;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import service.GenericServiceImpel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClerkRepositoryTest {

    private final GenericRepositoryImpel genericRepositoryImpel = new GenericRepositoryImpel();
    private final ClerkRepository clerkRepository = new ClerkRepository();
    private Clerk clerk = new Clerk(null,"farzad","123456789","1!Aa ",3000);
    static Session session;
    private GenericServiceImpel genericServiceImpel = new GenericServiceImpel();

    @BeforeAll
    public static void testConnection() {
        System.out.println("SessionFactory connected");
    }

    @AfterAll
    public static void downConnection() {
        if (session != null) session.close();
        System.out.println("SessionFactory destroyed");
    }

    @AfterEach
    public void cleanUp() {
        genericRepositoryImpel.delete(clerk);
    }


    @Test
    public void testConnection1(){
        assertNotNull(session);
    }

    @Test
    public void testFindById(){
        Clerk clerk1 = (Clerk) genericServiceImpel.add(clerk);

        Clerk clerk2 = clerkRepository.findById(clerk1.getId());

        assertNotNull(clerk2);
        assertEquals(clerk1.getId(),clerk2.getId());
    }

    @Test
    public void testFindAll(){
        Clerk clerk1 = new Clerk(null,"farzad","12345678","1!Aa ",3000);
        Clerk clerk2 = new Clerk(null,"farzad","1234567","1!Aa ",3000);
        genericRepositoryImpel.add(clerk1);
        genericRepositoryImpel.add(clerk2);

        List<Clerk> clerkList = clerkRepository.findAll();
        genericRepositoryImpel.delete(clerk1);
        genericRepositoryImpel.delete(clerk2);

        if(clerkList.size() < 2 )
            fail();
    }


    @BeforeEach
    public void openSession() {
        System.out.println("Session created");
    }

    @AfterEach
    public void closeSession() {
        System.out.println("Session closed\n");
    }

}