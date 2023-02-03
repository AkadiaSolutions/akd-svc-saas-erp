package br.akd.svc.akadia.models.dto.bckoff.mocks;

import br.akd.svc.akadia.models.dto.bckoff.MensagemDto;
import br.akd.svc.akadia.models.enums.bckoff.CaminhoMensagemEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MensagemDtoBuilder {

    MensagemDtoBuilder(){}
    MensagemDto mensagemDto;

    public static MensagemDtoBuilder builder() {
        MensagemDtoBuilder builder = new MensagemDtoBuilder();
        builder.mensagemDto = new MensagemDto();
        builder.mensagemDto.setId(1L);
        builder.mensagemDto.setDataEnvio(LocalDate.of(2023, 2, 2).toString());
        builder.mensagemDto.setHoraEnvio(LocalTime.of(23,24).toString());
        builder.mensagemDto.setVisualizada(true);
        builder.mensagemDto.setRespondida(true);
        builder.mensagemDto.setConteudo("Preciso de suporte com o meu login");
        builder.mensagemDto.setCaminhoMensagemEnum(CaminhoMensagemEnum.CLIENTE_PARA_SUPORTE);
        builder.mensagemDto.setAnexos(new ArrayList<>());
        return builder;
    }

    public MensagemDtoBuilder comAnexo() {
        this.mensagemDto.getAnexos().add(AnexoMensagemDtoBuilder.builder().build());
        return this;
    }

    public MensagemDto build() {
        return mensagemDto;
    }

}
