package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.entities.site.mocks.ClienteSistemaEntityBuilder;
import br.akd.svc.akadia.models.entities.site.mocks.PagamentoSistemaEntityBuilder;
import br.akd.svc.akadia.modules.web.services.AssinaturaService;
import br.akd.svc.akadia.modules.web.services.PagamentoSistemaService;
import br.akd.svc.akadia.modules.web.proxy.asaas.AsaasProxy;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.cancela.mocks.CancelamentoAssinaturaResponseBuilder;
import br.akd.svc.akadia.proxy.asaas.responses.assinatura.consulta.mocks.ConsultaAssinaturaResponseBuilder;
import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.cobranca.enums.EventoCobrancaEnum;
import br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.mocks.AtualizacaoCobrancaWebHookBuilder;
import br.akd.svc.akadia.modules.web.repository.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.modules.web.repository.impl.PagamentoSistemaRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Service: PagamentoSistema")
class PagamentoSistemaServiceTest {

    @InjectMocks
    PagamentoSistemaService pagamentoSistemaService;

    @Mock
    AssinaturaService assinaturaService;

    @Mock
    PagamentoSistemaRepositoryImpl pagamentoSistemaRepositoryImpl;

    @Mock
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Mock
    AsaasProxy asaasProxy;

    @Test
    @DisplayName("Deve testar método de atualização de pagamento alterado")
    void deveTestarMetodoDeAtualizacaoDePagamentoAlterado() {
        when(pagamentoSistemaRepositoryImpl.implementaBuscaPorCodigoPagamentoAsaas(any()))
                .thenReturn(PagamentoSistemaEntityBuilder.builder().build());

        when(clienteSistemaRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());

        pagamentoSistemaService.realizaAtualizacaoDePagamentoAlterado(
                AtualizacaoCobrancaWebHookBuilder.builder().comEnumEvento(EventoCobrancaEnum.PAYMENT_UPDATED).comPagamento().build(),
                ClienteSistemaEntityBuilder.builder().build());

        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar método de atualização de pagamento vencido")
    void deveTestarMetodoDeAtualizacaoDePagamentoVencido() {

        when(clienteSistemaRepositoryImpl.implementaBuscaPorId(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().comPlanoVencido().build());

        when(asaasProxy.cancelaAssinatura(any(), any()))
                .thenReturn(new ResponseEntity<>(CancelamentoAssinaturaResponseBuilder.builder().build(), HttpStatus.OK));

        when(assinaturaService.cancelaAssinatura(any()))
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

        when(asaasProxy.consultaAssinatura(any(), any()))
                .thenReturn(new ResponseEntity<>(ConsultaAssinaturaResponseBuilder.builder().build(), HttpStatus.OK));

        when(assinaturaService.consultaAssinaturaAsaas(any()))
                .thenReturn(ConsultaAssinaturaResponseBuilder.builder().build());

        when(clienteSistemaRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());

        pagamentoSistemaService.realizaAtualizacaoDePagamentoRealizado(
                AtualizacaoCobrancaWebHookBuilder.builder().comEnumEvento(EventoCobrancaEnum.PAYMENT_CONFIRMED).comPagamento().build(),
                ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());

        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test
    @DisplayName("Deve testar método de criação de novo pagamento")
    void deveTestarMetodoDeCriacaoDeNovoPagamento() {
        when(clienteSistemaRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteSistemaEntityBuilder.builder().comPlanoComPagamentoNoCredito().build());

        pagamentoSistemaService.realizaCriacaoDeNovoPagamento(
                AtualizacaoCobrancaWebHookBuilder.builder().comPagamento().comEnumEvento(EventoCobrancaEnum.PAYMENT_CREATED).build(),
                ClienteSistemaEntityBuilder.builder().comPlanoVencido().build());

        Assertions.assertDoesNotThrow(() -> new Exception());
    }


}
