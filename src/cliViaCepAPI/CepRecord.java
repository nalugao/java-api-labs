package cliViaCepAPI;

public record CepRecord(String cep,
                        String logradouro,
                        String bairro,
                        String localidade,
                        String uf) {
}
