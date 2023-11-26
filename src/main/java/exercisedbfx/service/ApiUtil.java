package exercisedbfx.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import exercisedbfx.model.FullExerciseDescription;
import exercisedbfx.model.ShortExerciseDescription;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiUtil {
    private static final String API_URI = "https://exercisedb.p.rapidapi.com/exercises/";
    private static final String API_BODY_PART = "bodyPart/";
    private static final String API_EXERCISE = "exercise/";
    private static final String API_ALL_BODY_PARTS = "bodyPartList";
    private static final String API_LIMIT = "?limit=500";
    private static final String API_KEY = "824ce5210fmsh35ec2c39dafe920p133e73jsneccad937d7dc";
    private static final String API_HOST = "exercisedb.p.rapidapi.com";

    // Returns a list of short descriptions of the exercise by specified body part
    public static List<ShortExerciseDescription> getExercisesByBodyPart(String bodyPart) {
        // Creates a URI
        String uri = API_URI + API_BODY_PART + bodyPart + API_LIMIT;
        // Gets an HTTP request
        HttpRequest request = getHttpRequest(uri);
        try {
            // Sends a request and retrieve a response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());
            // Creates a TypeToken to parse a list of ShortExerciseDescription objects
            TypeToken<List<ShortExerciseDescription>> token = new TypeToken<>(){};
            // Creates a new Gson object
            Gson gson = new Gson();
            // Returns a list of ShortExerciseDescription objects
            return gson.fromJson(response.body(), token);
        } catch (IOException | InterruptedException e) {
            // Throws an exception if an error occurs.
            throw new RuntimeException("An error occurs while connecting to the API."
                                       + e.getMessage());
        }
    }

    // Returns a full description of specified exercise by id
    public static FullExerciseDescription getFullDescriptionById(String id) {
        // Creates a URI
        String uri = API_URI + API_EXERCISE + id;
        // Gets an HTTP request
        HttpRequest request = getHttpRequest(uri);
        try {
            // Sends a request and retrieve a response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());
            // Creates a new Gson object
            Gson gson = new Gson();
            // Returns a FullExerciseDescription object
            return gson.fromJson(response.body(), FullExerciseDescription.class);
        } catch (IOException | InterruptedException e) {
            // Throws an exception if an error occurs.
            throw new RuntimeException("An error occurs while connecting to the API."
                                       + e.getMessage());
        }
    }

    // Returns a list of all body parts
    public static List<String> getBodyParts() {
        // Gets an HTTP request
        HttpRequest request = getHttpRequest(API_URI + API_ALL_BODY_PARTS);
        try {
            // Sends a request and retrieve a response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());
            // Creates a TypeToken to parse a list of ShortExerciseDescription objects
            TypeToken<List<String>> token = new TypeToken<>(){};
            // Creates a new Gson object
            Gson gson = new Gson();
            // Returns a list of ShortExerciseDescription objects
            return gson.fromJson(response.body(), token);
        } catch (IOException | InterruptedException e) {
            // Throws an exception if an error occurs.
            throw new RuntimeException("An error occurs while connecting to the API."
                                       + e.getMessage());
        }
    }

    // Returns an HTTP request based on the passed uri
    private static HttpRequest getHttpRequest(String uri) {
        // Builds an HTTP request
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", API_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }

    public static void main(String[] args) {
        // Test ApiUtil class
        List<ShortExerciseDescription> back = getExercisesByBodyPart("back");
        back.forEach(System.out::println);
        System.out.println("Exercise id 0001");
        System.out.println(getFullDescriptionById("0001"));
        System.out.println("List of the body parts:");
        getBodyParts().forEach(System.out::println);
    }
}
