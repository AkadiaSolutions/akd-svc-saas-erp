package br.akd.svc.akadia.modules.erp.colaboradores.acao.models.dto.response.page;

import br.akd.svc.akadia.modules.erp.colaboradores.acao.models.entity.AcaoEntity;
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
