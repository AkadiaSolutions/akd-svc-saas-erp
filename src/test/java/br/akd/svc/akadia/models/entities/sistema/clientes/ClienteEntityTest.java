package br.akd.svc.akadia.models.entities.sistema.clientes;

import br.akd.svc.akadia.models.entities.sistema.clientes.mocks.ClienteEntityBuilder;
import br.akd.svc.akadia.modules.erp.clientes.models.enums.StatusClienteEnum;
import br.akd.svc.akadia.modules.erp.clientes.models.enums.TipoPessoaEnum;
import br.akd.svc.akadia.modules.erp.clientes.models.entity.ClienteEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: ClienteEntity")
class ClienteEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, dataNascimento=1998-07-21, " +
                        "nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, inscricaoEstadual=145574080114, " +
                        "email=gabrielafonso@mail.com.br, statusCliente=null, tipoPessoa=null, " +
                        "qtdOrdensRealizadas=null, giroTotal=null, exclusao=null, endereco=null, telefone=null, " +
                        "colaboradorResponsavel=null, empresa=null)",
                ClienteEntityBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ClienteEntity clienteEntity = new ClienteEntity(
                1L,
                LocalDate.of(2023, 2, 27).toString(),
                LocalTime.of(17, 40).toString(),
                "1998-07-21",
                "Gabriel Lagrota",
                "582.645.389-32",
                "145574080114",
                "gabrielafonso@mail.com.br",
                StatusClienteEnum.COMUM,
                TipoPessoaEnum.FISICA,
                0,
                0.0,
                null,
                null,
                null,
                null,
                null
        );
        Assertions.assertEquals(
                "ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, dataNascimento=1998-07-21, " +
                        "nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, inscricaoEstadual=145574080114, " +
                        "email=gabrielafonso@mail.com.br, statusCliente=COMUM, tipoPessoa=FISICA, " +
                        "qtdOrdensRealizadas=0, giroTotal=0.0, exclusao=null, endereco=null, telefone=null, " +
                        "colaboradorResponsavel=null, empresa=null)",
                clienteEntity.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ClienteEntity clienteEntity = ClienteEntity.builder()
                .id(1L)
                .dataCadastro(LocalDate.of(2023, 2, 27).toString())
                .horaCadastro(LocalTime.of(17, 40).toString())
                .dataNascimento("1998-07-21")
                .nome("Gabriel Lagrota")
                .cpfCnpj("582.645.389-32")
                .inscricaoEstadual("145574080114")
                .email("gabrielafonso@mail.com.br")
                .endereco(null)
                .telefone(null)
                .colaboradorResponsavel(null)
                .empresa(null)
                .build();
        Assertions.assertEquals(
                "ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, dataNascimento=1998-07-21, " +
                        "nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, inscricaoEstadual=145574080114, " +
                        "email=gabrielafonso@mail.com.br, statusCliente=null, tipoPessoa=null, " +
                        "qtdOrdensRealizadas=null, giroTotal=null, exclusao=null, endereco=null, telefone=null, " +
                        "colaboradorResponsavel=null, empresa=null)",
                clienteEntity.toString()
        );
    }

}
