package br.akd.svc.akadia.models.dto.site;

import br.akd.svc.akadia.models.dto.site.mocks.CartaoDtoBuilder;
import br.akd.svc.akadia.modules.web.models.enums.BandeiraCartaoEnum;
import br.akd.svc.akadia.modules.web.models.dto.CartaoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("DTO: Cartao")
class CartaoDtoTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "CartaoDto(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, " +
                        "ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA)",
                CartaoDtoBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        CartaoDto cartaoDto = new CartaoDto(
                1L,
                "Fulano",
                "12345678910",
                5162306219378829L,
                315,
                4,
                24,
                "8f037c5a-20ec-48b5-96e3-412e1f4c4367",
                true,
                BandeiraCartaoEnum.ELO
        );
        Assertions.assertEquals(
                "CartaoDto(id=1, nomePortador=Fulano, cpfCnpj=12345678910, numero=5162306219378829, " +
                        "ccv=315, mesExpiracao=4, anoExpiracao=24, tokenCartao=8f037c5a-20ec-48b5-96e3-412e1f4c4367, " +
                        "ativo=true, bandeiraCartaoEnum=ELO)",
                cartaoDto.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        CartaoDto cartaoDto = CartaoDto.builder()
                .id(1L)
                .nomePortador("Fulano")
                .cpfCnpj("12345678910")
                .numero(5162306219378829L)
                .ccv(310)
                .mesExpiracao(2)
                .anoExpiracao(27)
                .tokenCartao(null)
                .ativo(false)
                .bandeiraCartaoEnum(BandeiraCartaoEnum.HIPERCARD)
                .build();
        Assertions.assertEquals(
                "CartaoDto(id=1, nomePortador=Fulano, cpfCnpj=12345678910, numero=5162306219378829, " +
                        "ccv=310, mesExpiracao=2, anoExpiracao=27, tokenCartao=null, ativo=false, bandeiraCartaoEnum=HIPERCARD)",
                cartaoDto.toString()
        );
    }
}
