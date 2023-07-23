package br.akd.svc.akadia.services.sistema.colaboradores;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.AcessoPageResponse;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcessoEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.AcessoEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.repositories.sistema.colaboradores.ColaboradorRepository;
import br.akd.svc.akadia.repositories.sistema.colaboradores.impl.ColaboradorRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.UnauthorizedAccessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service: Acesso")
class AcessoServiceTest {

    @InjectMocks
    AcessoService acessoService;

    @Mock
    ColaboradorRepository colaboradorRepository;

    @Mock
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @Test
    @DisplayName("Deve testar método de registro de acesso do colaborador com sucesso")
    void deveTestarMetodoDeRegistroDeAcessoDoColaboradorComSucesso() {

        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder()
                .comEmpresa()
                .build();

        when(colaboradorRepository.buscaPorMatricula(any())).thenReturn(Optional.of(ColaboradorEntityBuilder.builder().build()));
        when(colaboradorRepositoryImpl.implementaPersistencia(any())).thenReturn(colaboradorLogado);

        acessoService.registraAcessoColaborador("123456");
    }

    @Test
    @DisplayName("Deve testar método de registro de acesso do colaborador com Exception")
    void deveTestarMetodoDeRegistroDeAcessoDoColaboradorComException() {
        when(colaboradorRepository.buscaPorMatricula(any())).thenReturn(Optional.empty());

        try {
            acessoService.registraAcessoColaborador("123456");
            Assertions.fail();
        } catch (UnauthorizedAccessException unauthorizedAccessException) {
            Assertions.assertEquals("Ops! Ocorreu um erro ao tentar realizar o login. Favor entrar em " +
                    "contato com o suporte técnico", unauthorizedAccessException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de obtenção de acessos do colaborador")
    void deveTestarMetodoDeObtencaoAcessosColaborador() {

        ColaboradorEntity colaboradorLogado = ColaboradorEntityBuilder.builder()
                .comEmpresa()
                .build();

        List<AcessoEntity> acessos = new ArrayList<>();
        acessos.add(AcessoEntityBuilder.builder().build());

        Pageable pageable = PageRequest.of(0, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), acessos.size());
        Page<AcessoEntity> acessosPaged = new PageImpl<>(acessos.subList(start, end), pageable, acessos.size());

        when(colaboradorRepository.buscaAcessosPorIdColaborador(any(), any(), any())).thenReturn(acessosPaged);

        AcessoPageResponse acessoPageResponse =
                acessoService.obtemAcessosColaborador(colaboradorLogado, pageable, 1L);

        Assertions.assertEquals("AcessoPageResponse(content=[AcessoEntity(id=1, dataCadastro=2023-02-13, " +
                "horaCadastro=10:44)], empty=false, first=true, last=true, number=0, numberOfElements=1, offset=0, " +
                "pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, totalElements=1, totalPages=1)",
                acessoPageResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de conversão de entities para PageResponse")
    void deveTestarMetodoDeConversaoDeEntitiesParaPageResponse() {

        List<AcessoEntity> acessos = new ArrayList<>();
        acessos.add(AcessoEntityBuilder.builder().build());

        Pageable pageable = PageRequest.of(0, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), acessos.size());
        Page<AcessoEntity> acessosPaged = new PageImpl<>(acessos.subList(start, end), pageable, acessos.size());

        AcessoPageResponse acessoPageResponse = acessoService.converteListaDeEntitiesParaPageResponse(acessosPaged);

        Assertions.assertEquals("AcessoPageResponse(content=[AcessoEntity(id=1, dataCadastro=2023-02-13, " +
                        "horaCadastro=10:44)], empty=false, first=true, last=true, number=0, numberOfElements=1, " +
                        "offset=0, pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, totalElements=1, " +
                        "totalPages=1)",
                acessoPageResponse.toString());
    }

}
