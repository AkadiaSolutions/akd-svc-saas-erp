package br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ConfiguracaoPerfilEntity;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TemaTelaEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConfiguracaoPerfilEntityBuilder {

    ConfiguracaoPerfilEntityBuilder() {
    }

    ConfiguracaoPerfilEntity configuracaoPerfilEntity;

    public static ConfiguracaoPerfilEntityBuilder builder() {
        ConfiguracaoPerfilEntityBuilder builder = new ConfiguracaoPerfilEntityBuilder();
        builder.configuracaoPerfilEntity = new ConfiguracaoPerfilEntity();
        builder.configuracaoPerfilEntity.setId(1L);
        builder.configuracaoPerfilEntity.setDataUltimaAtualizacao(LocalDate.of(2023, 2, 13).toString());
        builder.configuracaoPerfilEntity.setHoraUltimaAtualizacao(LocalTime.of(10, 44).toString());
        builder.configuracaoPerfilEntity.setTemaTelaEnum(TemaTelaEnum.TELA_ESCURA);
        return builder;
    }

    public ConfiguracaoPerfilEntity build() {
        return configuracaoPerfilEntity;
    }

}
