package exercisedbfx.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import exercisedbfx.model.Exercise;
import exercisedbfx.model.ExerciseDetails;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class APIUtility {
    private static final String API_URI = "https://exercisedb.p.rapidapi.com/exercises/";
    private static final String API_BODY_PART = "bodyPart/";
    private static final String API_EXERCISE = "exercise/";
    private static final String API_ALL_BODY_PARTS = "bodyPartList";
    // Query parameter to limit the number of results in API responses
    private static final String API_LIMIT = "?limit=500";
    // API key for accessing the RapidAPI service
    private static final String API_KEY = "0f610d4467mshbc888a46be29965p144ce3jsn1f17ada7a579";
    // Hostname for the RapidAPI service
    private static final String API_HOST = "exercisedb.p.rapidapi.com";

    /**
     * Retrieves a list of exercises based on the specified body part.
     *
     * @param bodyPart the body part for which to retrieve exercises.
     * @return a list of Exercise objects.
     */
    public static List<Exercise> getExercisesByBodyPart(String bodyPart) {
        if (bodyPart == null) {
            return new ArrayList<>();
        }
        // Replaces space character with "%20" URL encode
        bodyPart = bodyPart.replace(" ", "%20");
        // Creates a URI
        String uri = API_URI + API_BODY_PART + bodyPart + API_LIMIT;
        // Gets an HTTP request
        HttpRequest request = getHttpRequest(uri);
        try {
            // Sends a request and retrieve a response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());
            // Creates a TypeToken to parse a list of Exercise objects
            TypeToken<List<Exercise>> token = new TypeToken<>() {
            };
            // Creates a new Gson object
            Gson gson = new Gson();
            // Returns a list of Exercise objects
            return gson.fromJson(response.body(), token);
        } catch (IOException | InterruptedException e) {
            // Throws an exception if an error occurs.
            throw new RuntimeException("An error occurs while connecting to the API."
                                       + e.getMessage());
        }
    }

    /**
     * Retrieves details of an exercise based on the specified exercise ID.
     *
     * @param id the ID of the exercise for which to retrieve details.
     * @return an ExerciseDetails object.
     */
    public static ExerciseDetails getExerciseDetailsById(String id) {
        String uri = API_URI + API_EXERCISE + id;
        HttpRequest request = getHttpRequest(uri);
        try {
            // Sends a request and retrieve a response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            return gson.fromJson(response.body(), ExerciseDetails.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("An error occurs while connecting to the API."
                                       + e.getMessage());
        }
    }

    /**
     * Retrieves a list of all body parts available in the exercise database.
     *
     * @return a list of body parts.
     */
    public static List<String> getBodyParts() {
        HttpRequest request = getHttpRequest(API_URI + API_ALL_BODY_PARTS);
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());
            TypeToken<List<String>> token = new TypeToken<>() {
            };
            Gson gson = new Gson();
            return gson.fromJson(response.body(), token);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("An error occurs while connecting to the API."
                                       + e.getMessage());
        }
    }

    /**
     * Builds and returns an HTTP request with necessary headers.
     *
     * @param uri the URI for the request.
     * @return an HttpRequest object.
     */
    private static HttpRequest getHttpRequest(String uri) {
        // Builds an HTTP request
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", API_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }

    /**
     * Main method for testing the APIUtility class.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        // Test APIUtility class
        List<Exercise> back = getExercisesByBodyPart("back");
        back.forEach(System.out::println);
        System.out.println("Exercise id 0001");
        System.out.println(getExerciseDetailsById("0001"));
        System.out.println("List of the body parts:");
        getBodyParts().forEach(System.out::println);
    }
}
