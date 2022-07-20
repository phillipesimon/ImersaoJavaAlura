import java.net.URI;
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
        for (Map<String, String> filme : listaDeFilmes) {
            var IMDB = Float.parseFloat(filme.get("imDbRating"));
            if (IMDB >= 8) {
                System.out.println("🌟🌟🌟🌟🌟");
            }
            if (IMDB >= 6 && IMDB < 8) {
                System.out.println("🌟🌟🌟");
            }
            if (IMDB < 6) {
                System.out.println("🌟");
            }
            System.out.println("Filme: " + "\u001b[1m " + filme.get("title") + "\u001b[0m");
            System.out.println("Poster: " + "\u001b[1m " + filme.get("image") + "\u001b[0m");
            System.out.println("IMDB Rating: " + "\u001b[1m " + filme.get("imDbRating") + "\u001b[0m");
            System.out.println("");
        }
    }
}
