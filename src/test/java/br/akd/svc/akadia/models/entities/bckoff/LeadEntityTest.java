package br.akd.svc.akadia.models.entities.bckoff;

import br.akd.svc.akadia.models.entities.bckoff.mocks.LeadEntityBuilder;
import br.akd.svc.akadia.models.entities.global.mocks.TelefoneEntityBuilder;
import br.akd.svc.akadia.modules.backoffice.models.enums.OrigemLeadEnum;
import br.akd.svc.akadia.modules.backoffice.models.entity.LeadEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: Lead")
class LeadEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals("LeadEntity(id=1, nome=Fulano da Silva, email=fulano@gmail.com, " +
                        "origemLeadEnum=MANUAL, telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, " +
                        "tipoTelefone=MOVEL_WHATSAPP))",
                LeadEntityBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {

        LeadEntity lead = new LeadEntity(
                1L,
                "Fulano",
                "fulano@gmail.com",
                OrigemLeadEnum.MANUAL,
                TelefoneEntityBuilder.builder().build()
        );

        Assertions.assertEquals("LeadEntity(id=1, nome=Fulano, email=fulano@gmail.com, origemLeadEnum=MANUAL, " +
                        "telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefone=MOVEL_WHATSAPP))",
                lead.toString());
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {

        LeadEntity lead = LeadEntity.builder()
                .id(1L)
                .nome("Fulano")
                .email("fulano@gmail.com")
                .origemLeadEnum(OrigemLeadEnum.MANUAL)
                .telefone(TelefoneEntityBuilder.builder().build())
                .build();

        Assertions.assertEquals("LeadEntity(id=1, nome=Fulano, email=fulano@gmail.com, origemLeadEnum=MANUAL, " +
                        "telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefone=MOVEL_WHATSAPP))",
                lead.toString());
    }
}
