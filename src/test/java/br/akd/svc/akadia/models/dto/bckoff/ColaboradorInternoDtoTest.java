package br.akd.svc.akadia.models.dto.bckoff;

import br.akd.svc.akadia.models.dto.bckoff.mocks.ColaboradorInternoDtoBuilder;
import br.akd.svc.akadia.models.dto.global.mocks.TelefoneDtoBuilder;
import br.akd.svc.akadia.models.enums.bckoff.CargoInternoEnum;
import br.akd.svc.akadia.models.enums.bckoff.StatusAtividadeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootTest
@DisplayName("DTO: ColaboradorInterno")
class ColaboradorInternoDtoTest {

    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals("ColaboradorInternoDto(id=1, dataCadastro=2023-02-02, horaCadastro=17:10, nome=Gabriel Lagrota, email=gabriellagrota23@gmail.com, cpf=471.534.278-21, acessoSistemaLiberado=true, dataNascimento=1998-07-21, remuneracao=10000.0, tempoFerias=30, entradaEmpresa=01-01-2023, saidaEmpresa=null, cargoEnum=GESTAO, statusAtividadeEnum=ATIVO, telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), parentescos=[ParentescoDto(id=1, nome=Heitor Gonçalves Lagrota, dataNascimento=2021-04-11, cpf=588.543.987-21, grauParentescoEnum=FILHO, telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP))], chamados=[ChamadoDto(id=null, dataCriacao=null, horaCriacao=null, ticket=null, descricao=null, dataBaixa=null, horaBaixa=null, categoriaChamadoEnum=null, statusChamadoEnum=null, atendenteResponsavel=null, empresa=null, avaliacao=null, mensagens=[])])",
                ColaboradorInternoDtoBuilder.builder().comParentescos().comChamados().build().toString());
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        ColaboradorInternoDto colaboradorInternoDto = new ColaboradorInternoDto(
                1L,
                LocalDate.of(2023, 2, 2).toString(),
                LocalTime.of(23, 52).toString(),
                "Fulano",
                "fulano@gmail.com",
                "123.456.789-10",
                true,
                "1989-04-15",
                8700.0,
                30,
                "2023-01-02",
                null,
                CargoInternoEnum.DESENVOLVEDOR,
                StatusAtividadeEnum.ATIVO,
                TelefoneDtoBuilder.builder().build(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        Assertions.assertEquals("ColaboradorInternoDto(id=1, dataCadastro=2023-02-02, horaCadastro=23:52, nome=Fulano, email=fulano@gmail.com, cpf=123.456.789-10, acessoSistemaLiberado=true, dataNascimento=1989-04-15, remuneracao=8700.0, tempoFerias=30, entradaEmpresa=2023-01-02, saidaEmpresa=null, cargoEnum=DESENVOLVEDOR, statusAtividadeEnum=ATIVO, telefone=TelefoneDto(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), parentescos=[], chamados=[])",
                colaboradorInternoDto.toString());
    }

}
