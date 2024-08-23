package br.edu.unicid.alumni.service;

import br.edu.unicid.alumni.domein.Medico;
import br.edu.unicid.alumni.exception.ResourceEcommerceNotFoundException;
import br.edu.unicid.alumni.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MedicoService {

    private final MedicoRepository repository;


    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Medico create(Medico medico) {
        return repository.save(medico);
    }

    public List<Medico> listar() {
        return repository.findAll();
    }

    public Medico update(final Medico medico) {
        return Optional.ofNullable(medico)
                .map(repository::save)
                .orElseThrow(() -> new ResourceEcommerceNotFoundException("Doctor not found with id: " + medico.getId()));
    }

    public void deletePorId(final Long id) {
        Optional.ofNullable(id)
                .ifPresent(repository::deleteById);
    }

    public Optional<Medico> encontrarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Medico> listarOrdenado() {
        return repository.findAll(getBy());
    }

    private static Sort getBy() {
        return Sort.by(Sort.Order.asc("nome"), Sort.Order.asc("especialidade"));
    }

    public Page<Medico> paginar(Pageable pageable) {
        return Optional.ofNullable(pageable)
                .map(repo -> repository.findAll(repo))
                .orElseThrow(() -> new IllegalArgumentException("the Pageable object cannot be null."));
    }

}
