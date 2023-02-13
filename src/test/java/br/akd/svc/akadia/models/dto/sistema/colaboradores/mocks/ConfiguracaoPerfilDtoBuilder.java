package br.akd.svc.akadia.models.dto.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.ConfiguracaoPerfilDto;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TemaTelaEnum;

import java.time.LocalDate;
import java.time.LocalTime;

class ConfiguracaoPerfilDtoBuilder {

    ConfiguracaoPerfilDtoBuilder() {
    }

    ConfiguracaoPerfilDto configuracaoPerfilDto;

    public static ConfiguracaoPerfilDtoBuilder builder() {
        ConfiguracaoPerfilDtoBuilder builder = new ConfiguracaoPerfilDtoBuilder();
        builder.configuracaoPerfilDto = new ConfiguracaoPerfilDto();
        builder.configuracaoPerfilDto.setId(1L);
        builder.configuracaoPerfilDto.setDataUltimaAtualizacao(LocalDate.of(2023, 2, 13).toString());
        builder.configuracaoPerfilDto.setHoraUltimaAtualizacao(LocalTime.of(10, 44).toString());
        builder.configuracaoPerfilDto.setTemaTelaEnum(TemaTelaEnum.TELA_ESCURA);
        return builder;
    }

    public ConfiguracaoPerfilDto build() {
        return configuracaoPerfilDto;
    }

}
