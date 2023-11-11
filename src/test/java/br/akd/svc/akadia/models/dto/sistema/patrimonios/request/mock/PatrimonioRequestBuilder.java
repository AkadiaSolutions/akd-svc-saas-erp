package br.akd.svc.akadia.models.dto.sistema.patrimonios.request.mock;

import br.akd.svc.akadia.modules.erp.patrimonios.models.dto.request.PatrimonioRequest;
import br.akd.svc.akadia.modules.erp.patrimonios.models.enums.TipoPatrimonioEnum;

import java.time.LocalDate;

public class PatrimonioRequestBuilder {
    PatrimonioRequestBuilder() {
    }

    PatrimonioRequest patrimonioRequest;

    public static PatrimonioRequestBuilder builder() {
        PatrimonioRequestBuilder builder = new PatrimonioRequestBuilder();
        builder.patrimonioRequest = new PatrimonioRequest();
        builder.patrimonioRequest.setDataEntrada(LocalDate.of(2023, 8, 21).toString());
        builder.patrimonioRequest.setDescricao("Dinheiro");
        builder.patrimonioRequest.setValor(100.0);
        builder.patrimonioRequest.setTipoPatrimonio(TipoPatrimonioEnum.ATIVO);
        return builder;
    }

    public PatrimonioRequest build() {
        return this.patrimonioRequest;
    }
}
