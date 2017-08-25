import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

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
    public void Stylist_instanciatesCorrectly_true() {
        Stylist testStylist = new Stylist("Sheila");
        assertTrue(testStylist instanceof Stylist);
    }

    @Test
    public void Stylist_stylistsInstanciateWithAName_String() {
        Stylist testStylist = new Stylist("Sheila");
        assertEquals("Sheila", testStylist.getName());
    }
 
    //Tests before the save are not dependent on the save()
    @Test
    public void save_savesStylistsIntoDatabase() {
        Stylist testStylist = new Stylist("Sheila");
        testStylist.save();
        assertTrue(Stylist.all().get(0).equals(testStylist));
    }

    @Test
    public void getId_stylistsInstanciateWithAnId() {
        Stylist testStylist = new Stylist("Sheila");
        testStylist.save();
        assertTrue(testStylist.getId() > 0);
    }

    @Test
    public void all_returnsAllInstancesOfStylists_true() {
        Stylist firstStylist = new Stylist("Sheila");
        firstStylist.save();
        Stylist secondStylist = new Stylist("Shaniqwa");
        secondStylist.save();
        assertTrue(Stylist.all().get(0).equals(firstStylist));
        assertTrue(Stylist.all().get(1).equals(secondStylist));
    }

    @Test
    public void equals_returnsTrueIfNamesAreEqual() {
        Stylist firstStylist = new Stylist("Sheila");
        Stylist secondStylist = new Stylist("Sheila");
        assertTrue(firstStylist.equals(secondStylist));
    }

    @Test
    public void save_savesStylistWithOwnId() {
        Stylist testStylist = new Stylist("Sheila");
        testStylist.save();
        Stylist savedStylist = Stylist.all().get(0);
        assertEquals(testStylist.getId(), savedStylist.getId());
    }

    @Test
    public void find_returnsStylistWithSameId() {
        Stylist firstStylist = new Stylist("Sheila");
        firstStylist.save();
        Stylist secondStylist = new Stylist("Shaniqwa");
        secondStylist.save();
        assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
    }
}