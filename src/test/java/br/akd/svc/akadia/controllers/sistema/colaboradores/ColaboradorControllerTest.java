package br.akd.svc.akadia.controllers.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.repositories.sistema.colaboradores.impl.ColaboradorRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Controller: Colaborador")
class ColaboradorControllerTest {

    @InjectMocks
    ColaboradorController colaboradorController;

    @Mock
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @Test
    @DisplayName("Deve testar método controlador de criação de novo colaborador")
    void deveTestarMetodoControladorDeCriacaoDeNovoColaborador() {
        when(colaboradorRepositoryImpl.buscaTodosOsColaboradores()).thenReturn(new ArrayList<>());
        ResponseEntity<List<ColaboradorEntity>> colaborador = colaboradorController.listaTodosColaboradores();
        Assertions.assertEquals("<200 OK OK,[],[]>", colaborador.toString());
    }

}
