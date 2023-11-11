package br.akd.svc.akadia.models.dto.site;

import br.akd.svc.akadia.models.dto.site.mocks.ClienteSistemaDtoBuilder;
import br.akd.svc.akadia.modules.web.models.dto.ClienteSistemaDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("DTO: ClienteSistema")
class ClienteSistemaDtoTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ClienteSistemaDto(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, " +
                        "horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=Fulano, " +
                        "senha=123, cpf=12345678910, saldo=0.0, plano=null, telefone=null, endereco=null, " +
                        "cartao=null, pagamentos=[], empresas=[])",
                ClienteSistemaDtoBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ClienteSistemaDto clienteSistemaDto = new ClienteSistemaDto(
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
                "ClienteSistemaDto(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, " +
                        "horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=fulano, " +
                        "senha=123, cpf=12345678910, saldo=0.0, plano=null, telefone=null, endereco=null, cartao=null, " +
                        "pagamentos=[], empresas=[])",
                clienteSistemaDto.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ClienteSistemaDto clienteSistemaDto = ClienteSistemaDto.builder()
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
                "ClienteSistemaDto(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, " +
                        "horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=fulano, " +
                        "senha=123, cpf=12345678910, saldo=0.0, plano=null, telefone=null, endereco=null, cartao=null, " +
                        "pagamentos=[], empresas=[])",
                clienteSistemaDto.toString()
        );
    }
}
