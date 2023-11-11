package br.akd.svc.akadia.modules.erp.produtos.models.dto.request;

import br.akd.svc.akadia.modules.erp.precos.models.dto.request.PrecoRequest;
import br.akd.svc.akadia.modules.erp.produtos.models.enums.TipoPesoEnum;
import br.akd.svc.akadia.modules.erp.produtos.models.enums.TipoProdutoEnum;
import br.akd.svc.akadia.modules.erp.produtos.models.enums.UnidadeComercialEnum;
import lombok.*;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {

    @NotBlank(message = "O campo código interno não pode ser vazio")
    @Size(message = "O campo código interno deve possuir no máximo {max} caracteres", max = 10)
    private String codigoInterno;

    @NotBlank(message = "O campo sigla não pode ser vazio")
    @Size(message = "O campo sigla deve possuir no máximo {max} caracteres", max = 8)
    private String sigla;

    @NotBlank(message = "O campo marca não pode ser vazio")
    @Size(message = "O campo marca deve possuir no máximo {max} caracteres", max = 30)
    private String marca;

    @NotBlank(message = "O campo descrição não pode ser vazio")
    @Size(message = "O campo descrição deve possuir no máximo {max} caracteres", max = 30)
    private String descricao;

    @NotBlank(message = "O campo categoria não pode ser vazio")
    @Size(message = "O campo categoria deve possuir no máximo {max} caracteres", max = 30)
    private String categoria;

    @NotNull(message = "O campo 'quantidade mínima' não pode ser vazio")
    @Min(message = "O campo quantidade mínima não pode ser menor do que {value}", value = 0)
    @Max(message = "O campo quantidade mínima não pode ser maior do que {value}", value = 999)
    private Integer quantidadeMinima;

    @NotNull(message = "O campo 'quantidade atacado' não pode ser vazio")
    @Min(message = "O campo quantidade atacado não pode ser menor do que {value}", value = 0)
    @Max(message = "O campo quantidade atacado não pode ser maior do que {value}", value = 999)
    private Integer quantidadeAtacado;

    @NotNull(message = "O campo 'código ncm' não pode ser vazio")
    @Min(message = "O campo código ncm não pode ser menor do que {value}", value = 10000000)
    @Max(message = "O campo código ncm não pode ser maior do que {value}", value = 99999999)
    private Integer codigoNcm;

    @NotNull(message = "O campo 'peso unitário' não pode ser vazio")
    @Min(message = "O campo 'peso unitário' não pode ser menor do que {value}", value = 0)
    @Max(message = "O campo 'peso unitário' não pode ser maior do que {value}kg", value = 999999)
    private Double pesoUnitario;

    @NotNull(message = "O campo 'tipo produto' não pode ser vazio")
    private TipoProdutoEnum tipoProduto;

    @NotNull(message = "O campo 'unidade comercial' não pode ser vazio")
    private UnidadeComercialEnum unidadeComercial;

    @NotNull(message = "O campo 'tipo peso' não pode ser vazio")
    private TipoPesoEnum tipoPeso;

    @NotEmpty(message = "A lista de preços do produto não pode ser vazia")
    @Size(message = "O produto deve possuir um preço de atacado e um preço de varejo", min = 2, max = 2)
    private List<PrecoRequest> precos = new ArrayList<>();
}
