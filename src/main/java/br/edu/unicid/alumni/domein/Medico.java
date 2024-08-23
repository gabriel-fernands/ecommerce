package br.edu.unicid.alumni.domein;

import br.edu.unicid.alumni.dtos.DadosCadastroMedico;
import br.edu.unicid.alumni.enus.Especialidade;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    @Embedded
    private Endereco endereco;
    @Enumerated
    private Especialidade especialidade;

    public Medico(DadosCadastroMedico cadastroMedico) {
        this.nome = cadastroMedico.nome();
        this.email = cadastroMedico.email();
        this.crm = cadastroMedico.crm();
        this.especialidade = cadastroMedico.especialidade();
        this.endereco = new Endereco(cadastroMedico.endereco());
    }
}
