package br.akd.svc.akadia.modules.web.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormaPagamentoSistemaEnum {

    BOLETO (0, "Boleto"),
    CREDIT_CARD(1, "Cartão de crédito"),
    PIX (2, "Pix");

    private final int code;
    private final String desc;

}
