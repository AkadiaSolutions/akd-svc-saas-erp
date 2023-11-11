package br.akd.svc.akadia.modules.erp.produtos.models.entity.id;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProdutoId implements Serializable {
    private UUID id;
    private Long empresa;
}
