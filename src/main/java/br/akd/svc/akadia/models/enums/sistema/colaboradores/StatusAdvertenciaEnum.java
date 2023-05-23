package br.akd.svc.akadia.models.enums.sistema.colaboradores;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusAdvertenciaEnum {
    PENDENTE(0, "Pendente de assinatura"),
    ASSINADA(1, "Assinada");
    private final int code;
    private final String desc;
}
