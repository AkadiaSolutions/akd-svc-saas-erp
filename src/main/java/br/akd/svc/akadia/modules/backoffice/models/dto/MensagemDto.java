package br.akd.svc.akadia.modules.backoffice.models.dto;

import br.akd.svc.akadia.modules.backoffice.models.enums.CaminhoMensagemEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MensagemDto {
    private Long id;
    private String dataEnvio;
    private String horaEnvio;
    private Boolean visualizada;
    private Boolean respondida;
    private String conteudo;
    private CaminhoMensagemEnum caminhoMensagemEnum;
    private List<AnexoMensagemDto> anexos = new ArrayList<>();
}