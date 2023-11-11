package br.akd.svc.akadia.modules.web.models.dto.empresa;

import br.akd.svc.akadia.modules.web.models.dto.empresa.mocks.DadosEmpresaDeletadaDtoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("DTO: DadosEmpresaDeletada")
class DadosEmpresaDeletadaDtoTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "DadosEmpresaDeletadaDto(id=1, dataRemocao=2023-02-12, horaRemocao=13:22, cnpj=12123123000121, " +
                        "razaoSocial=KG DIMOPOULOS NOVA ERA LTDA, inscricaoMunicipal=null, inscricaoEstadual=145574080114)",
                DadosEmpresaDeletadaDtoBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        DadosEmpresaDeletadaDto dadosEmpresaDeletadaDto = new DadosEmpresaDeletadaDto(
                1L,
                LocalDate.of(2023, 2, 13).toString(),
                LocalTime.of(14, 41).toString(),
                "11111111000111",
                "UNIGRUBS LTDA",
                null,
                "145544071114"
        );

        Assertions.assertEquals(
                "DadosEmpresaDeletadaDto(id=1, dataRemocao=2023-02-13, horaRemocao=14:41, " +
                        "cnpj=11111111000111, razaoSocial=UNIGRUBS LTDA, inscricaoMunicipal=null, " +
                        "inscricaoEstadual=145544071114)",
                dadosEmpresaDeletadaDto.toString()
        );

    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        DadosEmpresaDeletadaDto dadosEmpresaDeletadaDto = DadosEmpresaDeletadaDto.builder()
                .id(1L)
                .dataRemocao(LocalDate.of(2023, 2, 13).toString())
                .horaRemocao(LocalTime.of(14, 41).toString())
                .cnpj("11111111000111")
                .razaoSocial("UNIGRUBS LTDA")
                .inscricaoMunicipal(null)
                .inscricaoEstadual("145544071114")
                .build();
        Assertions.assertEquals(
                "DadosEmpresaDeletadaDto(id=1, dataRemocao=2023-02-13, horaRemocao=14:41, " +
                        "cnpj=11111111000111, razaoSocial=UNIGRUBS LTDA, inscricaoMunicipal=null, " +
                        "inscricaoEstadual=145544071114)",
                dadosEmpresaDeletadaDto.toString()
        );
    }

}
