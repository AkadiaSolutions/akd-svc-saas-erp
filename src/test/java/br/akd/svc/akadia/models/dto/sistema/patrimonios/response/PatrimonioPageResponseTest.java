package br.akd.svc.akadia.models.dto.sistema.patrimonios.response;

import br.akd.svc.akadia.modules.erp.patrimonios.models.dto.response.page.PatrimonioPageResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("PageResponse: Patrimonio")
class PatrimonioPageResponseTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "PatrimonioPageResponse(content=null, empty=null, first=null, last=null, number=null, " +
                        "numberOfElements=null, pageNumber=null, pageSize=null, paged=null, unpaged=null, size=null, " +
                        "totalElements=null, totalPages=null)", PatrimonioPageResponse.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        PatrimonioPageResponse patrimonioPageResponse = new PatrimonioPageResponse(
                null, true, true, true, 0, 0,
                0, 0, true, false, 0, 0L, 0);

        Assertions.assertEquals(
                "PatrimonioPageResponse(content=null, empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=false, size=0, " +
                        "totalElements=0, totalPages=0)",
                patrimonioPageResponse.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        PatrimonioPageResponse patrimonioPageResponse = PatrimonioPageResponse.builder()
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
                "PatrimonioPageResponse(content=null, empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=true, size=0, " +
                        "totalElements=0, totalPages=0)",
                patrimonioPageResponse.toString()
        );

    }
}
