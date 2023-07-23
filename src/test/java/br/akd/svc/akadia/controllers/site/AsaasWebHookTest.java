package br.akd.svc.akadia.controllers.site;

import br.akd.svc.akadia.proxy.asaas.webhooks.cobranca.mocks.AtualizacaoCobrancaWebHookBuilder;
import br.akd.svc.akadia.proxy.asaas.webhooks.fiscal.mocks.AtualizacaoFiscalWebHookBuilder;
import br.akd.svc.akadia.services.site.PagamentoSistemaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Controller: AsaasWebHook")
class AsaasWebHookTest {

    @InjectMocks
    AsaasWebhook asaasWebhook;

    @Mock
    PagamentoSistemaService pagamentoSistemaService;

    @Test
    @DisplayName("Deve testar método controlador de recebimento de status de pagamento")
    void deveTestarMetodoControladorDeRecebimentoDeStatusDePagamento() {
        Assertions.assertEquals("<200 OK OK,[]>",
                asaasWebhook.recebeStatusPagamento(AtualizacaoCobrancaWebHookBuilder.builder().build(),
                        System.getenv("TOKEN_WEBHOOK_ASAAS")).toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de recebimento de status fiscal")
    void deveTestarMetodoControladorDeRecebimentoDeStatusFiscal() {
        Assertions.assertEquals("<200 OK OK,[]>",
                asaasWebhook.recebeStatusFiscal(AtualizacaoFiscalWebHookBuilder.builder().build(),
                        System.getenv("TOKEN_WEBHOOK_ASAAS")).toString());
    }

}
