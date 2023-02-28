package br.akd.svc.akadia.models.entities.site;

import br.akd.svc.akadia.models.entities.site.mocks.CartaoEntityBuilder;
import br.akd.svc.akadia.models.enums.site.BandeiraCartaoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Entity: Cartao")
class CartaoEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "CartaoEntity(id=1, nomePortador=Gabriel, cpfCnpj=47153427821, numero=5162306219378829, " +
                        "ccv=318, mesExpiracao=8, anoExpiracao=2025, tokenCartao=null, ativo=true, bandeiraCartaoEnum=VISA)",
                CartaoEntityBuilder.builder().build().toString()
        );

    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        CartaoEntity cartaoEntity = new CartaoEntity(
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
                "CartaoEntity(id=1, nomePortador=Fulano, cpfCnpj=12345678910, numero=5162306219378829, " +
                        "ccv=315, mesExpiracao=4, anoExpiracao=24, tokenCartao=8f037c5a-20ec-48b5-96e3-412e1f4c4367, " +
                        "ativo=true, bandeiraCartaoEnum=ELO)",
                cartaoEntity.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        CartaoEntity cartaoEntity = CartaoEntity.builder()
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
                "CartaoEntity(id=1, nomePortador=Fulano, cpfCnpj=12345678910, numero=5162306219378829, " +
                        "ccv=310, mesExpiracao=2, anoExpiracao=27, tokenCartao=null, ativo=false, bandeiraCartaoEnum=HIPERCARD)",
                cartaoEntity.toString()
        );
    }

}
