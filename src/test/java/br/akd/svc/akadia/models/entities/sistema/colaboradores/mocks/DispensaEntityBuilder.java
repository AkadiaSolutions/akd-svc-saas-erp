package br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.DispensaEntity;

import java.time.LocalDate;
import java.util.ArrayList;

public class DispensaEntityBuilder {

    DispensaEntityBuilder() {
    }

    DispensaEntity dispensaEntity;

    public static DispensaEntityBuilder builder() {
        DispensaEntityBuilder builder = new DispensaEntityBuilder();
        builder.dispensaEntity = new DispensaEntity();
        builder.dispensaEntity.setId(1L);
        builder.dispensaEntity.setDataDispensa(LocalDate.of(2023, 2, 13).toString());
        builder.dispensaEntity.setMotivo("Corte de custos");
        builder.dispensaEntity.setListaNegra(false);
        builder.dispensaEntity.setAnexos(new ArrayList<>());
        builder.dispensaEntity.setContratoDispensa(new byte[]{});
        return builder;
    }

    public DispensaEntity build() {
        return dispensaEntity;
    }

}
