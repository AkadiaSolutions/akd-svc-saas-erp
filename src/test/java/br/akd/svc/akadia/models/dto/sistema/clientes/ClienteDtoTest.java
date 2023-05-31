package br.akd.svc.akadia.models.dto.sistema.clientes;

import br.akd.svc.akadia.models.dto.sistema.clientes.mocks.ClienteDtoBuilder;
import br.akd.svc.akadia.models.enums.sistema.clientes.StatusClienteEnum;
import br.akd.svc.akadia.models.enums.sistema.clientes.TipoPessoaEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@DisplayName("DTO: ClienteDto")
class ClienteDtoTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ClienteDto(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, dataNascimento=1998-07-21, " +
                        "nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, inscricaoEstadual=145574080114, " +
                        "email=gabrielafonso@mail.com.br, statusCliente=null, tipoPessoa=null, " +
                        "qtdOrdensRealizadas=null, giroTotal=null, exclusaoCliente=null, endereco=null, telefone=null, " +
                        "empresa=null)",
                ClienteDtoBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ClienteDto clienteDto = new ClienteDto(
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
                null
        );
        Assertions.assertEquals(
                "ClienteDto(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, dataNascimento=1998-07-21, " +
                        "nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, inscricaoEstadual=145574080114, " +
                        "email=gabrielafonso@mail.com.br, statusCliente=COMUM, tipoPessoa=FISICA, " +
                        "qtdOrdensRealizadas=0, giroTotal=0.0, exclusaoCliente=null, endereco=null, telefone=null, " +
                        "empresa=null)",
                clienteDto.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ClienteDto clienteDto = ClienteDto.builder()
                .id(1L)
                .dataCadastro(LocalDate.of(2023, 2, 27).toString())
                .horaCadastro(LocalTime.of(17, 40).toString())
                .dataNascimento("1998-07-21")
                .nome("Gabriel Lagrota")
                .cpfCnpj("582.645.389-32")
                .email("gabrielafonso@mail.com.br")
                .endereco(null)
                .telefone(null)
                .empresa(null)
                .build();
        Assertions.assertEquals(
                "ClienteDto(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, dataNascimento=1998-07-21, " +
                        "nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, inscricaoEstadual=null, " +
                        "email=gabrielafonso@mail.com.br, statusCliente=null, tipoPessoa=null, " +
                        "qtdOrdensRealizadas=null, giroTotal=null, exclusaoCliente=null, endereco=null, telefone=null, " +
                        "empresa=null)",
                clienteDto.toString()
        );
    }

}
