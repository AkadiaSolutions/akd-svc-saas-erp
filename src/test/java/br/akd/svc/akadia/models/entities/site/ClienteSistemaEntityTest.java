package br.akd.svc.akadia.models.entities.site;

import br.akd.svc.akadia.models.entities.site.mocks.ClienteSistemaEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootTest
@DisplayName("Entity: ClienteSistema")
class ClienteSistemaEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, " +
                        "horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=Fulano, " +
                        "senha=123, cpf=12345678910, saldo=0.0, plano=null, telefone=null, endereco=null, cartao=null)",
                ClienteSistemaEntityBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ClienteSistemaEntity clienteSistemaEntity = new ClienteSistemaEntity(
                1L,
                "cus_000005113026",
                LocalDate.of(2023, 2, 3).toString(),
                LocalTime.of(10, 40).toString(),
                "2023-02-03",
                "fulano@gmail.com",
                "fulano",
                "123",
                "12345678910",
                0.00,
                null,
                null,
                null,
                null,
                new ArrayList<>(),
                new ArrayList<>()
        );
        Assertions.assertEquals(
                "ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, " +
                        "horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=fulano, senha=123, " +
                        "cpf=12345678910, saldo=0.0, plano=null, telefone=null, endereco=null, cartao=null)",
                clienteSistemaEntity.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ClienteSistemaEntity clienteSistemaEntity = ClienteSistemaEntity.builder()
                .id(1L)
                .codigoClienteAsaas("cus_000005113026")
                .dataCadastro(LocalDate.of(2023, 2, 3).toString())
                .horaCadastro(LocalTime.of(10, 40).toString())
                .dataNascimento("2023-02-03")
                .email("fulano@gmail.com")
                .nome("fulano")
                .senha("123")
                .cpf("12345678910")
                .saldo(0.00)
                .plano(null)
                .telefone(null)
                .endereco(null)
                .cartao(null)
                .pagamentos(new ArrayList<>())
                .empresas(new ArrayList<>())
                .build();

        Assertions.assertEquals(
                "ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, " +
                        "horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=fulano, senha=123, " +
                        "cpf=12345678910, saldo=0.0, plano=null, telefone=null, endereco=null, cartao=null)",
                clienteSistemaEntity.toString()
        );
    }

}
