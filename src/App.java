import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Making an HTTP connection and get the top 250 movies
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);

        // Extract only the data that matter (title, poster, rating)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        // System.out.println(listaDeFilmes.size());

        // Show the data
        var generator = new StickerGenetaror();
        for (Map<String, String> filme : listaDeFilmes) {
            String urlImagem = filme.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String archiveName = titulo + ".png";

            generator.createSticker(inputStream, archiveName);
        }
    }
}
