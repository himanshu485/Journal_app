package in.shiblinux.learnspeing.services;

import in.shiblinux.learnspeing.API.Response.WeatherResponse;
import in.shiblinux.learnspeing.Cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {

    //    private static final String apiKey = "bc0f5d800959a210ea236834d989c9eb";
//remove static and final spring does not change static bean so remove static.

    @Value("${weather.api.key}")  // from application.yml
    private  String apiKey ;

//    public static final String URI = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {

        WeatherResponse weatherResponse= redisService.get("T"+city, WeatherResponse.class);


        if(weatherResponse!=null) {
            return  weatherResponse; //if data exist so return cached data or eles
        }
        else{
            String finalUrl = appCache.appChache.get(AppCache.keys.WEATHER_API.toString()).replace("<city>", city).replace("<apiKey>", apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body!=null){
                redisService.set("T"+city,body,1000);
            }
            return body;
        }
    }

}