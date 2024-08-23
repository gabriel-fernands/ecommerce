package br.edu.unicid.alumni.medicoTest;

import br.edu.unicid.alumni.controller.MedicoController;
import br.edu.unicid.alumni.domein.Medico;
import br.edu.unicid.alumni.service.MedicoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
public class MedicoControllerTest {

    @Mock
    private MedicoService service;

    @InjectMocks
    private MedicoController controller;

    @Test
    public void testUpdate() {
        Long id = 1L;
        Medico medico = new Medico();
        medico.setId(id);
        when(service.update(medico)).thenReturn(medico);

        ResponseEntity<Medico> response = controller.update(id, medico);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medico, response.getBody());
        verify(service).update(medico);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        doNothing().when(service).deletePorId(id);

        ResponseEntity<Void> response = controller.deleteById(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service).deletePorId(id);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Medico medico = new Medico();
        when(service.encontrarPorId(id)).thenReturn(Optional.of(medico));

        ResponseEntity<Medico> response = controller.findById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medico, response.getBody());
        verify(service).encontrarPorId(id);
    }

    @Test
    public void testFindByIdNotFound() {
        Long id = 1L;
        when(service.encontrarPorId(id)).thenReturn(Optional.empty());

        ResponseEntity<Medico> response = controller.findById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(service).encontrarPorId(id);
    }
}
