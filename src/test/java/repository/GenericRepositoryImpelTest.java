package repository;

import entity.Clerk;
import org.hibernate.Session;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

class GenericRepositoryImpelTest {
    private Clerk clerk = new Clerk(1,"farzad","123456789","1!Aa ",3000);
    private final GenericRepositoryImpel genericRepositoryImpel = new GenericRepositoryImpel();
    private final Connection connection = new Connection();
    static Session session;
    private final ClerkRepository clerkRepository = new ClerkRepository();

    @BeforeAll
    public static void testConnection() {
        Connection connection = new Connection();
        session = connection.openCurrentSessionWithTransaction();
        System.out.println("SessionFactory connected");
    }

    @AfterAll
    public static void downConnection() {
        Connection connection = new Connection();
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
    public void testadd(){
        genericRepositoryImpel.add(clerk);

        Clerk clerk1 = clerkRepository.findById(clerk.getId());
        assertAll(
                () -> assertNotEquals(0,clerk1.getId()),
                () -> assertNotNull(clerk1),
                () -> assertEquals("123456789",clerk1.getNationalId())
        );
    }

    @Test
    public void testUpdate(){
        genericRepositoryImpel.add(clerk);
        Clerk clerk1 = new Clerk(clerk.getId(),"farhad",clerk.getNationalId(),clerk.getPassword(),1000);

        genericRepositoryImpel.update(clerk1);
        Clerk clerk2 = clerkRepository.findById(clerk1.getId());

        assertEquals("farhad",clerk2.getFullName());
    }

    @Test
    public void testDelete(){
        genericRepositoryImpel.add(clerk);

        genericRepositoryImpel.delete(clerk);
        Clerk clerk1 = clerkRepository.findById(clerk.getId());

        assertNull(clerk1);
    }

    @BeforeEach
    public void openSession() {
        session = connection.openCurrentSession();
        System.out.println("Session created");
    }

    @AfterEach
    public void closeSession() {
        if (session != null) connection.closeCurrentSession();
        System.out.println("Session closed\n");
    }

}