package br.akd.svc.akadia.proxy.asaas.responses.assinatura.consulta.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.responses.assinatura.consulta.ConsultaAssinaturaResponse;

import java.time.LocalDate;
import java.util.ArrayList;

public class ConsultaAssinaturaResponseBuilder {

    ConsultaAssinaturaResponseBuilder() {
    }

    ConsultaAssinaturaResponse consultaAssinaturaResponse;

    public static ConsultaAssinaturaResponseBuilder builder() {
        ConsultaAssinaturaResponseBuilder builder = new ConsultaAssinaturaResponseBuilder();
        builder.consultaAssinaturaResponse = new ConsultaAssinaturaResponse();
        builder.consultaAssinaturaResponse.setId("sub_i8U7a1UzweKt");
        builder.consultaAssinaturaResponse.setDateCreated(LocalDate.of(2023, 2, 8).toString());
        builder.consultaAssinaturaResponse.setCustomer("cus_000005121369");
        builder.consultaAssinaturaResponse.setPaymentLink(null);
        builder.consultaAssinaturaResponse.setBillingType("PIX");
        builder.consultaAssinaturaResponse.setValue(650.0);
        builder.consultaAssinaturaResponse.setNextDueDate(LocalDate.of(2023, 2, 8).toString());
        builder.consultaAssinaturaResponse.setCycle("MONTHLY");
        builder.consultaAssinaturaResponse.setDescription("Assinatura de plano pro");
        builder.consultaAssinaturaResponse.setEndDate(null);
        builder.consultaAssinaturaResponse.setMaxPayments(null);
        builder.consultaAssinaturaResponse.setStatus("ACTIVE");
        builder.consultaAssinaturaResponse.setExternalReference(null);
        builder.consultaAssinaturaResponse.setSplit(new ArrayList<>());
        return builder;
    }

    public ConsultaAssinaturaResponse build() {
        return consultaAssinaturaResponse;
    }

}
