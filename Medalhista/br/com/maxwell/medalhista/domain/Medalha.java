package br.com.maxwell.medalhista.domain;
import br.com.maxwell.medalhista.enums.TipoMedalha;
import java.time.LocalDate;

public class Medalha {
    private TipoMedalha metalType;
    private LocalDate medalDate;
    private String discipline;
    private String event;
    
    public Medalha(TipoMedalha metalType, LocalDate medalDate, String discipline, String event) {
        this.metalType = metalType;
        this.medalDate = medalDate;
        this.discipline = discipline;
        this.event = event;
    }

    public TipoMedalha getMetalType() {
        return metalType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Medal type:  ").append(metalType);
        sb.append(" discipline ").append(discipline);
        sb.append(", event ").append(event);
    
        return sb.toString();
    }
    
    
}
