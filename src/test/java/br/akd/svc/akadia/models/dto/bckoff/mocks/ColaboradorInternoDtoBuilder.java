package br.akd.svc.akadia.models.dto.bckoff.mocks;

import br.akd.svc.akadia.models.dto.bckoff.ChamadoDto;
import br.akd.svc.akadia.models.dto.bckoff.ColaboradorInternoDto;
import br.akd.svc.akadia.models.dto.global.mocks.ParentescoDtoBuilder;
import br.akd.svc.akadia.models.dto.global.mocks.TelefoneDtoBuilder;
import br.akd.svc.akadia.models.enums.bckoff.CargoInternoEnum;
import br.akd.svc.akadia.models.enums.bckoff.StatusAtividadeEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ColaboradorInternoDtoBuilder {

    ColaboradorInternoDtoBuilder() {
    }

    ColaboradorInternoDto colaboradorInternoDto;

    public static ColaboradorInternoDtoBuilder builder() {

        ColaboradorInternoDtoBuilder builder = new ColaboradorInternoDtoBuilder();
        builder.colaboradorInternoDto = new ColaboradorInternoDto();
        builder.colaboradorInternoDto.setId(1L);
        builder.colaboradorInternoDto.setDataCadastro(LocalDate.of(2023, 2, 2).toString());
        builder.colaboradorInternoDto.setHoraCadastro(LocalTime.of(17, 10).toString());
        builder.colaboradorInternoDto.setNome("Gabriel Lagrota");
        builder.colaboradorInternoDto.setEmail("gabriellagrota23@gmail.com");
        builder.colaboradorInternoDto.setCpf("471.534.278-21");
        builder.colaboradorInternoDto.setAcessoSistemaLiberado(true);
        builder.colaboradorInternoDto.setDataNascimento("1998-07-21");
        builder.colaboradorInternoDto.setRemuneracao(10000.0);
        builder.colaboradorInternoDto.setTempoFerias(30);
        builder.colaboradorInternoDto.setEntradaEmpresa("01-01-2023");
        builder.colaboradorInternoDto.setSaidaEmpresa(null);
        builder.colaboradorInternoDto.setCargoEnum(CargoInternoEnum.GESTAO);
        builder.colaboradorInternoDto.setStatusAtividadeEnum(StatusAtividadeEnum.ATIVO);
        builder.colaboradorInternoDto.setTelefone(TelefoneDtoBuilder.builder().build());
        builder.colaboradorInternoDto.setParentescos(new ArrayList<>());
        builder.colaboradorInternoDto.setChamados(new ArrayList<>());
        return builder;
    }

    public ColaboradorInternoDtoBuilder comParentescos() {
        this.colaboradorInternoDto.getParentescos().add(ParentescoDtoBuilder.builder().build());
        return this;
    }

    public ColaboradorInternoDtoBuilder comChamados() {
        this.colaboradorInternoDto.getChamados().add(new ChamadoDto());
        return this;
    }

    public ColaboradorInternoDto build() {
        return colaboradorInternoDto;
    }

}
