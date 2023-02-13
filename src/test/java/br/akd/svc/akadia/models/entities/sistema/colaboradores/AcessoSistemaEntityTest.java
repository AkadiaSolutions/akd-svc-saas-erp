package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.AcessoSistemaEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@DisplayName("Entity: AcessoSistema")
class AcessoSistemaEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AcessoSistemaEntity(id=1, acessoSistemaAtivo=true, nomeUsuario=admin, senha=123, " +
                        "senhaCriptografada=1239jd89j1u9tbhg)",
                AcessoSistemaEntityBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AcessoSistemaEntity acessoSistemaEntity = new AcessoSistemaEntity(
                1L,
                true,
                "admin",
                "123",
                "kapdsjfioahncub830198163"
        );
        Assertions.assertEquals(
                "AcessoSistemaEntity(id=1, acessoSistemaAtivo=true, nomeUsuario=admin, senha=123, " +
                        "senhaCriptografada=kapdsjfioahncub830198163)",
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
                        "senhaCriptografada=d9k1089fh19b)",
                acessoSistemaEntity.toString()
        );
    }

}
