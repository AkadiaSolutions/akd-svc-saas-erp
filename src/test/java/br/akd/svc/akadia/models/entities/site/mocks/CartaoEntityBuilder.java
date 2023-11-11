package br.akd.svc.akadia.models.entities.site.mocks;

import br.akd.svc.akadia.modules.web.models.entity.CartaoEntity;
import br.akd.svc.akadia.modules.web.models.enums.BandeiraCartaoEnum;

public class CartaoEntityBuilder {

    CartaoEntityBuilder () {}
    CartaoEntity cartaoEntity;

    public static CartaoEntityBuilder builder() {
        CartaoEntityBuilder builder = new CartaoEntityBuilder();
        builder.cartaoEntity = new CartaoEntity();
        builder.cartaoEntity.setId(1L);
        builder.cartaoEntity.setNomePortador("Gabriel");
        builder.cartaoEntity.setCpfCnpj("47153427821");
        builder.cartaoEntity.setNumero(5162306219378829L);
        builder.cartaoEntity.setCcv(318);
        builder.cartaoEntity.setMesExpiracao(8);
        builder.cartaoEntity.setAnoExpiracao(2025);
        builder.cartaoEntity.setTokenCartao(null);
        builder.cartaoEntity.setAtivo(true);
        builder.cartaoEntity.setBandeiraCartaoEnum(BandeiraCartaoEnum.VISA);
        return builder;
    }

    public CartaoEntity build() {
        return cartaoEntity;
    }

}
