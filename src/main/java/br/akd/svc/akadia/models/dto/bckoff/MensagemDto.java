package br.akd.svc.akadia.models.dto.bckoff;

import br.akd.svc.akadia.models.enums.bckoff.CaminhoMensagemEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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