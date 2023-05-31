package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModulosEnum;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.AcessoSistemaEntityBuilder;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.PermissaoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;


@SpringBootTest
@DisplayName("Entity: AcessoSistema")
class AcessoSistemaEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AcessoSistemaEntity(id=1, acessoSistemaAtivo=true, senha=123, " +
                        "senhaCriptografada=1239jd89j1u9tbhg, permissaoEnum=LEITURA_AVANCADA_ALTERACAO, privilegios=[])",
                AcessoSistemaEntityBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {

        Set<ModulosEnum> privilegiosUsuario = new HashSet<>();

        AcessoSistemaEntity acessoSistemaEntity = new AcessoSistemaEntity(
                1L,
                true,
                "admin",
                "123",
                PermissaoEnum.LEITURA_AVANCADA_ALTERACAO,
                privilegiosUsuario
        );
        Assertions.assertEquals(
                "AcessoSistemaEntity(id=1, acessoSistemaAtivo=true, senha=admin, senhaCriptografada=123, " +
                        "permissaoEnum=LEITURA_AVANCADA_ALTERACAO, privilegios=[])",
                acessoSistemaEntity.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        Set<ModulosEnum> privilegiosUsuario = new HashSet<>();
        AcessoSistemaEntity acessoSistemaEntity = AcessoSistemaEntity.builder()
                .id(1L)
                .acessoSistemaAtivo(true)
                .senha("123")
                .senhaCriptografada("d9k1089fh19b")
                .permissaoEnum(PermissaoEnum.LEITURA_BASICA)
                .privilegios(privilegiosUsuario)
                .build();
        Assertions.assertEquals(
                "AcessoSistemaEntity(id=1, acessoSistemaAtivo=true, senha=123, " +
                        "senhaCriptografada=d9k1089fh19b, permissaoEnum=LEITURA_BASICA, privilegios=[])",
                acessoSistemaEntity.toString()
        );
    }

}
