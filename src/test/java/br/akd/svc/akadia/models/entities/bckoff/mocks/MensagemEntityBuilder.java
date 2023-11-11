package br.akd.svc.akadia.models.entities.bckoff.mocks;

import br.akd.svc.akadia.modules.backoffice.models.entity.MensagemEntity;
import br.akd.svc.akadia.modules.backoffice.models.enums.CaminhoMensagemEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MensagemEntityBuilder {

    MensagemEntityBuilder(){}
    MensagemEntity mensagemEntity;

    public static MensagemEntityBuilder builder() {
        MensagemEntityBuilder builder = new MensagemEntityBuilder();
        builder.mensagemEntity = new MensagemEntity();
        builder.mensagemEntity.setId(1L);
        builder.mensagemEntity.setDataEnvio(LocalDate.of(2023, 2, 2).toString());
        builder.mensagemEntity.setHoraEnvio(LocalTime.of(23,24).toString());
        builder.mensagemEntity.setVisualizada(true);
        builder.mensagemEntity.setRespondida(true);
        builder.mensagemEntity.setConteudo("Preciso de suporte com o meu login");
        builder.mensagemEntity.setCaminhoMensagemEnum(CaminhoMensagemEnum.CLIENTE_PARA_SUPORTE);
        builder.mensagemEntity.setAnexos(new ArrayList<>());
        return builder;
    }

    public MensagemEntityBuilder comAnexo() {
        this.mensagemEntity.getAnexos().add(AnexoMensagemEntityBuilder.builder().build());
        return this;
    }

    public MensagemEntity build() {
        return mensagemEntity;
    }

}
