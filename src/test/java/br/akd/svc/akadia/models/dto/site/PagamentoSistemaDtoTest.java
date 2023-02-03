package br.akd.svc.akadia.models.dto.site;

import br.akd.svc.akadia.models.dto.site.mocks.ClienteSistemaDtoBuilder;
import br.akd.svc.akadia.models.dto.site.mocks.PagamentoSistemaDtoBuilder;
import br.akd.svc.akadia.models.enums.site.FormaPagamentoSistemaEnum;
import br.akd.svc.akadia.models.enums.site.StatusPagamentoSistemaEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@DisplayName("DTO: PagamentoSistema")
class PagamentoSistemaDtoTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "PagamentoSistemaDto(id=1, dataCadastro=2023-02-03, horaCadastro=10:57, codigoTransacao=129371283971, valor=650.0, vencimento=2023-02-03, formaPagamentoSistemaEnum=CREDIT_CARD, statusPagamentoSistemaEnum=APROVADO, cartao=CartaoDto(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA), clienteSistema=ClienteSistemaDto(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=PlanoDto(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=BOLETO), telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), endereco=EnderecoDto(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), cartao=CartaoDto(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA), pagamentos=[], empresas=[]))",
                PagamentoSistemaDtoBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        PagamentoSistemaDto pagamentoSistemaDto = new PagamentoSistemaDto(
                1L,
                LocalDate.of(2023, 2, 3).toString(),
                LocalTime.of(15, 50).toString(),
                12345678910L,
                650.0,
                LocalDate.of(2023, 2, 5).toString(),
                FormaPagamentoSistemaEnum.PIX,
                StatusPagamentoSistemaEnum.REPROVADO,
                null,
                ClienteSistemaDtoBuilder.builder().build()
        );

        Assertions.assertEquals(
                "PagamentoSistemaDto(id=1, dataCadastro=2023-02-03, horaCadastro=15:50, codigoTransacao=12345678910, valor=650.0, vencimento=2023-02-05, formaPagamentoSistemaEnum=PIX, statusPagamentoSistemaEnum=REPROVADO, cartao=null, clienteSistema=ClienteSistemaDto(id=1, codigoClienteAsaas=cus_000005113026, dataCadastro=2023-02-03, horaCadastro=10:40, dataNascimento=2023-02-03, email=fulano@gmail.com, nome=Fulano, senha=123, cpf=12345678910, saldo=0.0, plano=PlanoDto(id=1, codigoAssinaturaAsaas=sub_jaIvjZ8TMlXZ, dataContratacao=2023-02-03, horaContratacao=09:58, tipoPlanoEnum=BASIC, statusPlanoEnum=ATIVO, formaPagamentoSistemaEnum=BOLETO), telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), endereco=EnderecoDto(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, estadoEnum=SP), cartao=CartaoDto(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA), pagamentos=[], empresas=[]))",
                pagamentoSistemaDto.toString()
        );

    }
}
