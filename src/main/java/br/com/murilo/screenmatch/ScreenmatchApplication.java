package br.com.murilo.screenmatch;

import br.com.murilo.screenmatch.model.DadosEpisodio;
import br.com.murilo.screenmatch.model.DadosSerie;
import br.com.murilo.screenmatch.service.ConsumoAPI;
import br.com.murilo.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    private final String apiKey = "a8174a12";

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);

    }

    @Override
    public void run(String... args) {

        final var consumoAPI = new ConsumoAPI();
        Scanner teclado = new Scanner(System.in);
        String serie = teclado.nextLine();
        var json = consumoAPI.obterDados("https://www.omdbapi.com/?t=" + serie + "&apikey=" + apiKey);
        ConverteDados conversor = new ConverteDados();
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados.toString());
        json = consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=" + apiKey);
        DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
        System.out.println(dadosEpisodio.toString());
    }
}
