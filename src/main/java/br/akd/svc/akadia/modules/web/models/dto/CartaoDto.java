package br.akd.svc.akadia.modules.web.models.dto;

import br.akd.svc.akadia.modules.web.models.enums.BandeiraCartaoEnum;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
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
