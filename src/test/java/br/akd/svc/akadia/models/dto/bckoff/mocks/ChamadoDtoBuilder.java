package br.akd.svc.akadia.models.dto.bckoff.mocks;

import br.akd.svc.akadia.modules.backoffice.models.dto.ChamadoDto;
import br.akd.svc.akadia.modules.backoffice.models.enums.CategoriaChamadoEnum;
import br.akd.svc.akadia.modules.backoffice.models.enums.StatusChamadoEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ChamadoDtoBuilder {

    ChamadoDtoBuilder(){}
    ChamadoDto chamadoDto;

    public static ChamadoDtoBuilder builder() {
        ChamadoDtoBuilder builder = new ChamadoDtoBuilder();
        builder.chamadoDto = new ChamadoDto();
        builder.chamadoDto.setDataCriacao(LocalDate.of(2023, 2, 2).toString());
        builder.chamadoDto.setHoraCriacao(LocalTime.of(23, 31).toString());
        builder.chamadoDto.setTicket(9841243L);
        builder.chamadoDto.setDescricao("Ajuda com login");
        builder.chamadoDto.setDataBaixa(LocalDate.of(2023, 2, 3).toString());
        builder.chamadoDto.setHoraBaixa(LocalTime.of(14, 25).toString());
        builder.chamadoDto.setCategoriaChamadoEnum(CategoriaChamadoEnum.PROBLEMA_TECNICO);
        builder.chamadoDto.setStatusChamadoEnum(StatusChamadoEnum.FINALIZADO);
        builder.chamadoDto.setAtendenteResponsavel(null);
        builder.chamadoDto.setEmpresa(null);
        builder.chamadoDto.setAvaliacao(null);
        builder.chamadoDto.setMensagens(new ArrayList<>());
        return builder;
    }

    public ChamadoDto build() {
        return chamadoDto;
    }

}
