package br.akd.svc.akadia.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BandeiraCartaoEnum {

    MASTERCARD (0, "Mastercard"),
    VISA (1, "Visa"),
    ELO (2, "Elo"),
    AMERICAN_EXPRESS (3, "American Express"),
    HIPERCARD (4, "Hipercard");

    private final int code;
    private final String desc;
}
