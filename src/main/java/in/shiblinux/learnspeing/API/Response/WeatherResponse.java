package in.shiblinux.learnspeing.API.Response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

    public Current current;

    @Getter
    @Setter
    public class Current{

        @JsonProperty("observation_time")
        public String observationTime; // don't use snake_case use camelCase use  @JsonProperty("observation_time")
        public int temperature;
        public int feelslike;
        public int uv_index;
    }

}
