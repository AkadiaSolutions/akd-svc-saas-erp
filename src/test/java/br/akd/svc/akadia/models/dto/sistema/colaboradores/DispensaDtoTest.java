package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.mocks.DispensaDtoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest
@DisplayName("DTO: Dispensa")
class DispensaDtoTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "DispensaDto(id=1, dataDispensa=2023-02-13, motivo=Corte de custos, listaNegra=false, " +
                        "anexos=[], contratoDispensa=[])",
                DispensaDtoBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        DispensaDto configuracaoPerfilDto = new DispensaDto(
                1L,
                LocalDate.of(2023, 2, 13).toString(),
                "Justa causa",
                true,
                new ArrayList<>(),
                new byte[]{}
        );
        Assertions.assertEquals(
                "DispensaDto(id=1, dataDispensa=2023-02-13, motivo=Justa causa, listaNegra=true, anexos=[], " +
                        "contratoDispensa=[])",
                configuracaoPerfilDto.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        DispensaDto configuracaoPerfilDto = DispensaDto.builder()
                .id(1L)
                .dataDispensa(LocalDate.of(2023, 2, 13).toString())
                .motivo("Corte de custos")
                .listaNegra(false)
                .anexos(new ArrayList<>())
                .build();
        Assertions.assertEquals(
                "DispensaDto(id=1, dataDispensa=2023-02-13, motivo=Corte de custos, listaNegra=false, " +
                        "anexos=[], contratoDispensa=null)",
                configuracaoPerfilDto.toString()
        );
    }
}
