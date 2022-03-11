package service;

import entity.Clerk;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ClerkServiceTest {
    private final GenericServiceImpel<Clerk> genericServiceImpel = new GenericServiceImpel<>();
    ClerkService clerkService = new ClerkService();

    @Test
    public void testFindById(){
        Clerk clerk = new Clerk(null,"farzad","111122233","aA 1!",1000);
        Clerk clerk1 = genericServiceImpel.add(clerk);

        Clerk clerk2 = clerkService.findById(clerk1.getId());
        genericServiceImpel.delete(clerk1);

        assertNotNull(clerk2);
        assertNotEquals(0,clerk2.getId());
    }

}