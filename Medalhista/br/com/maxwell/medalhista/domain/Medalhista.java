package br.com.maxwell.medalhista.domain;

import br.com.maxwell.medalhista.enums.TipoMedalha;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Medalhista {
    private static final int MAX_MEDALHAS = 8;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String country;
    private Medalha[] medals;
    private int medalCount;

    public Medalhista(String name, String gender, LocalDate birthDate, String country) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.country = country;
        this.medals = new Medalha[MAX_MEDALHAS];
        this.medalCount = 0;
    }

    public int totalDeMedalhas() {
        return medalCount;
    }

    public Medalha[] ouros() {
        return Arrays.stream(medals)
                .filter(Objects::nonNull)
                .filter(m -> TipoMedalha.OURO.equals(m.getMetalType()))
                .toArray(Medalha[]::new);
    }

    public Medalha[] pratas() {
        return Arrays.stream(medals)
                .filter(Objects::nonNull)
                .filter(m -> TipoMedalha.PRATA.equals(m.getMetalType()))
                .toArray(Medalha[]::new);
    }

    public Medalha[] bronzes() {
        return Arrays.stream(medals)
                .filter(Objects::nonNull)
                .filter(m -> TipoMedalha.BRONZE.equals(m.getMetalType()))
                .toArray(Medalha[]::new);
    }

    public int incluirMedalha(Medalha medalha) {
        if (medalha == null) {
            System.out.println("Error: Null medal cannot be added.");
            return -1;
        }
        if (medalCount >= MAX_MEDALHAS) {
            System.out.println("Error: Medal array is full.");
            return -1;
        }
        medals[medalCount] = medalha;
        medalCount++;
        return medalCount - 1;
    }

    public Medalha[] getMedals() {
        return Arrays.copyOf(medals, medalCount); // Return only the filled portion of the array
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Nome: " + name +
                ", Genero: " + gender +
                ", Data de Nascimento: " + birthDate +
                ", País: " + country +
                ", Medalha: " + Arrays.stream(medals) 
                .filter(Objects::nonNull)
                .map(Medalha::toString)
                .collect(Collectors.joining(", ")) +
                ", Número de medalhas: " + medalCount;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medalhista that = (Medalhista) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}