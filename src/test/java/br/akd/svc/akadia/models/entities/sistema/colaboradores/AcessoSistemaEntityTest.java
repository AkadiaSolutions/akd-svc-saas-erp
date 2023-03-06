package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.AcessoSistemaEntityBuilder;
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
                "AcessoSistemaEntity(id=1, acessoSistemaAtivo=true, nomeUsuario=admin, senha=123, " +
                        "senhaCriptografada=1239jd89j1u9tbhg, privilegios=[])",
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
                "9js109j192jf18g09fj",
                privilegiosUsuario
        );
        Assertions.assertEquals(
                "AcessoSistemaEntity(id=1, acessoSistemaAtivo=true, nomeUsuario=admin, senha=123, " +
                        "senhaCriptografada=9js109j192jf18g09fj, privilegios=[])",
                acessoSistemaEntity.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        AcessoSistemaEntity acessoSistemaEntity = AcessoSistemaEntity.builder()
                .id(1L)
                .acessoSistemaAtivo(true)
                .nomeUsuario("admin")
                .senha("123")
                .senhaCriptografada("d9k1089fh19b")
                .build();
        Assertions.assertEquals(
                "AcessoSistemaEntity(id=1, acessoSistemaAtivo=true, nomeUsuario=admin, senha=123, " +
                        "senhaCriptografada=d9k1089fh19b, privilegios=null)",
                acessoSistemaEntity.toString()
        );
    }

}
