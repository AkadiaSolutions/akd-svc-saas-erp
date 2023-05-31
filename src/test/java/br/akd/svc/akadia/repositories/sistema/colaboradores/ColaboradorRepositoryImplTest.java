package br.akd.svc.akadia.repositories.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.repositories.sistema.colaboradores.impl.ColaboradorRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("RepositoryImpl: Colaborador")
class ColaboradorRepositoryImplTest {

    @InjectMocks
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @Mock
    ColaboradorRepository colaboradorRepository;

    @Test
    @DisplayName("Deve testar implantação da persistência de novo cliente")
    void deveTestarImplantacaoDaPersistenciaDeNovoCliente() {
        when(colaboradorRepository.save(any())).thenReturn(ColaboradorEntityBuilder.builder().build());
        ColaboradorEntity colaborador = colaboradorRepositoryImpl.implementaPersistencia(ColaboradorEntityBuilder.builder().build());
        Assertions.assertEquals("ColaboradorEntity(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, " +
                "matricula=123456, nome=João da Silva, dataNascimento=2021-04-11, email=joaosilva@gmail.com, " +
                "cpfCnpj=12345678910, salario=2000.0, entradaEmpresa=2023-02-13, saidaEmpresa=null, ocupacao=Técnico " +
                "Interno, tipoOcupacaoEnum=TECNICO_INTERNO, modeloContratacaoEnum=CLT, modeloTrabalhoEnum=PRESENCIAL, " +
                "statusColaboradorEnum=ATIVO, fotoPerfil=null, contratoContratacao=null, exclusao=null, acessoSistema=null, " +
                "configuracaoPerfil=null, endereco=null, telefone=null, expediente=null, dispensa=null, empresa=null)",
                colaborador.toString());
    }
}
