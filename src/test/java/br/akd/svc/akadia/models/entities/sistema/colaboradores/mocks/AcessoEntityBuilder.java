package br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks;

import br.akd.svc.akadia.modules.erp.colaboradores.acesso.models.entity.AcessoEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public class AcessoEntityBuilder {
    AcessoEntityBuilder() {
    }

    AcessoEntity acessoEntity;

    public static AcessoEntityBuilder builder() {
        AcessoEntityBuilder builder = new AcessoEntityBuilder();
        builder.acessoEntity = new AcessoEntity();
        builder.acessoEntity.setId(1L);
        builder.acessoEntity.setDataCadastro(LocalDate.of(2023, 2, 13).toString());
        builder.acessoEntity.setHoraCadastro(LocalTime.of(10, 44).toString());
        return builder;
    }

    public AcessoEntity build() {
        return acessoEntity;
    }
}
