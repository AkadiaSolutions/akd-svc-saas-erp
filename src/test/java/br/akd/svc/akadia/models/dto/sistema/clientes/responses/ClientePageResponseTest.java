package br.akd.svc.akadia.models.dto.sistema.clientes.responses;

import br.akd.svc.akadia.models.dto.sistema.clientes.responses.mocks.ClientePageResponseBuilder;
import br.akd.svc.akadia.modules.erp.clientes.models.dto.response.page.ClientePageResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Response: ClientePage")
class ClientePageResponseTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ClientePageResponse(content=null, empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=false, size=0, " +
                        "totalElements=0, totalPages=0)", ClientePageResponseBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ClientePageResponse clientePageResponse = new ClientePageResponse(
                null, true, true, true, 0, 0,
                0, 0, true, false, 0, 0L, 0);

        Assertions.assertEquals(
                "ClientePageResponse(content=null, empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=false, size=0, " +
                        "totalElements=0, totalPages=0)",
                clientePageResponse.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        ClientePageResponse clientePageResponse = ClientePageResponse.builder()
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
                "ClientePageResponse(content=null, empty=true, first=true, last=true, number=0, " +
                        "numberOfElements=0, pageNumber=0, pageSize=0, paged=true, unpaged=true, size=0, " +
                        "totalElements=0, totalPages=0)",
                clientePageResponse.toString()
        );

    }
}
