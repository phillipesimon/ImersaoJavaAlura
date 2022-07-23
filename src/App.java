import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // Making an HTTP connection
        // URL aula 02 "https://api.mocki.io/v2/549a5d8b"
        // String url =
        // "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        // ExtractContent extract = new ImdbSticker();

        // URL aula 03
        // "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json"
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        ExtractContent extract = new NasaSticker();

        var http = new ClientHttp();
        String json = http.SearchData(url);

        // Show the data
        List<Content> contentList = extract.extractContent(json);

        var generator = new StickerGenerator();

        for (int i = 0; i < 1; i++) {

            Content content = contentList.get(i);

            InputStream inputStream = new URL(content.getUrlImg()).openStream();
            String archiveName = content.getTitle() + ".png";

            generator.createSticker(inputStream, archiveName);
        }
    }
}
