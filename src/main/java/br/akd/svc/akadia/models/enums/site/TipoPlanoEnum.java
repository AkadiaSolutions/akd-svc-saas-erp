package br.akd.svc.akadia.models.enums.site;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPlanoEnum {

    BASIC (0, "Basic", 250.0, 1),
    STANDART (1, "Standart", 400.0, 3),
    PRO (2, "Pro", 650.00, 5);

    private final int code;
    private final String desc;
    private final Double valor;
    private final int qtdLimiteEmpresasCadastradas;

}
