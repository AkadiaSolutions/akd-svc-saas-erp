package br.akd.svc.akadia.proxy.asaas.webhooks.fiscal.mocks;

import br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.fiscal.InvoiceWebHook;

public class InvoiceWebHookBuilder {
    InvoiceWebHookBuilder() {
    }

    InvoiceWebHook invoiceWebHook;

    public static InvoiceWebHookBuilder builder() {
        InvoiceWebHookBuilder builder = new InvoiceWebHookBuilder();
        builder.invoiceWebHook = new InvoiceWebHook();
        builder.invoiceWebHook.setInvoice(null);
        builder.invoiceWebHook.setId(null);
        builder.invoiceWebHook.setStatus(null);
        builder.invoiceWebHook.setCustomer(null);
        builder.invoiceWebHook.setType(null);
        builder.invoiceWebHook.setStatusDescription(null);
        builder.invoiceWebHook.setServiceDescription(null);
        builder.invoiceWebHook.setPdfUrl(null);
        builder.invoiceWebHook.setXmlUrl(null);
        builder.invoiceWebHook.setRpsSerie(null);
        builder.invoiceWebHook.setRpsNumber(null);
        builder.invoiceWebHook.setNumber(null);
        builder.invoiceWebHook.setValidationCode(null);
        builder.invoiceWebHook.setValue(null);
        builder.invoiceWebHook.setDeductions(null);
        builder.invoiceWebHook.setEffectiveDate(null);
        builder.invoiceWebHook.setObservations(null);
        builder.invoiceWebHook.setEstimatedTaxesDescription(null);
        builder.invoiceWebHook.setPayment(null);
        builder.invoiceWebHook.setInstallment(null);
        builder.invoiceWebHook.setTaxes(null);
        builder.invoiceWebHook.setMunicipalServiceCode(null);
        builder.invoiceWebHook.setMunicipalServiceName(null);
        return builder;
    }

    public InvoiceWebHook build() {
        return invoiceWebHook;
    }
}
