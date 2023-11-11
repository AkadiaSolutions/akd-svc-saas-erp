package br.akd.svc.akadia.modules.web.proxy.asaas.webhooks.fiscal;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceWebHook {
    private String invoice;
    private String id;
    private String status;
    private String customer;
    private String type;
    private String statusDescription;
    private String serviceDescription;
    private String pdfUrl;
    private String xmlUrl;
    private String rpsSerie;
    private String rpsNumber;
    private String number;
    private String validationCode;
    private Double value;
    private Integer deductions;
    private String effectiveDate;
    private String observations;
    private String estimatedTaxesDescription;
    private String payment;
    private String installment;
    private TaxesWebHook taxes;
    private String municipalServiceCode;
    private String municipalServiceName;
}
