package service;

import entity.Clerk;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import repository.SessionFactorySingleton;

import static org.junit.jupiter.api.Assertions.*;

class GenericServiceImpelTest<K> {
    private Clerk clerk = new Clerk(1,"farzad","123456789","1!Aa ",3000);
    private final GenericServiceImpel genericServiceImpel = new GenericServiceImpel();
    static Session session;
    private final ClerkService clerkService = new ClerkService();

    @BeforeAll
    public static void testConnection() {
        session = SessionFactorySingleton.getInstance().openSession();
        System.out.println("SessionFactory connected");
    }

    @AfterAll
    public static void downConnection() {
        if (session != null) session.close();
        System.out.println("SessionFactory destroyed");
    }

    @AfterEach
    public void cleanUp() {
        genericServiceImpel.delete(clerk);
    }


    @Test
    public void testConnection1(){
        assertNotNull(session);
    }

    @Test
    public void testadd(){
        genericServiceImpel.add(clerk);

        Clerk clerk1 = clerkService.findById(clerk.getId());
        assertAll(
                () -> assertNotEquals(0,clerk1.getId()),
                () -> assertNotNull(clerk1),
                () -> assertEquals("123456789",clerk1.getNationalId())
        );
    }

    @Test
    public void testUpdate(){
        genericServiceImpel.add(clerk);
        Clerk clerk1 = new Clerk(clerk.getId(),"farhad",clerk.getNationalId(),clerk.getPassword(),1000);

        genericServiceImpel.update(clerk1);
        Clerk clerk2 = clerkService.findById(clerk1.getId());

        assertEquals("farhad",clerk2.getFullName());
    }

    @Test
    public void testDelete(){
        genericServiceImpel.add(clerk);

        genericServiceImpel.delete(clerk);
        Clerk clerk1 = clerkService.findById(clerk.getId());

        assertNull(clerk1);
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