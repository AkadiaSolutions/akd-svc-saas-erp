package br.akd.svc.akadia.models.entities.site;

import br.akd.svc.akadia.models.entities.global.mocks.EnderecoEntityBuilder;
import br.akd.svc.akadia.models.entities.global.mocks.TelefoneEntityBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.CartaoEntityBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.ClienteSistemaEntityBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.PlanoEntityBuilder;
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
                        "horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=Fulano, senha=123, " +
                        "cpf=12345678910, saldo=0.0, plano=PlanoEntity(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, " +
                        "dataContratacao=2023-02-03, horaContratacao=09:58, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, " +
                        "formaPagamentoSistemaEnum=BOLETO), telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, " +
                        "tipoTelefoneEnum=MOVEL_WHATSAPP), endereco=EnderecoEntity(id=1, logradouro=Avenida Coronel " +
                        "Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, " +
                        "estadoEnum=SP), cartao=CartaoEntity(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, " +
                        "numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, " +
                        "ativo=true, bandeiraCartaoEnum=VISA))",
                ClienteSistemaEntityBuilder.builder().comEmpresa().comPagamento().build().toString()
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
                PlanoEntityBuilder.builder().build(),
                TelefoneEntityBuilder.builder().build(),
                EnderecoEntityBuilder.builder().build(),
                CartaoEntityBuilder.builder().build(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        Assertions.assertEquals(
                "ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, " +
                        "horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=fulano, " +
                        "senha=123, cpf=12345678910, saldo=0.0, plano=PlanoEntity(id=1, " +
                        "codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, " +
                        "tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=BOLETO), " +
                        "telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), " +
                        "endereco=EnderecoEntity(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, " +
                        "bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), " +
                        "cartao=CartaoEntity(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, " +
                        "ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA))",
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
                .plano(PlanoEntityBuilder.builder().build())
                .telefone(TelefoneEntityBuilder.builder().build())
                .endereco(EnderecoEntityBuilder.builder().build())
                .cartao(CartaoEntityBuilder.builder().build())
                .pagamentos(new ArrayList<>())
                .empresas(new ArrayList<>())
                .build();

        Assertions.assertEquals(
                "ClienteSistemaEntity(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, " +
                        "horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=fulano, " +
                        "senha=123, cpf=12345678910, saldo=0.0, plano=PlanoEntity(id=1, " +
                        "codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, " +
                        "tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=BOLETO), " +
                        "telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), " +
                        "endereco=EnderecoEntity(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, " +
                        "bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), " +
                        "cartao=CartaoEntity(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, " +
                        "ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA))",
                clienteSistemaEntity.toString()
        );
    }

}
