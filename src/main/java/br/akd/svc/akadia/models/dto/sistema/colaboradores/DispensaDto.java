package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DispensaDto {
    private Long id;

    private String dataDispensa;

    private String motivo;

    private Boolean listaNegra;

    private List<byte[]> anexos;

    private byte[] contratoDispensa;
}
