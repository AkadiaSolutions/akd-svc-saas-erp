package br.akd.svc.akadia.services.sistema.despesas;

import br.akd.svc.akadia.modules.erp.despesas.models.dto.response.page.DespesaPageResponse;
import br.akd.svc.akadia.modules.erp.despesas.models.dto.response.DespesaResponse;
import br.akd.svc.akadia.modules.erp.despesas.models.entity.DespesaEntity;
import br.akd.svc.akadia.models.entities.sistema.despesas.mocks.DespesaEntityBuilder;
import br.akd.svc.akadia.modules.erp.despesas.services.DespesaTypeConverter;
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
@DisplayName("TypeConverter: Despesa")
class DespesaTypeConverterTest {

    @InjectMocks
    DespesaTypeConverter despesaTypeConverter;

    @Test
    @DisplayName("Deve testar método de conversão em massa de despesa Entity para despesa Response")
    void deveTestarMetodoDeConversaoEmMassaDeDespesaEntityParaDespesaResponse() {
        List<DespesaEntity> despesas = new ArrayList<>();
        despesas.add(DespesaEntityBuilder.builder().build());

        Pageable pageable = PageRequest.of(0, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), despesas.size());
        Page<DespesaEntity> despesasPaged =
                new PageImpl<>(despesas.subList(start, end), pageable, despesas.size());

        DespesaPageResponse despesaPageResponse =
                despesaTypeConverter
                        .converteListaDeDespesasEntityParaDespesasResponse(despesasPaged);

        Assertions.assertEquals("DespesaPageResponse(content=[DespesaResponse(id=1, dataCadastro=2023-08-18, " +
                        "horaCadastro=07:55, dataPagamento=2023-08-18, dataAgendamento=Pago, descricao=Gasolina carro, " +
                        "valor=100.0, observacao=Sem recorrências, qtdRecorrencias=0, statusDespesa=PAGO, " +
                        "tipoDespesa=VARIAVEL, tipoRecorrencia=SEM_RECORRENCIA)], empty=false, first=true, last=true, " +
                        "number=0, numberOfElements=1, pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, " +
                        "totalElements=1, totalPages=1)",
                despesaPageResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de conversão de DespesaEntity para DespesaResponse")
    void deveTestarMetodoDeConversaoDeDespesaEntityParaDespesaResponse() {
        DespesaEntity despesaEntity = DespesaEntityBuilder.builder()
                .build();

        DespesaResponse despesaResponse = despesaTypeConverter
                .converteDespesaEntityParaDespesaResponse(despesaEntity);

        Assertions.assertEquals("DespesaResponse(id=1, dataCadastro=2023-08-18, horaCadastro=07:55, " +
                        "dataPagamento=2023-08-18, dataAgendamento=Pago, descricao=Gasolina carro, valor=100.0, " +
                        "observacao=Sem recorrências, qtdRecorrencias=0, statusDespesa=PAGO, tipoDespesa=VARIAVEL, " +
                        "tipoRecorrencia=SEM_RECORRENCIA)",
                despesaResponse.toString());
    }

}
