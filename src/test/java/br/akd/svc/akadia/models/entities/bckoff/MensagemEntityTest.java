package br.akd.svc.akadia.models.entities.bckoff;

import br.akd.svc.akadia.models.entities.bckoff.mocks.MensagemEntityBuilder;
import br.akd.svc.akadia.models.enums.bckoff.CaminhoMensagemEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootTest
@DisplayName("Entity: Mensagem")
class MensagemEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "MensagemEntity(id=1, dataEnvio=2023-02-02, horaEnvio=23:24, visualizada=true, respondida=true, " +
                        "conteudo=Preciso de suporte com o meu login, caminhoMensagemEnum=CLIENTE_PARA_SUPORTE)",
                MensagemEntityBuilder.builder().comAnexo().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {

        MensagemEntity mensagemEntity = new MensagemEntity(
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
                "MensagemEntity(id=1, dataEnvio=2023-02-03, horaEnvio=00:21, visualizada=true, respondida=true, " +
                        "conteudo=Vou te ajudar, caminhoMensagemEnum=SUPORTE_PARA_CLIENTE)",
                mensagemEntity.toString()
        );
    }

}
