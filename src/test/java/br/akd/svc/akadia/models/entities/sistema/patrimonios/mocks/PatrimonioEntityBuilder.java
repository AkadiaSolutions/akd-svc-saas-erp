package br.akd.svc.akadia.models.entities.sistema.patrimonios.mocks;

import br.akd.svc.akadia.models.entities.global.mocks.ExclusaoEntityBuilder;
import br.akd.svc.akadia.modules.erp.patrimonios.models.entity.PatrimonioEntity;
import br.akd.svc.akadia.modules.erp.patrimonios.models.enums.TipoPatrimonioEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class PatrimonioEntityBuilder {

    PatrimonioEntityBuilder() {
    }

    ;

    PatrimonioEntity patrimonioEntity;

    public static PatrimonioEntityBuilder builder() {
        PatrimonioEntityBuilder builder = new PatrimonioEntityBuilder();
        builder.patrimonioEntity = new PatrimonioEntity();
        builder.patrimonioEntity.setId(1L);
        builder.patrimonioEntity.setDataCadastro(LocalDate.of(2023, 8, 21).toString());
        builder.patrimonioEntity.setHoraCadastro(LocalTime.of(10, 20).toString());
        builder.patrimonioEntity.setDataEntrada(LocalDate.of(2023, 8, 21).toString());
        builder.patrimonioEntity.setDescricao("Dinheiro");
        builder.patrimonioEntity.setValor(100.0);
        builder.patrimonioEntity.setTipoPatrimonio(TipoPatrimonioEnum.ATIVO);
        builder.patrimonioEntity.setExclusao(null);
        builder.patrimonioEntity.setColaboradorResponsavel(null);
        builder.patrimonioEntity.setEmpresa(null);
        return builder;
    }

    public PatrimonioEntityBuilder comExclusao() {
        patrimonioEntity.setExclusao(ExclusaoEntityBuilder.builder().build());
        return this;
    }

    public PatrimonioEntity build() {
        return this.patrimonioEntity;
    }

}
