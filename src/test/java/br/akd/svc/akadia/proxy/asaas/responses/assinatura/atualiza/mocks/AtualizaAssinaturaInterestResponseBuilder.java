package br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.atualiza.AtualizaAssinaturaInterestResponse;

public class AtualizaAssinaturaInterestResponseBuilder {

    AtualizaAssinaturaInterestResponseBuilder() {
    }

    AtualizaAssinaturaInterestResponse atualizaAssinaturaInterestResponse;

    public static AtualizaAssinaturaInterestResponseBuilder builder() {
        AtualizaAssinaturaInterestResponseBuilder builder = new AtualizaAssinaturaInterestResponseBuilder();
        builder.atualizaAssinaturaInterestResponse = new AtualizaAssinaturaInterestResponse();
        builder.atualizaAssinaturaInterestResponse.setInterest(0.0);
        return builder;
    }

    public AtualizaAssinaturaInterestResponse build() {
        return atualizaAssinaturaInterestResponse;
    }

}
