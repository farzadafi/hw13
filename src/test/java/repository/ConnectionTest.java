package repository;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {
    Session session;
    Connection connection = new Connection();

    @Test()
    public void testConnection(){
        session = connection.openCurrentSession();

        assertNotNull(session);
    }

}