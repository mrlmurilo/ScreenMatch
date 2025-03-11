package br.com.murilo.screenmatch;

import br.com.murilo.screenmatch.model.DadosEpisodio;
import br.com.murilo.screenmatch.model.DadosSerie;
import br.com.murilo.screenmatch.model.DadosTemporada;
import br.com.murilo.screenmatch.principal.Principal;
import br.com.murilo.screenmatch.service.ConsumoAPI;
import br.com.murilo.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    private final String apiKey = "a8174a12";

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);

    }

    @Override
    public void run(String... args) {
        Principal principal = new Principal();
        principal.exibeMenu();
    }
}
