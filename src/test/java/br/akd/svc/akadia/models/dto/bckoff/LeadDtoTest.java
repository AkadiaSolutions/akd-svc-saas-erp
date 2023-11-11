package br.akd.svc.akadia.models.dto.bckoff;

import br.akd.svc.akadia.models.dto.bckoff.mocks.LeadDtoBuilder;
import br.akd.svc.akadia.models.dto.global.mocks.TelefoneDtoBuilder;
import br.akd.svc.akadia.modules.backoffice.models.enums.OrigemLeadEnum;
import br.akd.svc.akadia.modules.backoffice.models.dto.LeadDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("DTO: Lead")
class LeadDtoTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals("LeadDto(id=1, nome=Fulano da Silva, email=fulano@gmail.com, " +
                        "origemLeadEnum=MANUAL, telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, " +
                        "tipoTelefone=MOVEL_WHATSAPP))",
                LeadDtoBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {

        LeadDto lead = new LeadDto(
                1L,
                "Fulano",
                "fulano@gmail.com",
                OrigemLeadEnum.MANUAL,
                TelefoneDtoBuilder.builder().build()
        );

        Assertions.assertEquals("LeadDto(id=1, nome=Fulano, email=fulano@gmail.com, origemLeadEnum=MANUAL, " +
                        "telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefone=MOVEL_WHATSAPP))",
                lead.toString());
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {

        LeadDto lead = LeadDto.builder()
                .id(1L)
                .nome("Fulano")
                .email("fulano@gmail.com")
                .origemLeadEnum(OrigemLeadEnum.MANUAL)
                .telefone(TelefoneDtoBuilder.builder().build())
                .build();

        Assertions.assertEquals("LeadDto(id=1, nome=Fulano, email=fulano@gmail.com, origemLeadEnum=MANUAL, " +
                        "telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefone=MOVEL_WHATSAPP))",
                lead.toString());
    }
}
