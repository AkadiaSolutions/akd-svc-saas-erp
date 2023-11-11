package br.akd.svc.akadia.repositories.sistema.colaboradores;

import br.akd.svc.akadia.modules.global.entity.ArquivoEntity;
import br.akd.svc.akadia.models.entities.global.mocks.ArquivoEntityBuilder;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.repository.ColaboradorRepository;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.repository.impl.ColaboradorRepositoryImpl;
import br.akd.svc.akadia.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("RepositoryImpl: Colaborador")
class ColaboradorRepositoryImplTest {

    @InjectMocks
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @Mock
    ColaboradorRepository colaboradorRepository;

    @Test
    @DisplayName("Deve testar método de implementação de persistência")
    void deveTestarMetodoDeimplementacaoDePersistencia() {

        when(colaboradorRepository.save(any()))
                .thenReturn(ColaboradorEntityBuilder.builder().build());

        ColaboradorEntity colaboradorEntity = colaboradorRepositoryImpl
                .implementaPersistencia(ColaboradorEntityBuilder.builder().build());

        assertEquals("ColaboradorEntity(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, matricula=123456, " +
                        "nome=João da Silva, dataNascimento=2021-04-11, email=joaosilva@gmail.com, " +
                        "cpfCnpj=12345678910, salario=2000.0, entradaEmpresa=2023-02-13, saidaEmpresa=null, " +
                        "ocupacao=Técnico Interno, tipoOcupacaoEnum=TECNICO_INTERNO, modeloContratacaoEnum=CLT, " +
                        "modeloTrabalhoEnum=PRESENCIAL, statusColaboradorEnum=ATIVO, fotoPerfil=null, " +
                        "contratoContratacao=null, exclusao=null, acessoSistema=null, configuracaoPerfil=null, " +
                        "endereco=null, telefone=null, expediente=null, dispensa=null, empresa=null)",
                colaboradorEntity.toString());
    }

