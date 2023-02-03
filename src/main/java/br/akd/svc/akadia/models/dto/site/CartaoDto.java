package br.akd.svc.akadia.models.dto.site;

import br.akd.svc.akadia.models.enums.site.BandeiraCartaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartaoDto {
    private Long id;
    private String nomePortador;
    private String cpfCnpj;
    private Long numero;
    private Integer ccv;
    private Integer mesExpiracao;
    private Integer anoExpiracao;
    private String tokenCartao;
    private Boolean ativo;
    private BandeiraCartaoEnum bandeiraCartaoEnum;
}
