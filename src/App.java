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
        // Fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);

        // Extrair somente os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        // System.out.println(listaDeFilmes.size());

        // Exibir os dados
        var generator = new StickerGenetaror();
        for (Map<String, String> filme : listaDeFilmes) {
            // var IMDB = Float.parseFloat(filme.get("imDbRating"));
            // if (IMDB >= 8) {
            // System.out.println("🌟🌟🌟🌟🌟");
            // }
            // if (IMDB >= 6 && IMDB < 8) {
            // System.out.println("🌟🌟🌟");
            // }
            // if (IMDB < 6) {
            // System.out.println("🌟");
            // }
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String archiveName = titulo + ".png";

            generator.createSticker(inputStream, archiveName);
        }
    }
}
