package common.chatGPT;

import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    public void getTextResponse() throws Exception {
        //header, header key and url will be constants
        //the get response is what will be different

        Transcript transcript = new Transcript();
        transcript.setSomething("url here");
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI(""))
                .header("something", Constants.API_KEY) //need to get authorization header
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        //to use the body of the postResponse use postResponse.body()
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());

        transcript = gson.fromJson(postResponse.body(), Transcript.class);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("")) //need to append transcript.getId() to end or uri
                .header("something", Constants.API_KEY)
                .build();

        while(true) {
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);
            System.out.println(transcript.getStatus());

            if("completed".equals(transcript.getStatus()) || "error".equals(transcript.getStatus())){
                break;
            }
            Thread.sleep(1000);
        }
        System.out.println("Transcription completed");
        System.out.println(transcript.getText());
    }


    public ApiClient() throws URISyntaxException {
    }
}

















