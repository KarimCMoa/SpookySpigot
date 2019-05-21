package eu.poliks.pspigot.api;

import org.bukkit.entity.*;
import org.json.simple.parser.*;
import org.json.simple.*;
import java.net.*;
import java.io.*;

public class SkIP
{
    @SuppressWarnings("unused")
    private static final String OPEN_WEATHER_MAP_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s,%s";
    @SuppressWarnings("unused")
    private static final String IP_INFO_DB_API_KEY = "NONE";
    public static final String SKIP_VERSION = "0.1";
    private static IPMode mode;

    static {
        SkIP.mode = IPMode.IP_API;
    }

    public static final IPMode getIPMode() {
        return SkIP.mode;
    }

    public static final void setIPMode(final IPMode mode) {
        SkIP.mode = mode;
    }

    public static final String getPlayerIP(final Player player) {
        return player.getAddress().getAddress().getHostAddress();
    }

    public static final SkIPData getIPData(final String ip) {
        try {
            final JSONObject json = (JSONObject)new JSONParser().parse(httpGet(SkIP.mode.getQueryURL(ip)));
            final String[] args = SkIP.mode.getArguments();
            return new SkIPData(json.get((Object)args[0]).toString(), json.get((Object)args[1]).toString(), json.get((Object)args[2]).toString(), json.get((Object)args[3]).toString(), json.get((Object)args[4]).toString(), Double.valueOf(json.get((Object)args[5]).toString()), Double.valueOf(json.get((Object)args[6]).toString()), json.get((Object)args[7]).toString());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static final SkIPDataWeather getWeatherData(final String city, final String countryCode) {
        try {
            final JSONObject weather = (JSONObject)((JSONArray)((JSONObject)new JSONParser().parse(httpGet(String.format("http://api.openweathermap.org/data/2.5/weather?q=%s,%s", city, countryCode)))).get((Object)"weather")).get(0);
            return new SkIPDataWeather(weather.get((Object)"main").toString(), weather.get((Object)"description").toString());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static final String httpGet(final String url) throws IOException {
        final HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "SkIP");
        final String response = String.valueOf(connection.getResponseCode()) + " " + connection.getResponseMessage();
        if (!response.startsWith("200")) {
            return null;
        }
        final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        final StringBuilder builder = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            builder.append(line);
        }
        in.close();
        return builder.toString();
    }

    public enum IPMode
    {
        IP_API("IP_API", 0, "http://ip-api.com/json/%s", new String[] { "country", "countryCode", "regionName", "city", "zip", "lat", "lon", "timezone" }),
        IP_INFO_DB("IP_INFO_DB", 1, "http://api.ipinfodb.com/v3/ip-city/?key=NONE&ip=%s", new String[] { "countryName", "countryCode", "regionName", "cityName", "zipCode", "latitude", "longitude", "timeZone" });

        private final String queryURL;
        private final String[] args;

        private IPMode(final String s, final int n, final String queryURL, final String[] args) {
            this.queryURL = queryURL;
            this.args = args;
        }

        public final String getQueryURL(final String ip) {
            return String.format(this.queryURL, ip);
        }

        public final String[] getArguments() {
            return this.args;
        }
    }

    public static class SkIPData
    {
        private final String countryName;
        private final String countryCode;
        private final String region;
        private final String city;
        private final String zip;
        private final double latitude;
        private final double longitude;
        private final String timezone;

        public SkIPData(final String countryName, final String countryCode, final String region, final String zip, final String city, final double latitude, final double longitude, final String timezone) {
            this.countryName = countryName;
            this.countryCode = countryCode;
            this.region = region;
            this.zip = zip;
            this.city = city;
            this.latitude = latitude;
            this.longitude = longitude;
            this.timezone = timezone;
        }

        public final String getCountryName() {
            return this.countryName;
        }

        public final String getCountryCode() {
            return this.countryCode;
        }

        public final String getRegion() {
            return this.region;
        }

        public final String getZip() {
            return this.zip;
        }

        public final String getCity() {
            return this.city;
        }

        public final double getLatitude() {
            return this.latitude;
        }

        public final double getLongitude() {
            return this.longitude;
        }

        public final String getTimezone() {
            return this.timezone;
        }
    }

    public static class SkIPDataWeather
    {
        private final String weatherName;
        private final String weatherDesc;

        public SkIPDataWeather(final String weatherName, final String weatherDesc) {
            this.weatherName = weatherName;
            this.weatherDesc = weatherDesc;
        }

        public final String getWeatherName() {
            return this.weatherName;
        }

        public final String getWeatherDescription() {
            return this.weatherDesc;
        }
    }
}
