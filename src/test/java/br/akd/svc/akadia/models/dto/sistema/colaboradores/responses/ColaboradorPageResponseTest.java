package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks.ColaboradorPageResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
@DisplayName("Response: ColaboradorPageResponse")
class ColaboradorPageResponseTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ColaboradorPageResponse(content=[], empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, offset=0, pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, " +
                        "totalElements=0, totalPages=0)",
                ColaboradorPageResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ColaboradorPageResponse colaboradorPageResponse = new ColaboradorPageResponse(
                new ArrayList<>(),
                true,
                true,
                true,
                0,
                0,
                0L,
                0,
                10,
                true,
                false,
                10,
                0L,
                0
        );
        Assertions.assertEquals(
                "ColaboradorPageResponse(content=[], empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, offset=0, pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, " +
                        "totalElements=0, totalPages=0)",
                colaboradorPageResponse.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ColaboradorPageResponse colaboradorPageResponse = ColaboradorPageResponse.builder()
                .content(new ArrayList<>())
                .empty(true)
                .first(true)
                .last(true)
                .number(0)
                .numberOfElements(0)
                .offset(0L)
                .pageNumber(0)
                .pageSize(10)
                .paged(true)
                .unpaged(false)
                .size(10)
                .totalElements(0L)
                .totalPages(0)
                .build();
        Assertions.assertEquals(
                "ColaboradorPageResponse(content=[], empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, offset=0, pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, " +
                        "totalElements=0, totalPages=0)",
                colaboradorPageResponse.toString()
        );
    }
}