    @Test
    @DisplayName("Deve testar método de implementação de persistência em massa")
    void deveTestarMetodoDeimplementacaoDePersistenciaEmMassa() {

        List<ColaboradorEntity> colaboradores = new ArrayList<>();
        colaboradores.add(ColaboradorEntityBuilder.builder().build());

        try {
            colaboradorRepositoryImpl.implementaPersistenciaEmMassa(colaboradores);
            assertDoesNotThrow(() -> new RuntimeException());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por id com sucesso")
    void deveTestarMetodoDeimplementacaoDeBuscaPorIdComSucesso() {

        when(colaboradorRepository.buscaPorId(anyLong(), anyLong()))
                .thenReturn(Optional.of(ColaboradorEntityBuilder.builder().build()));

        ColaboradorEntity colaboradorEntity = colaboradorRepositoryImpl
                .implementaBuscaPorId(1L, 1L);

        assertEquals("ColaboradorEntity(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, matricula=123456, " +
                        "nome=João da Silva, dataNascimento=2021-04-11, email=joaosilva@gmail.com, " +
                        "cpfCnpj=12345678910, salario=2000.0, entradaEmpresa=2023-02-13, saidaEmpresa=null, " +
                        "ocupacao=Técnico Interno, tipoOcupacaoEnum=TECNICO_INTERNO, modeloContratacaoEnum=CLT, " +
                        "modeloTrabalhoEnum=PRESENCIAL, statusColaboradorEnum=ATIVO, fotoPerfil=null, " +
                        "contratoContratacao=null, exclusao=null, acessoSistema=null, configuracaoPerfil=null, " +
                        "endereco=null, telefone=null, expediente=null, dispensa=null, empresa=null)",
                colaboradorEntity.toString());
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por id com erro")
    void deveTestarMetodoDeimplementacaoDeBuscaPorIdComErro() {
        when(colaboradorRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ObjectNotFoundException.class, () ->
                colaboradorRepositoryImpl.implementaBuscaPorId(1L, 1L));
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca de imagem de perfil por id com sucesso")
    void deveTestarMetodoDeimplementacaoDeBuscaDeImagemDePerfilIdComSucesso() {

        when(colaboradorRepository.buscaImagemPerfilPorId(anyLong(), anyLong()))
                .thenReturn(Optional.of(ArquivoEntityBuilder.builder().build()));

        ArquivoEntity arquivoEntity = colaboradorRepositoryImpl
                .implementaBuscaDeImagemDePerfilPorId(1L, 1L);

        assertEquals("ArquivoEntity(id=1, nome=arquivo.pdf, tamanho=1000, tipo=PDF, arquivo=[])",
                arquivoEntity.toString());
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca de imagem de perfil por id com erro")
    void deveTestarMetodoDeimplementacaoDeBuscaDeImagemDePerfilPorIdComErro() {
        when(colaboradorRepository.buscaImagemPerfilPorId(anyLong(), anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ObjectNotFoundException.class, () ->
                colaboradorRepositoryImpl.implementaBuscaDeImagemDePerfilPorId(1L, 1L));
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por todos os itens")
    void deveTestarMetodoDeimplementacaoDeBuscaPorTodosOsItens() {

        List<ColaboradorEntity> colaboradorEntities = new ArrayList<>();
        colaboradorEntities.add(ColaboradorEntityBuilder.builder().build());

        when(colaboradorRepository.buscaTodos(anyLong()))
                .thenReturn(colaboradorEntities);

        List<ColaboradorEntity> retornoConsulta = colaboradorRepositoryImpl
                .implementaBuscaPorTodos(1L);

        assertEquals("[ColaboradorEntity(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, matricula=123456, " +
                        "nome=João da Silva, dataNascimento=2021-04-11, email=joaosilva@gmail.com, " +
                        "cpfCnpj=12345678910, salario=2000.0, entradaEmpresa=2023-02-13, saidaEmpresa=null, " +
                        "ocupacao=Técnico Interno, tipoOcupacaoEnum=TECNICO_INTERNO, modeloContratacaoEnum=CLT, " +
                        "modeloTrabalhoEnum=PRESENCIAL, statusColaboradorEnum=ATIVO, fotoPerfil=null, " +
                        "contratoContratacao=null, exclusao=null, acessoSistema=null, configuracaoPerfil=null, " +
                        "endereco=null, telefone=null, expediente=null, dispensa=null, empresa=null)]",
                retornoConsulta.toString());
    }


    @Test
    @DisplayName("Deve testar método de implementação de busca por id em massa com sucesso")
    void deveTestarMetodoDeimplementacaoDeBuscaPorIdEmMassaComSucesso() {

        List<ColaboradorEntity> colaboradorEntities = new ArrayList<>();
        colaboradorEntities.add(ColaboradorEntityBuilder.builder().build());

        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        when(colaboradorRepository.findAllById(any()))
                .thenReturn(colaboradorEntities);

        List<ColaboradorEntity> retornoConsulta = colaboradorRepositoryImpl
                .implementaBuscaPorIdEmMassa(ids);

        assertEquals("[ColaboradorEntity(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, matricula=123456, " +
                        "nome=João da Silva, dataNascimento=2021-04-11, email=joaosilva@gmail.com, " +
                        "cpfCnpj=12345678910, salario=2000.0, entradaEmpresa=2023-02-13, saidaEmpresa=null, " +
                        "ocupacao=Técnico Interno, tipoOcupacaoEnum=TECNICO_INTERNO, modeloContratacaoEnum=CLT, " +
                        "modeloTrabalhoEnum=PRESENCIAL, statusColaboradorEnum=ATIVO, fotoPerfil=null, " +
                        "contratoContratacao=null, exclusao=null, acessoSistema=null, configuracaoPerfil=null, " +
                        "endereco=null, telefone=null, expediente=null, dispensa=null, empresa=null)]",
                retornoConsulta.toString());
    }

    @Test
    @DisplayName("Deve testar método de implementação de busca por id em massa com erro")
    void deveTestarMetodoDeimplementacaoDeBuscaPorIdEmMassaComErro() {

        List<ColaboradorEntity> colaboradorEntities = new ArrayList<>();

        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        when(colaboradorRepository.findAllById(any()))
                .thenReturn(colaboradorEntities);

        assertThrows(ObjectNotFoundException.class,
                () -> colaboradorRepositoryImpl.implementaBuscaPorIdEmMassa(ids));
    }
}
