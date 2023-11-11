package br.akd.svc.akadia.models.dto.bckoff;

import br.akd.svc.akadia.models.dto.bckoff.mocks.MensagemDtoBuilder;
import br.akd.svc.akadia.modules.backoffice.models.enums.CaminhoMensagemEnum;
import br.akd.svc.akadia.modules.backoffice.models.dto.MensagemDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("DTO: Mensagem")
class MensagemDtoTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "MensagemDto(id=1, dataEnvio=2023-02-02, horaEnvio=23:24, visualizada=true, respondida=true, conteudo=Preciso de suporte com o meu login, caminhoMensagemEnum=CLIENTE_PARA_SUPORTE, anexos=[AnexoMensagemDto(id=1, dados=[], nome=imagem.png, tipo=png)])",
                MensagemDtoBuilder.builder().comAnexo().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {

        MensagemDto mensagemDto = new MensagemDto(
                1L,
                LocalDate.of(2023, 2, 3).toString(),
                LocalTime.of(0, 21).toString(),
                true,
                true,
                "Vou te ajudar",
                CaminhoMensagemEnum.SUPORTE_PARA_CLIENTE,
                new ArrayList<>()
        );

        Assertions.assertEquals(
                "MensagemDto(id=1, dataEnvio=2023-02-03, horaEnvio=00:21, visualizada=true, respondida=true, conteudo=Vou te ajudar, caminhoMensagemEnum=SUPORTE_PARA_CLIENTE, anexos=[])",
                mensagemDto.toString()
        );
    }
}
