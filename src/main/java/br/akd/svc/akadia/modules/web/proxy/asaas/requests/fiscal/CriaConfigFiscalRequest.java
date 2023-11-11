package br.akd.svc.akadia.modules.web.proxy.asaas.requests.fiscal;

import br.akd.svc.akadia.modules.web.proxy.asaas.global.Taxes;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CriaConfigFiscalRequest {
    private String municipalServiceId;
    private String municipalServiceCode;
    private String municipalServiceName;
    private String updatePayment;
    private Integer deductions;
    private EffectiveDatePeriodEnum effectiveDatePeriod;
    private Boolean receivedOnly;
    private Integer daysBeforeDueDate;
    private String observations;
    private Taxes taxes;
}
