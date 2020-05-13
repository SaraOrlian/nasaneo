package orlian.nasa.neo;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class NeoFeed {

    @SerializedName("near_earth_objects")
    HashMap<String, List<NearEarthObject>> nearEarthObjects;

    public NearEarthObject getFirstObject(String date) {
        return nearEarthObjects.get(date).get(0);
    }

    static class NearEarthObject {
        String id;
        String name;
        @SerializedName("nasa_jpl_url")
        String nasaJplUrl;
        @SerializedName("is_potentially_hazardous_asteroid")
        boolean hazardous;
        @SerializedName("close_approach_data")
        List<CloseApproachData> closeApproachData;

        public double closestLunarDistance() {
            return closeApproachData.get(0).missDistance.lunar;
        }
    }

    class CloseApproachData {
        @SerializedName("close_approach_date")
        String closeApproachDate;
        @SerializedName("miss_distance")
        MissDistance missDistance;
    }

    class MissDistance {
        double lunar;
    }



}
//https://api.nasa.gov/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY