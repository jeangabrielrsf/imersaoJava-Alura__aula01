import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        //fazer a conexão HTTP e buscar as melhores séries de TV
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que nos interessam (título, poster, nota)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> series = parser.parse(body);

        //exibir e manipular dados
        for (Map<String, String> serie : series) {
            System.out.println("\u001b[1mTítulo: \u001b[m" + serie.get("title"));
            System.out.println("\u001b[1mPoster: \u001b[m" + serie.get("image"));
            System.out.println("\u001b[30m\u001b[43mClassificação: " + serie.get("imDbRating")+ "\u001b[m");

            for (int i = 0; i < (int) Double.parseDouble(serie.get("imDbRating")); i++) {
                System.out.print("\u2B50");
            }
            System.out.println("\n");
        }
    }
}
