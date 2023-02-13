package br.akd.svc.akadia.models.dto.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.DispensaDto;

import java.time.LocalDate;
import java.util.ArrayList;

public class DispensaDtoBuilder {

    DispensaDtoBuilder() {
    }

    DispensaDto dispensaDto;

    public static DispensaDtoBuilder builder() {
        DispensaDtoBuilder builder = new DispensaDtoBuilder();
        builder.dispensaDto = new DispensaDto();
        builder.dispensaDto.setId(1L);
        builder.dispensaDto.setDataDispensa(LocalDate.of(2023, 2, 13).toString());
        builder.dispensaDto.setMotivo("Corte de custos");
        builder.dispensaDto.setListaNegra(false);
        builder.dispensaDto.setAnexos(new ArrayList<>());
        builder.dispensaDto.setContratoDispensa(new byte[]{});
        return builder;
    }

    public DispensaDto build() {
        return dispensaDto;
    }

}
