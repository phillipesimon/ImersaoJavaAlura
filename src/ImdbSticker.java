import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImdbSticker implements ExtractContent {

  public List<Content> extractContent(String json) {

    // Extract only the data that matter (title, poster, rating)
    var parser = new JsonParser();
    List<Map<String, String>> attributesList = parser.parse(json);

    List<Content> contents = new ArrayList<>();

    for (Map<String, String> attributes : attributesList) {
      String title = attributes.get("title");
      String urlImagem = attributes.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
      var content = new Content(title, urlImagem);

      contents.add(content);
    }

    return contents;
  }

}
