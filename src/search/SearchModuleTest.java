package search;

import basics.Room;
import basics.RoomAttribute;
import hotel.Hotel;
import hotel.InitializeHotel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SearchModuleTest {

    private InitializeHotel initializeHotel;
    private SearchModule searchModule;
    private List<RoomAttribute> roomAttributes;

    @Before
    public void initialize() {
        this.initializeHotel = new InitializeHotel();
        this.searchModule = new SearchModule();
        this.roomAttributes = new ArrayList<>();
        roomAttributes.add(new RoomAttribute("Jacuzzi", "This room has a jacuzzi"));

    }

    @Test
    public void searchFault() {
        Hotel molveno = initializeHotel.makeMolveno();
        String result = molveno.search(LocalDateTime.now(), LocalDateTime.now().plusDays(3), 80,
                3);
        Assert.assertEquals("Call the receptionist on " + molveno.getTelNo(), result);
    }

    @Test
    public void searchNormal() {
        Hotel molveno = initializeHotel.makeMolveno();
        List<Room> suggestion = searchModule.searchSuggestion(LocalDateTime.now(), LocalDateTime.now().plusDays(3),
                8, 3, molveno);

        Assert.assertEquals(3, suggestion.size());

    }

    @Test
    public void searchAll() {
        Hotel molveno = initializeHotel.makeMolveno();
        List<Room> suggestion = searchModule.searchAll(LocalDateTime.now(), LocalDateTime.now().plusDays(3), molveno);

        Assert.assertEquals(4, suggestion.size());
    }
    @Test
    public void searchAttributes() {
        Hotel molveno = initializeHotel.makeMolveno();
        List<Room> allRooms = molveno.getRooms();
        List<Room> suggestion = searchModule.searchAttributes(roomAttributes, allRooms);
//        for (Room room : suggestion) {
//            System.out.println(room.toString());
//        }
        Assert.assertEquals(4, suggestion.size());
    }
}
