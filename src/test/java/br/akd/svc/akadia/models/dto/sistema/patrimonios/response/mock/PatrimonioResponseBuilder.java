package br.akd.svc.akadia.models.dto.sistema.patrimonios.response.mock;

import br.akd.svc.akadia.modules.erp.patrimonios.models.dto.response.PatrimonioResponse;
import br.akd.svc.akadia.modules.erp.patrimonios.models.enums.TipoPatrimonioEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class PatrimonioResponseBuilder {
    PatrimonioResponseBuilder() {
    }

    ;

    PatrimonioResponse patrimonioResponse;

    public static PatrimonioResponseBuilder builder() {
        PatrimonioResponseBuilder builder = new PatrimonioResponseBuilder();
        builder.patrimonioResponse = new PatrimonioResponse();
        builder.patrimonioResponse.setId(1L);
        builder.patrimonioResponse.setDataCadastro(LocalDate.of(2023, 8, 21).toString());
        builder.patrimonioResponse.setHoraCadastro(LocalTime.of(10, 20).toString());
        builder.patrimonioResponse.setDataEntrada(LocalDate.of(2023, 8, 21).toString());
        builder.patrimonioResponse.setDescricao("Dinheiro");
        builder.patrimonioResponse.setValor(100.0);
        builder.patrimonioResponse.setTipoPatrimonio(TipoPatrimonioEnum.ATIVO.getDesc());
        return builder;
    }

    public PatrimonioResponse build() {
        return this.patrimonioResponse;
    }
}
