package br.akd.svc.akadia.models.dto.sistema.despesas.response;

import br.akd.svc.akadia.models.dto.sistema.despesas.response.mock.DespesaPageResponseBuilder;
import br.akd.svc.akadia.modules.erp.despesas.models.dto.response.page.DespesaPageResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: DespesaPage")
class DespesaPageResponseTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "DespesaPageResponse(content=null, empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=false, size=0, " +
                        "totalElements=0, totalPages=0)", DespesaPageResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        DespesaPageResponse despesaPageResponse = new DespesaPageResponse(
                null, true, true, true, 0, 0,
                0, 0, true, false, 0, 0L, 0);

        Assertions.assertEquals(
                "DespesaPageResponse(content=null, empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=false, size=0, " +
                        "totalElements=0, totalPages=0)",
                despesaPageResponse.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        DespesaPageResponse despesaPageResponse = DespesaPageResponse.builder()
                .content(null)
                .empty(true)
                .first(true)
                .last(true)
                .number(0)
                .numberOfElements(0)
                .pageNumber(0)
                .pageSize(0)
                .paged(true)
                .unpaged(true)
                .size(0)
                .totalElements(0L)
                .totalPages(0)
                .build();

        Assertions.assertEquals(
                "DespesaPageResponse(content=null, empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=true, size=0, " +
                        "totalElements=0, totalPages=0)",
                despesaPageResponse.toString()
        );

    }
}
