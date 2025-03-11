package br.com.murilo.screenmatch.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Getter
@Setter
@ToString
public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numero;
    private Double avaliacao;
    private LocalDate dataLancamento;

    public Episodio(Integer temporada, DadosEpisodio de) {
        this.temporada = temporada;
        titulo = de.titulo();
        numero = de.numero();
        try {
            avaliacao = Double.valueOf(de.avaliacao());
        } catch (NumberFormatException e) {
            avaliacao = 0d;
        }
        try {
            dataLancamento = LocalDate.parse(de.dataLancamento());
        } catch (DateTimeParseException e) {
            dataLancamento = null;
        }
    }
}
