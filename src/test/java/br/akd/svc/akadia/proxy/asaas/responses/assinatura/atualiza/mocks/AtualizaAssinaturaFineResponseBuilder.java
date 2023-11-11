package br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.atualiza.AtualizaAssinaturaFineResponse;

public class AtualizaAssinaturaFineResponseBuilder {

    AtualizaAssinaturaFineResponseBuilder() {
    }

    AtualizaAssinaturaFineResponse atualizaAssinaturaFineResponse;

    public static AtualizaAssinaturaFineResponseBuilder builder() {
        AtualizaAssinaturaFineResponseBuilder builder = new AtualizaAssinaturaFineResponseBuilder();
        builder.atualizaAssinaturaFineResponse = new AtualizaAssinaturaFineResponse();
        builder.atualizaAssinaturaFineResponse.setValue(0.0);
        return builder;
    }

    public AtualizaAssinaturaFineResponse build() {
        return atualizaAssinaturaFineResponse;
    }

}
