package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.acao;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcaoEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AcaoPageResponse {
    List<AcaoEntity> content;
    Boolean empty;
    Boolean first;
    Boolean last;
    Integer number;
    Integer numberOfElements;
    Integer pageNumber;
    Integer pageSize;
    Boolean paged;
    Boolean unpaged;
    Integer size;
    Long totalElements;
    Integer totalPages;
}
