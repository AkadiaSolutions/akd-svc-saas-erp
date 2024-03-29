package br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.entities.global.mocks.ArquivoEntityBuilder;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.entity.AdvertenciaEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.enums.StatusAdvertenciaEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class AdvertenciaEntityBuilder {

    AdvertenciaEntityBuilder() {
    }

    AdvertenciaEntity advertenciaEntity;

    public static AdvertenciaEntityBuilder builder() {
        AdvertenciaEntityBuilder builder = new AdvertenciaEntityBuilder();
        builder.advertenciaEntity = new AdvertenciaEntity();
        builder.advertenciaEntity.setId(1L);
        builder.advertenciaEntity.setDataCadastro(LocalDate.of(2023, 2, 13).toString());
        builder.advertenciaEntity.setHoraCadastro(LocalTime.of(10, 44).toString());
        builder.advertenciaEntity.setMotivo("Brigou na loja");
        builder.advertenciaEntity.setDescricao("Cuspiu no cliente");
        builder.advertenciaEntity.setAdvertenciaAssinada(null);
        builder.advertenciaEntity.setStatusAdvertenciaEnum(StatusAdvertenciaEnum.ASSINADA);
        return builder;
    }

    public AdvertenciaEntityBuilder comArquivo() {
        advertenciaEntity.setAdvertenciaAssinada(ArquivoEntityBuilder.builder().build());
        return this;
    }

    public AdvertenciaEntity build() {
        return advertenciaEntity;
    }

}
