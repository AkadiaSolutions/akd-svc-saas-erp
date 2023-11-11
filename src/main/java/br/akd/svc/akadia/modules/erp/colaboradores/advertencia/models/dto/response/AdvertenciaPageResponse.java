package br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.dto.response;

import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.entity.AdvertenciaEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdvertenciaPageResponse {
    List<AdvertenciaEntity> content;
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
