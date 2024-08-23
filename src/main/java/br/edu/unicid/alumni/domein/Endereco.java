package br.edu.unicid.alumni.domein;


import br.edu.unicid.alumni.dtos.DadosEnderecoMedico;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    private String logradoura;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;


    public Endereco(DadosEnderecoMedico endereco) {
        this.logradoura = endereco.logradoura();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
        this.cep = endereco.cep();
    }
}
