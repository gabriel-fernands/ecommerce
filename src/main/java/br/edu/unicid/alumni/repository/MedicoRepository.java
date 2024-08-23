package br.edu.unicid.alumni.repository;

import br.edu.unicid.alumni.domein.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico,Long> {

    }

