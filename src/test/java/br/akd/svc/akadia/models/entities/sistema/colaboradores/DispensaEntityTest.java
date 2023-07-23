package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.DispensaEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: Dispensa")
class DispensaEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "DispensaEntity(id=1, dataDispensa=2023-02-13, motivo=Corte de custos, listaNegra=false, " +
                        "anexos=[], contratoDispensa=[])",
                DispensaEntityBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        DispensaEntity configuracaoPerfilEntity = new DispensaEntity(
                1L,
                LocalDate.of(2023, 2, 13).toString(),
                "Justa causa",
                true,
                new ArrayList<>(),
                new byte[]{}
        );
        Assertions.assertEquals(
                "DispensaEntity(id=1, dataDispensa=2023-02-13, motivo=Justa causa, listaNegra=true, anexos=[], " +
                        "contratoDispensa=[])",
                configuracaoPerfilEntity.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        DispensaEntity configuracaoPerfilEntity = DispensaEntity.builder()
                .id(1L)
                .dataDispensa(LocalDate.of(2023, 2, 13).toString())
                .motivo("Corte de custos")
                .listaNegra(false)
                .anexos(new ArrayList<>())
                .build();
        Assertions.assertEquals(
                "DispensaEntity(id=1, dataDispensa=2023-02-13, motivo=Corte de custos, listaNegra=false, " +
                        "anexos=[], contratoDispensa=null)",
                configuracaoPerfilEntity.toString()
        );
    }

}
