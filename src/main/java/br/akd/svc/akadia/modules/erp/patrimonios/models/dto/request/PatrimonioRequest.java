package br.akd.svc.akadia.modules.erp.patrimonios.models.dto.request;

import br.akd.svc.akadia.modules.erp.patrimonios.models.enums.TipoPatrimonioEnum;
import lombok.*;

import javax.validation.constraints.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PatrimonioRequest {

    @NotNull(message = "O campo 'Data de aquisição' não pode ser vazio")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "O campo 'Data de aquisição' é inválido")
    private String dataEntrada;

    @NotNull(message = "O campo 'descrição' não pode ser vazio")
    @Size(message = "O campo 'descrição' deve possuir no máximo {max} caracteres", max = 50)
    private String descricao;

    @NotNull(message = "O campo 'valor' não pode ser vazio")
    @Min(message = "O campo 'valor' não pode ser menor do que R$ {value},00", value = 1)
    @Max(message = "O campo 'valor' não pode ser maior do que R$ {value},00", value = 999999999)
    private Double valor;

    @NotNull(message = "O campo 'tipo patrimônio' não pode ser vazio")
    private TipoPatrimonioEnum tipoPatrimonio;

}
