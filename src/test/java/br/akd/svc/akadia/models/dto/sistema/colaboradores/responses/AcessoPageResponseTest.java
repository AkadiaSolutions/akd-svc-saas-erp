package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses;

import br.akd.svc.akadia.modules.erp.colaboradores.acesso.models.dto.response.page.AcessoPageResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks.AcessoPageResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: AcessoPageResponse")
class AcessoPageResponseTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AcessoPageResponse(content=[], empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, offset=0, pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, " +
                        "totalElements=0, totalPages=0)",
                AcessoPageResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AcessoPageResponse acessoPageResponse = new AcessoPageResponse(
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
                "AcessoPageResponse(content=[], empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, offset=0, pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, " +
                        "totalElements=0, totalPages=0)",
                acessoPageResponse.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        AcessoPageResponse acessoPageResponse = AcessoPageResponse.builder()
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
                "AcessoPageResponse(content=[], empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, offset=0, pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, " +
                        "totalElements=0, totalPages=0)",
                acessoPageResponse.toString()
        );
    }
}
