package br.akd.svc.akadia.services.sistema.patrimonios;

import br.akd.svc.akadia.modules.erp.patrimonios.models.dto.response.page.PatrimonioPageResponse;
import br.akd.svc.akadia.modules.erp.patrimonios.models.dto.response.PatrimonioResponse;
import br.akd.svc.akadia.modules.erp.patrimonios.models.entity.PatrimonioEntity;
import br.akd.svc.akadia.models.entities.sistema.patrimonios.mocks.PatrimonioEntityBuilder;
import br.akd.svc.akadia.modules.erp.patrimonios.services.PatrimonioTypeConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("TypeConverter: Patrimonio")
class PatrimonioTypeConverterTest {

    @InjectMocks
    PatrimonioTypeConverter patrimonioTypeConverter;

    @Test
    @DisplayName("Deve testar método de conversão em massa de Entity para Response")
    void deveTestarMetodoDeConversaoEmMassaDeEntityParaResponse() {
        List<PatrimonioEntity> patrimonios = new ArrayList<>();
        patrimonios.add(PatrimonioEntityBuilder.builder().build());

        Pageable pageable = PageRequest.of(0, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), patrimonios.size());
        Page<PatrimonioEntity> patrimoniosPaged =
                new PageImpl<>(patrimonios.subList(start, end), pageable, patrimonios.size());

        PatrimonioPageResponse patrimonioPageResponse =
                patrimonioTypeConverter
                        .converteListaEntityListaResponse(patrimoniosPaged);

        Assertions.assertEquals("PatrimonioPageResponse(content=[PatrimonioResponse(id=1, " +
                        "dataCadastro=2023-08-21, horaCadastro=10:20, dataEntrada=2023-08-21, descricao=Dinheiro, " +
                        "valor=100.0, tipoPatrimonio=Ativo)], empty=false, first=true, last=true, number=0, " +
                        "numberOfElements=1, pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, " +
                        "totalElements=1, totalPages=1)",
                patrimonioPageResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de conversão de Entity para Response")
    void deveTestarMetodoDeConversaoDeEntityParaResponse() {
        PatrimonioEntity patrimonioEntity = PatrimonioEntityBuilder.builder()
                .build();

        PatrimonioResponse patrimonioResponse = patrimonioTypeConverter
                .converteEntityParaResponse(patrimonioEntity);

        Assertions.assertEquals("PatrimonioResponse(id=1, dataCadastro=2023-08-21, horaCadastro=10:20, " +
                        "dataEntrada=2023-08-21, descricao=Dinheiro, valor=100.0, tipoPatrimonio=Ativo)",
                patrimonioResponse.toString());
    }

}
