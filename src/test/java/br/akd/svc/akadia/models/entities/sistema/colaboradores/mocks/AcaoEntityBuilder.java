package br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcaoEntity;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModulosEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TipoAcaoEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class AcaoEntityBuilder {
    AcaoEntityBuilder() {
    }

    AcaoEntity acaoEntity;

    public static AcaoEntityBuilder builder() {
        AcaoEntityBuilder builder = new AcaoEntityBuilder();
        builder.acaoEntity = new AcaoEntity();
        builder.acaoEntity.setId(1L);
        builder.acaoEntity.setIdObjeto(1L);
        builder.acaoEntity.setDataCriacao(LocalDate.of(2023, 2, 13).toString());
        builder.acaoEntity.setHoraCriacao(LocalTime.of(10, 44).toString());
        builder.acaoEntity.setModuloEnum(ModulosEnum.COLABORADORES);
        builder.acaoEntity.setTipoAcaoEnum(TipoAcaoEnum.CRIACAO);
        builder.acaoEntity.setObservacao("observação");
        return builder;
    }

    public AcaoEntity build() {
        return acaoEntity;
    }
}
