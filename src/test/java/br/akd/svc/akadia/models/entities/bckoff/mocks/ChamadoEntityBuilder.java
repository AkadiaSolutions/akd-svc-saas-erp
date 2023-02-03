package br.akd.svc.akadia.models.entities.bckoff.mocks;

import br.akd.svc.akadia.models.entities.bckoff.ChamadoEntity;
import br.akd.svc.akadia.models.entities.site.mocks.EmpresaEntityBuilder;
import br.akd.svc.akadia.models.enums.bckoff.CategoriaChamadoEnum;
import br.akd.svc.akadia.models.enums.bckoff.StatusChamadoEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ChamadoEntityBuilder {

    ChamadoEntityBuilder(){}
    ChamadoEntity chamadoEntity;

    public static ChamadoEntityBuilder builder() {
        ChamadoEntityBuilder builder = new ChamadoEntityBuilder();
        builder.chamadoEntity = new ChamadoEntity();
        builder.chamadoEntity.setDataCriacao(LocalDate.of(2023, 2, 2).toString());
        builder.chamadoEntity.setHoraCriacao(LocalTime.of(23, 31).toString());
        builder.chamadoEntity.setTicket(9841243L);
        builder.chamadoEntity.setDescricao("Ajuda com login");
        builder.chamadoEntity.setDataBaixa(LocalDate.of(2023, 2, 3).toString());
        builder.chamadoEntity.setHoraBaixa(LocalTime.of(14, 25).toString());
        builder.chamadoEntity.setCategoriaChamadoEnum(CategoriaChamadoEnum.PROBLEMA_TECNICO);
        builder.chamadoEntity.setStatusChamadoEnum(StatusChamadoEnum.FINALIZADO);
        builder.chamadoEntity.setAtendenteResponsavel(ColaboradorInternoEntityBuilder.builder().comParentescos().comChamados().build());
        builder.chamadoEntity.setEmpresa(EmpresaEntityBuilder.builder().build());
        builder.chamadoEntity.setAvaliacao(AvaliacaoEntityBuilder.builder().build());
        builder.chamadoEntity.setMensagens(new ArrayList<>());
        return builder;
    }

    public ChamadoEntityBuilder comMensagens() {
        this.chamadoEntity.getMensagens().add(MensagemEntityBuilder.builder().build());
        return this;
    }

    public ChamadoEntity build() {
        return chamadoEntity;
    }

}
