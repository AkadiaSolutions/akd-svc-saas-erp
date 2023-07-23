package br.akd.svc.akadia.models.entities.global;

import br.akd.svc.akadia.models.enums.global.TipoTelefoneEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: Telefone")
class TelefoneEntityTest {

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {

        TelefoneEntity telefoneEntity =
                new TelefoneEntity(1L, "11", "979815415", TipoTelefoneEnum.MOVEL_WHATSAPP);

        Assertions.assertEquals(
                "TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefone=MOVEL_WHATSAPP)",
                telefoneEntity.toString()
        );

    }

    @Test
    @DisplayName("Deve testar getTelefoneCompleto")
    void deveTestarGetTelefoneCompleto() {
        TelefoneEntity telefoneEntity = TelefoneEntity.builder()
                .id(1L)
                .prefixo("11")
                .numero("979815415")
                .tipoTelefone(TipoTelefoneEnum.MOVEL_WHATSAPP)
                .build();

        String telefoneCompleto = telefoneEntity.getTelefoneCompleto();

        Assertions.assertEquals(
                "(11) 979815415", telefoneCompleto
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        TelefoneEntity telefoneEntity = TelefoneEntity.builder()
                .id(1L)
                .prefixo("11")
                .numero("979815415")
                .tipoTelefone(TipoTelefoneEnum.MOVEL_WHATSAPP)
                .build();
        Assertions.assertEquals(
                "TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefone=MOVEL_WHATSAPP)",
                telefoneEntity.toString()
        );
    }

}
