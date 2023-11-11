package br.akd.svc.akadia.modules.web.proxy.asaas.responses.fiscal;

import br.akd.svc.akadia.modules.web.proxy.asaas.global.Taxes;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CriaConfigFiscalResponse {
    private String municipalServiceId;
    private String municipalServiceCode;
    private String municipalServiceName;
    private Integer deductions;
    private String invoiceCreationPeriod;
    private Integer daysBeforeDueDate;
    private Boolean receivedOnly;
    private String observations;
    private Taxes taxes;
}
