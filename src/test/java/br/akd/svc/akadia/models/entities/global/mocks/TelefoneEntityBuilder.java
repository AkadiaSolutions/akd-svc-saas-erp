package br.akd.svc.akadia.models.entities.global.mocks;

import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.enums.global.TipoTelefoneEnum;

public class TelefoneEntityBuilder {

    TelefoneEntityBuilder(){}
    TelefoneEntity telefoneEntity;

    public static TelefoneEntityBuilder builder() {
        TelefoneEntityBuilder builder = new TelefoneEntityBuilder();
        builder.telefoneEntity = new TelefoneEntity();
        builder.telefoneEntity.setId(1L);
        builder.telefoneEntity.setPrefixo("11");
        builder.telefoneEntity.setNumero("979815415");
        builder.telefoneEntity.setTipoTelefone(TipoTelefoneEnum.MOVEL_WHATSAPP);
        return builder;
    }

    public TelefoneEntity build() {
        return telefoneEntity;
    }
}
