import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApp {

    private static final String API_KEY = "YOUR_API_KEY"; // Replace with your OpenWeatherMap API key
    private static final String API_URL = "https://api.meteomatics.com/2023-11-22T00:00:00Z--2023-11-25T00:00:00Z:PT1H/t_2m:C,t_20m:C,t_100m:C,t_200m:C,t_500m:C,t_850hPa:C,t_700hPa:C/48.8566,2.3522/html";

    public static void main(String[] args) {
        try {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            System.out.print("Enter city name: ");
            String cityName = scanner.nextLine();

            String apiUrl = String.format(API_URL, cityName, API_KEY);
            String jsonResponse = getWeatherData(apiUrl);

            if (jsonResponse != null) {
                System.out.println("Weather Data for " + cityName + ":\n" + jsonResponse);
            } else {
                System.out.println("Failed to fetch weather data. Please check your API key and city name.");
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getWeatherData(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            return response.toString();
        } else {
            System.out.println("Failed to fetch weather data. HTTP error code: " + responseCode);
            return null;
        }
    }
}
