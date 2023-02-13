package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.mocks.AdvertenciaDtoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@DisplayName("DTO: Advertencia")
class AdvertenciaDtoTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "AdvertenciaDto(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, motivo=Brigou na loja, " +
                        "descricao=Cuspiu no cliente, advertenciaAssinada=[])",
                AdvertenciaDtoBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        AdvertenciaDto advertenciaDto = new AdvertenciaDto(
                1L,
                LocalDate.of(2023, 2, 13).toString(),
                LocalTime.of(11, 17).toString(),
                "Falta todo dia",
                "Fica faltando no serviço toda hora",
                new byte[]{}
        );
        Assertions.assertEquals(
                "AdvertenciaDto(id=1, dataCadastro=2023-02-13, horaCadastro=11:17, " +
                        "motivo=Falta todo dia, descricao=Fica faltando no serviço toda hora, advertenciaAssinada=[])",
                advertenciaDto.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        AdvertenciaDto advertenciaDto = AdvertenciaDto.builder()
                .id(1L)
                .dataCadastro(LocalDate.of(2023, 2, 13).toString())
                .horaCadastro(LocalTime.of(11, 18).toString())
                .motivo("Falta todo dia")
                .descricao("Fica faltando no serviço toda hora")
                .advertenciaAssinada(new byte[]{})
                .build();
        Assertions.assertEquals(
                "AdvertenciaDto(id=1, dataCadastro=2023-02-13, horaCadastro=11:18, motivo=Falta todo dia, " +
                        "descricao=Fica faltando no serviço toda hora, advertenciaAssinada=[])",
                advertenciaDto.toString()
        );
    }

}
