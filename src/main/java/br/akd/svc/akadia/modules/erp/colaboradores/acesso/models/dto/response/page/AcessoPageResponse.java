package br.akd.svc.akadia.modules.erp.colaboradores.acesso.models.dto.response.page;

import br.akd.svc.akadia.modules.erp.colaboradores.acesso.models.entity.AcessoEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AcessoPageResponse {
    List<AcessoEntity> content;
    Boolean empty;
    Boolean first;
    Boolean last;
    Integer number;
    Integer numberOfElements;
    Long offset;
    Integer pageNumber;
    Integer pageSize;
    Boolean paged;
    Boolean unpaged;
    Integer size;
    Long totalElements;
    Integer totalPages;
}
