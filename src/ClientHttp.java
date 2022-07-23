import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClientHttp {
  public String SearchData(String url) {

    try {

      URI address = URI.create(url);
      var client = HttpClient.newHttpClient();
      var resquest = HttpRequest.newBuilder(address).GET().build();
      HttpResponse<String> response = client.send(resquest, BodyHandlers.ofString());
      String body = response.body();
      return body;

    } catch (IOException | InterruptedException ex) {
      throw new RuntimeException(ex);
    }

  }
}
