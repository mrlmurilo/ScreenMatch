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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    private final String apiKey = "a8174a12";

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);

    }

    @Override
    public void run(String... args) {
//        Principal principal = new Principal();
//        principal.exibeMenu();

        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );
//
//        Map<String, List<Produto>> produtosAgrupados = produtos.stream()
//                .collect(Collectors.groupingBy(Produto::getCategoria));
//        System.out.println(produtosAgrupados);
//
//        Map<String, Long> contagemPorCategoria = produtos.stream()
//                .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.counting()));
//        System.out.println(contagemPorCategoria);
//
//        Map<String, Optional<Produto>> maisCaroPorCategoria = produtos.stream()
//                .collect(Collectors.groupingBy(Produto::getCategoria,
//                        Collectors.maxBy(Comparator.comparingDouble(Produto::getPreco))));
//
//        System.out.println(maisCaroPorCategoria);

        Map<String, Double> precosPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.summingDouble(Produto::getPreco)));

        System.out.println(precosPorCategoria);

    }

    private class Produto {
        private String nome;
        private double preco;
        private String categoria;

        public Produto(String nome, double preco, String categoria) {
            this.nome = nome;
            this.preco = preco;
            this.categoria = categoria;
        }

        public String getNome() {
            return nome;
        }

        public double getPreco() {
            return preco;
        }

        public String getCategoria() {
            return categoria;
        }

        @Override
        public String toString() {
            return "Produto{" +
                    "nome='" + nome + '\'' +
                    ", preco=" + preco +
                    ", categoria='" + categoria + '\'' +
                    '}';
        }
    }
}
