package br.akd.svc.akadia.models.dto.site.mocks;

import br.akd.svc.akadia.models.dto.site.CartaoDto;
import br.akd.svc.akadia.models.enums.site.BandeiraCartaoEnum;

public class CartaoDtoBuilder {

    CartaoDtoBuilder() {}
    CartaoDto cartaoDto;

    public static CartaoDtoBuilder builder() {
        CartaoDtoBuilder builder = new CartaoDtoBuilder();
        builder.cartaoDto = new CartaoDto();
        builder.cartaoDto.setId(1L);
        builder.cartaoDto.setNomePortador("Gabriel");
        builder.cartaoDto.setCpfCnpj("47153427821");
        builder.cartaoDto.setNumero(5162306219378829L);
        builder.cartaoDto.setCcv(318);
        builder.cartaoDto.setMesExpiracao(8);
        builder.cartaoDto.setAnoExpiracao(2025);
        builder.cartaoDto.setTokenCartao(null);
        builder.cartaoDto.setAtivo(true);
        builder.cartaoDto.setBandeiraCartaoEnum(BandeiraCartaoEnum.VISA);
        return builder;
    }

    public CartaoDto build() {
        return cartaoDto;
    }

}
