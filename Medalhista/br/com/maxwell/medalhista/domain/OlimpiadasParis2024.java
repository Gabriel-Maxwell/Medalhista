package br.com.maxwell.medalhista.domain;

import br.com.maxwell.medalhista.enums.TipoMedalha;
import br.com.maxwell.medalhista.utils.DateUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class OlimpiadasParis2024 {
    public static void main(String[] args) {
        File file = new File("medallists.csv");
        List<Medalhista> medalhistas = new ArrayList<>();

        try (Scanner sc = new Scanner(file)) {
            if (sc.hasNextLine()) sc.nextLine(); // Ignora a primeira linha (cabeçalho)
            //name, medal_type, medal_date,gender,  birth_date,  country,  discipline,  event
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] dados = line.split(",");
                String name = dados[0];
                String medalType = dados [1];
                String medalDate = dados[2];
                String gender = dados[3];
                String birthDate = dados [4];
                String country = dados[5];
                String discipline = dados[6];
                String event = dados[7];

                Medalhista medalhista = new Medalhista(name, gender, DateUtil.convertDate(birthDate), country);
                Medalha medalha = new Medalha(TipoMedalha.valueOf(medalType), DateUtil.convertDate(medalDate), discipline, event);

                Optional<Medalhista> medalhistaOpt = medalhistas.stream()
                        .filter(m -> m.getName().equals(name))
                        .findFirst();
                if (medalhistaOpt.isPresent()) {
                    medalhista = medalhistaOpt.get();
                } else {
                    medalhistas.add(medalhista);
                }
                medalhista.incluirMedalha(medalha);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }

        
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("FIM")) break;

            String[] consulta = line.split(",");
            String nomeAtleta = consulta[0];
            TipoMedalha tipoMedalhaConsulta = TipoMedalha.valueOf(consulta[1].toUpperCase());

            Optional<Medalhista> medalhistaOpt = medalhistas.stream()
                    .filter(m -> m.getName().equals(nomeAtleta))
                    .findFirst();

                    if (medalhistaOpt.isPresent()) {
                        Medalhista medalhista = medalhistaOpt.get();
                        System.out.println(medalhista);
                        
                        Arrays.stream(medalhista.getMedals()).filter(m -> m.getMetalType().equals(tipoMedalhaConsulta)).forEach(System.out::println);
                

                        // for (Medalha medalha : medalhista.getMedals()) {
                        //     if (medalha != null && medalha.getMetalType().equals(tipoMedalhaConsulta)) {
                        //         System.out.println(medalha);
                        //     }
                        // }
                    }
            // LEAL Rayssa,BRONZE
        }
    }
}
