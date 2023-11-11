package br.akd.svc.akadia.modules.erp.patrimonios.models.dto.response.page;

import br.akd.svc.akadia.modules.erp.patrimonios.models.dto.response.PatrimonioResponse;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PatrimonioPageResponse {
    List<PatrimonioResponse> content;
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
