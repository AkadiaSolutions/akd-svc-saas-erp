package br.akd.svc.akadia.modules.erp.precos.models.entity.id;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PrecoId implements Serializable {
    private UUID id;
    private Long empresa;
}
