package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses;

import br.akd.svc.akadia.modules.erp.colaboradores.acao.models.dto.response.page.AcaoPageResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks.AcaoPageResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: AcaoPageResponse")
class AcaoPageResponseTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AcaoPageResponse(content=[], empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, " +
                        "totalElements=0, totalPages=0)",
                AcaoPageResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AcaoPageResponse acaoPageResponse = new AcaoPageResponse(
                new ArrayList<>(),
                true,
                true,
                true,
                0,
                0,
                0,
                10,
                true,
                false,
                10,
                0L,
                0
        );
        Assertions.assertEquals(
                "AcaoPageResponse(content=[], empty=true, first=true, last=true, number=0, numberOfElements=0, " +
                        "pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, totalElements=0, totalPages=0)",
                acaoPageResponse.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        AcaoPageResponse acaoPageResponse = AcaoPageResponse.builder()
                .content(new ArrayList<>())
                .empty(true)
                .first(true)
                .last(true)
                .number(0)
                .numberOfElements(0)
                .pageNumber(0)
                .pageSize(10)
                .paged(true)
                .unpaged(false)
                .size(10)
                .totalElements(0L)
                .totalPages(0)
                .build();
        Assertions.assertEquals(
                "AcaoPageResponse(content=[], empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, " +
                        "totalElements=0, totalPages=0)",
                acaoPageResponse.toString()
        );
    }

}
