package br.akd.svc.akadia.modules.erp.precos.models.dto.request;

import br.akd.svc.akadia.modules.erp.precos.models.enums.TipoPrecoEnum;
import lombok.*;

import javax.validation.constraints.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PrecoRequest {
    @NotNull(message = "O campo 'valor' não pode ser vazio")
    @Min(message = "O campo 'valor' não pode ser menor do que R$ {value},00", value = 0)
    @Max(message = "O campo 'valor' não pode ser maior do que R$ {value},00", value = 999999999)
    private Double valor;

    @Size(message = "O campo 'observação' deve possuir no máximo {max} caracteres", max = 30)
    private String observacao;

    @NotNull(message = "O tipo do preço não pode ser vazio")
    private TipoPrecoEnum tipoPreco;
}
