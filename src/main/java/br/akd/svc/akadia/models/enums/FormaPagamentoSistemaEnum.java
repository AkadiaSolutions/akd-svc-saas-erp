package br.akd.svc.akadia.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormaPagamentoSistemaEnum {

    BOLETO (0, "Boleto"),
    CARTAO_CREDITO (1, "Cartão de crédito"),
    PIX (2, "Pix");

    private final int code;
    private final String desc;

}
