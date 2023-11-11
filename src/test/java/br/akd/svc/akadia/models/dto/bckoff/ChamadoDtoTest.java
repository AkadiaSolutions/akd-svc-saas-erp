package br.akd.svc.akadia.models.dto.bckoff;

import br.akd.svc.akadia.models.dto.bckoff.mocks.ChamadoDtoBuilder;
import br.akd.svc.akadia.modules.backoffice.models.enums.CategoriaChamadoEnum;
import br.akd.svc.akadia.modules.backoffice.models.enums.StatusChamadoEnum;
import br.akd.svc.akadia.modules.backoffice.models.dto.ChamadoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("DTO: Chamado")
class ChamadoDtoTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "ChamadoDto(id=null, dataCriacao=2023-02-02, horaCriacao=23:31, ticket=9841243, " +
                        "descricao=Ajuda com login, dataBaixa=2023-02-03, horaBaixa=14:25, " +
                        "categoriaChamadoEnum=PROBLEMA_TECNICO, statusChamadoEnum=FINALIZADO, atendenteResponsavel=null, " +
                        "empresa=null, avaliacao=null, mensagens=[])",
                ChamadoDtoBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Deve testar allArgsConstructor")
    void deveTestarAllArgsConstructor() {

        ChamadoDto chamadoDto = new ChamadoDto(
                1L,
                LocalDate.of(2023, 2, 3).toString(),
                LocalTime.of(15, 16).toString(),
                12345678L,
                "Preciso de ajuda com certificado digital",
                null,
                null,
                CategoriaChamadoEnum.DUVIDA_TECNICA,
                StatusChamadoEnum.EM_ATENDIMENTO,
                null,
                null,
                null,
                new ArrayList<>()
        );

        Assertions.assertEquals(
                "ChamadoDto(id=1, dataCriacao=2023-02-03, horaCriacao=15:16, ticket=12345678, " +
                        "descricao=Preciso de ajuda com certificado digital, dataBaixa=null, horaBaixa=null, " +
                        "categoriaChamadoEnum=DUVIDA_TECNICA, statusChamadoEnum=EM_ATENDIMENTO, " +
                        "atendenteResponsavel=null, empresa=null, avaliacao=null, mensagens=[])",
                chamadoDto.toString());
    }

}
