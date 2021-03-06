package orlian.nasa.neo;


import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class NeoServiceTest {

    @Test
    public void getAsteroids() throws IOException {
        //given
        NeoService service = new NeoServiceFactory().getInstance();

        //when
        NeoFeed feed = service.getAsteroids("2020-04-28", "2020-04-29").execute().body();  //Actually getting the info
        Response<NeoFeed> response = service.getAsteroids("2020-04-28", "2020-04-29").execute();  //Just checking for response -usually the first step

        //then
        assertTrue(response.toString(), response.isSuccessful());  //to find errors in response
        //NeoFeed feed = response.body();
        //assertNotNull(feed);
        HashMap<String, List<NeoFeed.NearEarthObject>> nearEarthObjects = feed.nearEarthObjects;
        assertFalse(nearEarthObjects.isEmpty());

        NeoFeed.NearEarthObject nearEarthObject = nearEarthObjects.get("2020-04-28").get(0);
        assertNotNull(nearEarthObject.id);
        assertNotNull(nearEarthObject.name);
        assertNotNull(nearEarthObject.nasaJplUrl);
        assertNotNull(nearEarthObject.hazardous);
        List<NeoFeed.CloseApproachData> closeApproachData = nearEarthObject.closeApproachData;
        assertNotNull(closeApproachData);
        assertFalse(closeApproachData.isEmpty());
        NeoFeed.CloseApproachData closeApproachData1 = closeApproachData.get(0);
        assertNotNull(closeApproachData1.closeApproachDate);
        assertNotNull(closeApproachData1.missDistance);
        assertTrue(closeApproachData1.missDistance.lunar > 0);

    }
}