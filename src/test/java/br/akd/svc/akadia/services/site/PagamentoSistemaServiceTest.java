package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.entities.site.mocks.ClienteSistemaEntityBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.PagamentoSistemaEntityBuilder;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.consulta.mocks.ConsultaAssinaturaResponseBuilder;
import br.akd.svc.akadia.proxy.asaas.webhooks.enums.EventEnum;
import br.akd.svc.akadia.proxy.asaas.webhooks.mocks.AtualizacaoCobrancaWebHookBuilder;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.repositories.site.impl.PagamentoSistemaRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Service: PagamentoSistema")
class PagamentoSistemaServiceTest {

    @InjectMocks
    PagamentoSistemaService pagamentoSistemaService;

    @Mock
    PagamentoSistemaRepositoryImpl pagamentoSistemaRepositoryImpl;

    @Mock
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Mock
    ClienteSistemaService clienteSistemaService;

    @Test
    @DisplayName("Deve testar método de atualização de pagamento alterado")
    void deveTestarMetodoDeAtualizacaoDePagamentoAlterado() {
        when(pagamentoSistemaRepositoryImpl.implementaBuscaPorCodigoPagamentoAsaas(any()))
                .thenReturn(PagamentoSistemaEntityBuilder.builder().build());

        when(clienteSistemaRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());

        pagamentoSistemaService.realizaAtualizacaoDePagamentoAlterado(
                AtualizacaoCobrancaWebHookBuilder.builder().comEnumEvento(EventEnum.PAYMENT_UPDATED).comPagamento().build(),
                ClienteSistemaEntityBuilder.builder().build());

        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar método de atualização de pagamento vencido")
    void deveTestarMetodoDeAtualizacaoDePagamentoVencido() {

        when(clienteSistemaService.cancelaAssinatura(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().build());

        when(clienteSistemaRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());

        pagamentoSistemaService.realizaAtualizacaoDePlanoParaPagamentoVencido(
                ClienteSistemaEntityBuilder.builder().comPlanoVencido().build());

        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar método de atualização de pagamento aprovado")
    void deveTestarMetodoDeAtualizacaoDePagamentoAprovado() {
        when(pagamentoSistemaRepositoryImpl.implementaBuscaPorCodigoPagamentoAsaas(any()))
                .thenReturn(PagamentoSistemaEntityBuilder.builder().build());

        when(clienteSistemaService.consultaAssinaturaAsaas(any()))
                .thenReturn(ConsultaAssinaturaResponseBuilder.builder().build());

        when(clienteSistemaRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());

        pagamentoSistemaService.realizaAtualizacaoDePagamentoRealizado(
                AtualizacaoCobrancaWebHookBuilder.builder().comEnumEvento(EventEnum.PAYMENT_CONFIRMED).comPagamento().build(),
                ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());

        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar método de criação de novo pagamento")
    void deveTestarMetodoDeCriacaoDeNovoPagamento() {
        when(clienteSistemaRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());

        pagamentoSistemaService.realizaCriacaoDeNovoPagamento(
                AtualizacaoCobrancaWebHookBuilder.builder().comPagamento().comEnumEvento(EventEnum.PAYMENT_CREATED).build(),
                ClienteSistemaEntityBuilder.builder().comPlanoVencido().build());

        Assertions.assertDoesNotThrow(() -> new Exception());
    }


}
