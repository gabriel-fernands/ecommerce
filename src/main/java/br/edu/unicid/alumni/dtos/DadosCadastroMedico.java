package br.edu.unicid.alumni.dtos;

import br.edu.unicid.alumni.domein.Endereco;
import br.edu.unicid.alumni.enus.Especialidade;


public record DadosCadastroMedico(String nome, String email,String crm, DadosEnderecoMedico endereco, Especialidade especialidade) {
}
