package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModeloContratacaoEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModeloTrabalhoEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.StatusColaboradorEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TipoOcupacaoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootTest
@DisplayName("Entity: Colaborador")
class ColaboradorEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ColaboradorEntity(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, matricula=123456, " +
                        "nome=João da Silva, dataNascimento=2021-04-11, email=joaosilva@gmail.com, cpfCnpj=12345678910, " +
                        "salario=2000.0, entradaEmpresa=2023-02-13, saidaEmpresa=null, ocupacao=Técnico Interno, " +
                        "tipoOcupacaoEnum=TECNICO_INTERNO, modeloContratacaoEnum=CLT, modeloTrabalhoEnum=PRESENCIAL, " +
                        "statusColaboradorEnum=ATIVO, fotoPerfil=null, contratoContratacao=null, exclusao=null, " +
                        "acessoSistema=null, configuracaoPerfil=null, endereco=null, telefone=null, expediente=null, " +
                        "dispensa=null, empresa=null)",
                ColaboradorEntityBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ColaboradorEntity colaboradorEntity = new ColaboradorEntity(
                1L,
                LocalDate.of(2023, 2, 13).toString(),
                LocalTime.of(11, 17).toString(),
                "123456",
                "Fulano",
                "11-11-2011",
                "fulano@gmail.com",
                "10987654321",
                3400.0,
                LocalDate.of(2023, 11, 1).toString(),
                null,
                "Administrador",
                TipoOcupacaoEnum.ADMINISTRATIVO,
                ModeloContratacaoEnum.CLT,
                ModeloTrabalhoEnum.HIBRIDO,
                StatusColaboradorEnum.FERIAS,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                null
        );
        Assertions.assertEquals(
                "ColaboradorEntity(id=1, dataCadastro=2023-02-13, horaCadastro=11:17, matricula=123456, " +
                        "nome=Fulano, dataNascimento=11-11-2011, email=fulano@gmail.com, cpfCnpj=10987654321, " +
                        "salario=3400.0, entradaEmpresa=2023-11-01, saidaEmpresa=null, ocupacao=Administrador, " +
                        "tipoOcupacaoEnum=ADMINISTRATIVO, modeloContratacaoEnum=CLT, modeloTrabalhoEnum=HIBRIDO, " +
                        "statusColaboradorEnum=FERIAS, fotoPerfil=null, contratoContratacao=null, exclusao=null, " +
                        "acessoSistema=null, configuracaoPerfil=null, endereco=null, telefone=null, expediente=null, " +
                        "dispensa=null, empresa=null)",
                colaboradorEntity.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ColaboradorEntity colaboradorEntity = ColaboradorEntity.builder()
                .id(1L)
                .dataCadastro(LocalDate.of(2023, 2, 13).toString())
                .horaCadastro(LocalTime.of(11, 18).toString())
                .matricula("123456")
                .nome("Ciclano Nogueira")
                .dataNascimento(LocalDate.of(1972, 6, 4).toString())
                .email("ciclanonogueira@uol.com.br")
                .cpfCnpj("12436585312")
                .salario(1800.0)
                .entradaEmpresa(LocalDate.of(2018, 8, 12).toString())
                .saidaEmpresa(LocalDate.of(20222, 12, 1).toString())
                .ocupacao("Técnico geral")
                .tipoOcupacaoEnum(TipoOcupacaoEnum.TECNICO)
                .modeloContratacaoEnum(ModeloContratacaoEnum.FREELANCER)
                .modeloTrabalhoEnum(ModeloTrabalhoEnum.PRESENCIAL)
                .statusColaboradorEnum(StatusColaboradorEnum.DISPENSADO)
                .fotoPerfil(null)
                .contratoContratacao(null)
                .exclusao(null)
                .acessoSistema(null)
                .configuracaoPerfil(null)
                .endereco(null)
                .telefone(null)
                .expediente(null)
                .dispensa(null)
                .pontos(new ArrayList<>())
                .historicoFerias(new ArrayList<>())
                .advertencias(new ArrayList<>())
                .acessos(new ArrayList<>())
                .acoes(new ArrayList<>())
                .empresa(null)
                .build();
        Assertions.assertEquals(
                "ColaboradorEntity(id=1, dataCadastro=2023-02-13, horaCadastro=11:18, matricula=123456, " +
                        "nome=Ciclano Nogueira, dataNascimento=1972-06-04, email=ciclanonogueira@uol.com.br, " +
                        "cpfCnpj=12436585312, salario=1800.0, entradaEmpresa=2018-08-12, saidaEmpresa=+20222-12-01, " +
                        "ocupacao=Técnico geral, tipoOcupacaoEnum=TECNICO, modeloContratacaoEnum=FREELANCER, " +
                        "modeloTrabalhoEnum=PRESENCIAL, statusColaboradorEnum=DISPENSADO, fotoPerfil=null, " +
                        "contratoContratacao=null, exclusao=null, acessoSistema=null, configuracaoPerfil=null, " +
                        "endereco=null, telefone=null, expediente=null, dispensa=null, empresa=null)",
                colaboradorEntity.toString()
        );
    }

}
