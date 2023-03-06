package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModulosEnum {

    HOME(0, "Tela principal", "PRINCIPAL"),
    CLIENTES(1, "Clientes", "CLIENTES"),
    VENDAS(2, "Vendas", "VENDAS"),
    LANCAMENTOS(3, "Lançamentos", "LANCAMENTOS"),
    ESTOQUE(4, "Estoque", "ESTOQUE"),
    DESPESAS(5, "Despesas", "DESPESAS"),
    FECHAMENTOS(6, "Fechamentos", "FECHAMENTOS"),
    PATRIMONIOS(7, "Patrimônios", "PATRIMONIOS"),
    FORNECEDORES(8, "Fornecedores", "FORNECEDORES"),
    COMPRAS(9, "Compras", "COMPRAS"),
    COLABORADORES(10, "Colaboradores", "COLABORADORES"),
    PRECOS(11, "Preços", "PRECOS");

    private final Integer code;
    private final String desc;

    public final String role;

}
