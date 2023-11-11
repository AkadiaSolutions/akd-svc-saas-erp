package br.akd.svc.akadia.modules.erp.despesas.models.dto.response.page;

import br.akd.svc.akadia.modules.erp.despesas.models.dto.response.DespesaResponse;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DespesaPageResponse {
    List<DespesaResponse> content;
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
