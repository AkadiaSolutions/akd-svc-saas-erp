package br.akd.svc.akadia.models.entities.global;

import br.akd.svc.akadia.models.entities.global.mocks.ParentescoEntityBuilder;
import br.akd.svc.akadia.models.entities.global.mocks.TelefoneEntityBuilder;
import br.akd.svc.akadia.modules.global.enums.GrauParentescoEnum;
import br.akd.svc.akadia.modules.global.entity.ParentescoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Entity: Parentesco")
class ParentescoEntityTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ParentescoEntity(id=1, nome=Heitor Gonçalves Lagrota, dataNascimento=2021-04-11, " +
                        "cpf=588.543.987-21, grauParentescoEnum=FILHO, telefone=TelefoneEntity(id=1, prefixo=11, " +
                        "numero=979815415, tipoTelefone=MOVEL_WHATSAPP))",
                ParentescoEntityBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ParentescoEntity parentescoEntity = new ParentescoEntity(
                1L,
                "Guilherme",
                "2008-02-03",
                "123.456.789-10",
                GrauParentescoEnum.IRMAO,
                TelefoneEntityBuilder.builder().build()
        );
        Assertions.assertEquals(
                "ParentescoEntity(id=1, nome=Guilherme, dataNascimento=2008-02-03, cpf=123.456.789-10, " +
                        "grauParentescoEnum=IRMAO, telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, " +
                        "tipoTelefone=MOVEL_WHATSAPP))",
                parentescoEntity.toString()
        );
    }

}
