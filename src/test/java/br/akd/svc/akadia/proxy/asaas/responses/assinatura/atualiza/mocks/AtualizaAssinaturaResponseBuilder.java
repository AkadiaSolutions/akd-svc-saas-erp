package br.akd.svc.akadia.proxy.asaas.responses.assinatura.atualiza.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.atualiza.AtualizaAssinaturaResponse;

import java.time.LocalDate;

public class AtualizaAssinaturaResponseBuilder {

    AtualizaAssinaturaResponseBuilder() {
    }

    AtualizaAssinaturaResponse atualizaAssinaturaResponse;

    public static AtualizaAssinaturaResponseBuilder builder() {
        AtualizaAssinaturaResponseBuilder builder = new AtualizaAssinaturaResponseBuilder();
        builder.atualizaAssinaturaResponse = new AtualizaAssinaturaResponse();
        builder.atualizaAssinaturaResponse.setId("sub_i8U7a1UzweKt");
        builder.atualizaAssinaturaResponse.setDateCreated(LocalDate.of(2023, 2, 8).toString());
        builder.atualizaAssinaturaResponse.setCustomer("cus_000005121369");
        builder.atualizaAssinaturaResponse.setPaymentLink(null);
        builder.atualizaAssinaturaResponse.setBillingType("PIX");
        builder.atualizaAssinaturaResponse.setValue(650.0);
        builder.atualizaAssinaturaResponse.setNextDueDate(LocalDate.of(2023, 2, 8).toString());
        builder.atualizaAssinaturaResponse.setDiscount(null);
        builder.atualizaAssinaturaResponse.setInterest(null);
        builder.atualizaAssinaturaResponse.setFine(null);
        builder.atualizaAssinaturaResponse.setCycle("MONTHLY");
        builder.atualizaAssinaturaResponse.setDescription("Assinatura de plano pro");
        builder.atualizaAssinaturaResponse.setEndDate(null);
        builder.atualizaAssinaturaResponse.setMaxPayments(null);
        builder.atualizaAssinaturaResponse.setStatus("ACTIVE");
        builder.atualizaAssinaturaResponse.setExternalReference(null);
        builder.atualizaAssinaturaResponse.setSplit(null);
        return builder;
    }

    public AtualizaAssinaturaResponse build() {
        return atualizaAssinaturaResponse;
    }

}