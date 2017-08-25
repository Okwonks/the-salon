import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {
    
    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "albert", "2525");
    }

    @After
    public void tearDown() {
        try (Connection con = DB.sql2o.open()) {
            String deleteClientQuery = "DELETE FROM clients *;";
            String deleteStylistQuery = "DELETE FROM stylists *;";
            con.createQuery(deleteClientQuery).executeUpdate();
            con.createQuery(deleteStylistQuery).executeUpdate();
        }
    }

    @Test
    public void Client_instanciatesCorrectly_true() {
        Client testClient = new Client("Jane");
        assertTrue(testClient instanceof Client);
    }

    @Test
    public void all_returnsAllInstancesOfClient_true() {
        Client firstClient = new Client("Jane");
        firstClient.save();
        Client secondClient = new Client("Stella");
        secondClient.save();
        assertEquals(true, Client.all().get(0).equals(firstClient));
        assertEquals(true, Client.all().get(1).equals(secondClient));
    }

    @Test
    public void equals_returnsTrueIfNamesAreTheSame() {
        Client firstClient = new Client("Jane");
        Client secondClient = new Client("Jane");
        assertTrue(firstClient.equals(secondClient));
    }

    @Test
    public void Client_instanciatesWithName_String() {
        Client testClient = new Client("Jane");
        assertEquals("Jane", testClient.getName());
    }

    @Test
    public void getId_clientInstanciatesWithId_int() {
        Client testClient = new Client("Jane");
        testClient.save();
        assertTrue(testClient.getId() > 0);
    }

    @Test
    public void save_returnsTrueIfNamesAreSaved() {
        Client testClient = new Client("Jane");
        testClient.save();
        assertTrue(Client.all().get(0).equals(testClient));
    }

    @Test
    public void save_assignIdToClient() {
        Client testClient = new Client("Jane");
        testClient.save();
        Client savedClient = Client.all().get(0);
        assertEquals(testClient.getId(), savedClient.getId());
    }

    @Test
    public void find_returnsClientWithSameId() {
        Client firstClient = new Client("Jane");
        firstClient.save();
        Client secondClient = new Client("Stella");
        secondClient.save();
        assertEquals(Client.find(secondClient.getId()), secondClient);
    }
}