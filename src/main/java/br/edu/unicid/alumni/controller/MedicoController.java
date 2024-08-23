package br.edu.unicid.alumni.controller;

import br.edu.unicid.alumni.domein.Medico;
import br.edu.unicid.alumni.dtos.DadosCadastroMedico;
import br.edu.unicid.alumni.exception.ResourceEcommerceNotFoundException;
import br.edu.unicid.alumni.service.MedicoService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Medico> create(@RequestBody DadosCadastroMedico cadastroMedico) {
        return Optional.ofNullable(cadastroMedico)
                .map((DadosCadastroMedico medico) -> service.create(new Medico(cadastroMedico)))
                .map(createdMedico -> ResponseEntity.status(HttpStatus.CREATED).body(createdMedico))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }



    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Medico> update(@PathVariable Long id, @RequestBody Medico medico) {
        return Optional.ofNullable(id)
                .filter(i -> medico != null)
                .map(i -> {
                    medico.setId(i);
                    return service.update(medico);
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<Page<Medico>> paginate(@PageableDefault(size = 5, sort = "nome") Pageable pageable) {
        return Optional.of(service.paginar(pageable))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            service.deletePorId(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceEcommerceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    @GetMapping("/{id}")
    public ResponseEntity<Medico> findById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        var medico = service.encontrarPorId(id);
        return medico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
