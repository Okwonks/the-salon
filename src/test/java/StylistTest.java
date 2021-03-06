import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Stylist_instanciatesCorrectly_true() {
        Stylist testStylist = new Stylist("Sheila", "Braiding", "image-url");
        assertTrue(testStylist instanceof Stylist);
    }

    @Test
    public void Stylist_stylistsInstanciateWithAName_String() {
        Stylist testStylist = new Stylist("Sheila", "Braiding", "image-url");
        assertEquals("Sheila", testStylist.getStylistName());
    }
 
    //Tests before the save are not dependent on the save()
    @Test
    public void save_savesStylistsIntoDatabase() {
        Stylist testStylist = new Stylist("Sheila", "Braiding", "image-url");
        testStylist.save();
        assertTrue(Stylist.all().get(0).equals(testStylist));
    }

    @Test
    public void getId_stylistsInstanciateWithAnId() {
        Stylist testStylist = new Stylist("Sheila", "Braiding", "image-url");
        testStylist.save();
        assertTrue(testStylist.getId() > 0);
    }

    @Test
    public void all_returnsAllInstancesOfStylists_true() {
        Stylist firstStylist = new Stylist("Sheila", "Braiding", "image-url");
        firstStylist.save();
        Stylist secondStylist = new Stylist("Shaniqwa", "Braiding", "image-url");
        secondStylist.save();
        assertTrue(Stylist.all().get(0).equals(firstStylist));
        assertTrue(Stylist.all().get(1).equals(secondStylist));
    }

    @Test
    public void equals_returnsTrueIfNamesAreEqual() {
        Stylist firstStylist = new Stylist("Sheila", "Braiding", "image-url");
        Stylist secondStylist = new Stylist("Sheila", "Braiding", "image-url");
        assertTrue(firstStylist.equals(secondStylist));
    }

    @Test
    public void save_savesStylistWithOwnId() {
        Stylist testStylist = new Stylist("Sheila", "Braiding", "image-url");
        testStylist.save();
        Stylist savedStylist = Stylist.all().get(0);
        assertEquals(testStylist.getId(), savedStylist.getId());
    }

    @Test
    public void find_returnsStylistWithSameId() {
        Stylist firstStylist = new Stylist("Sheila", "Braiding", "image-url");
        firstStylist.save();
        Stylist secondStylist = new Stylist("Shaniqwa", "Braiding", "image-url");
        secondStylist.save();
        assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
    }

    @Test
    public void getClient_getsStylistsClientsFromDatabase() {
        Stylist testStylist = new Stylist("Sheila", "Braiding", "image-url");
        testStylist.save();
        Client firstClient = new Client("Jane", testStylist.getId());
        firstClient.save();
        Client secondClient = new Client("Stella", testStylist.getId());
        secondClient.save();
        Client[] clients = new Client[] { firstClient, secondClient };
        assertTrue(testStylist.getClients().containsAll(Arrays.asList(clients)));
    }

    @Test
    public void update_updatesStylistsDetail_true() {
        Stylist testStylist = new Stylist("Sheila", "Braiding", "image-url");
        testStylist.save();
        testStylist.update("Sheila Stark");
        assertEquals("Sheila Stark", Stylist.find(testStylist.getId()).getStylistName());
    }

    @Test
    public void delete_removesStylistsFromDB_true() {
        Stylist testStylist = new Stylist("Sheila", "Braiding", "image-url");
        testStylist.save();
        int testStylistId = testStylist.getId();
        testStylist.delete();
        assertNull(Stylist.find(testStylistId));
    }
}