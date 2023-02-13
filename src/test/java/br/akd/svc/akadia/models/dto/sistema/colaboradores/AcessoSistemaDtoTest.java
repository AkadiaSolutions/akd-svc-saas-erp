package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.mocks.AcessoSistemaDtoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@DisplayName("DTO: AcessoSistema")
class AcessoSistemaDtoTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AcessoSistemaDto(id=1, acessoSistemaAtivo=true, nomeUsuario=admin, senha=123, " +
                        "senhaCriptografada=1239jd89j1u9tbhg)",
                AcessoSistemaDtoBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AcessoSistemaDto acessoSistemaDto = new AcessoSistemaDto(
                1L,
                true,
                "admin",
                "123",
                "kapdsjfioahncub830198163"
        );
        Assertions.assertEquals(
                "AcessoSistemaDto(id=1, acessoSistemaAtivo=true, nomeUsuario=admin, senha=123, " +
                        "senhaCriptografada=kapdsjfioahncub830198163)",
                acessoSistemaDto.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        AcessoSistemaDto acessoSistemaDto = AcessoSistemaDto.builder()
                .id(1L)
                .acessoSistemaAtivo(true)
                .nomeUsuario("admin")
                .senha("123")
                .senhaCriptografada("d9k1089fh19b")
                .build();
        Assertions.assertEquals(
                "AcessoSistemaDto(id=1, acessoSistemaAtivo=true, nomeUsuario=admin, senha=123, " +
                        "senhaCriptografada=d9k1089fh19b)",
                acessoSistemaDto.toString()
        );
    }

}
