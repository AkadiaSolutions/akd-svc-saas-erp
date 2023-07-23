package br.akd.svc.akadia.models.dto.global;

import br.akd.svc.akadia.models.dto.global.mocks.ParentescoDtoBuilder;
import br.akd.svc.akadia.models.dto.global.mocks.TelefoneDtoBuilder;
import br.akd.svc.akadia.models.enums.global.GrauParentescoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("DTO: Parentesco")
class ParentescoDtoTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ParentescoDto(id=1, nome=Heitor Gonçalves Lagrota, dataNascimento=2021-04-11, " +
                        "cpf=588.543.987-21, grauParentescoEnum=FILHO, telefone=TelefoneDto(id=1, prefixo=11, " +
                        "numero=979815415, tipoTelefone=MOVEL_WHATSAPP))",
                ParentescoDtoBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ParentescoDto parentescoDto = new ParentescoDto(
                1L,
                "Guilherme",
                "2008-02-03",
                "123.456.789-10",
                GrauParentescoEnum.IRMAO,
                TelefoneDtoBuilder.builder().build()
        );
        Assertions.assertEquals(
                "ParentescoDto(id=1, nome=Guilherme, dataNascimento=2008-02-03, cpf=123.456.789-10, " +
                        "grauParentescoEnum=IRMAO, telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, " +
                        "tipoTelefone=MOVEL_WHATSAPP))",
                parentescoDto.toString()
        );
    }
}
