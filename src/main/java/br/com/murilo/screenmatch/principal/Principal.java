package br.com.murilo.screenmatch.principal;

import br.com.murilo.screenmatch.model.DadosEpisodio;
import br.com.murilo.screenmatch.model.DadosSerie;
import br.com.murilo.screenmatch.model.DadosTemporada;
import br.com.murilo.screenmatch.model.Episodio;
import br.com.murilo.screenmatch.service.ConsumoAPI;
import br.com.murilo.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private final Scanner teclado = new Scanner(System.in);
    private final String URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=a8174a12";
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConverteDados converterDados = new ConverteDados();


    public void exibeMenu() {
        System.out.println("Digite o nome da série: ");
        var nomeSerie = teclado.nextLine();
        var json = consumoApi.obterDados(
                URL +
                        nomeSerie.replace(" ", "+") +
                        API_KEY
        );

        var dadosSerie = converterDados.obterDados(json, DadosSerie.class);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            json = consumoApi.obterDados(
                    URL +
                            nomeSerie.replace(" ", "+") + "&season=" + i +
                            API_KEY);

            DadosTemporada dadosTemporada = converterDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()).toList();

        System.out.println("\nTop 10 episódios melhor avaliados: ");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primeiro Filtro(N/A) " + e))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(10)
                .map(e -> e.titulo().toUpperCase())
                .forEach(System.out::println);

//        List<Episodio> episodios = temporadas.stream()
//                .flatMap(t -> t.episodios().stream()
//                        .map(d -> new Episodio(t.numero(), d))
//                ).collect(Collectors.toList());
//
//        System.out.println("A partir de que ano você deseja ver os episódios? ");
//        var ano = teclado.nextInt();
//        teclado.nextLine();
//
//        LocalDate data = LocalDate.of(ano, 1, 1);
//
//
//        episodios.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(data))
//                .forEach(System.out::println);

    }

}
