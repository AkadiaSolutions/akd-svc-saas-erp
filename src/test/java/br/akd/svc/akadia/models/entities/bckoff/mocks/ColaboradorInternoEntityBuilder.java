package br.akd.svc.akadia.models.entities.bckoff.mocks;

import br.akd.svc.akadia.modules.backoffice.models.entity.ChamadoEntity;
import br.akd.svc.akadia.modules.backoffice.models.entity.ColaboradorInternoEntity;
import br.akd.svc.akadia.models.entities.global.mocks.ParentescoEntityBuilder;
import br.akd.svc.akadia.models.entities.global.mocks.TelefoneEntityBuilder;
import br.akd.svc.akadia.modules.backoffice.models.enums.CargoInternoEnum;
import br.akd.svc.akadia.modules.backoffice.models.enums.StatusAtividadeEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ColaboradorInternoEntityBuilder {

    ColaboradorInternoEntityBuilder() {
    }

    ColaboradorInternoEntity colaboradorInternoEntity;

    public static ColaboradorInternoEntityBuilder builder() {

        ColaboradorInternoEntityBuilder builder = new ColaboradorInternoEntityBuilder();
        builder.colaboradorInternoEntity = new ColaboradorInternoEntity();
        builder.colaboradorInternoEntity.setId(1L);
        builder.colaboradorInternoEntity.setDataCadastro(LocalDate.of(2023, 2, 2).toString());
        builder.colaboradorInternoEntity.setHoraCadastro(LocalTime.of(17, 10).toString());
        builder.colaboradorInternoEntity.setNome("Gabriel Lagrota");
        builder.colaboradorInternoEntity.setEmail("gabriellagrota23@gmail.com");
        builder.colaboradorInternoEntity.setCpf("471.534.278-21");
        builder.colaboradorInternoEntity.setAcessoSistemaLiberado(true);
        builder.colaboradorInternoEntity.setDataNascimento("1998-07-21");
        builder.colaboradorInternoEntity.setRemuneracao(10000.0);
        builder.colaboradorInternoEntity.setTempoFerias(30);
        builder.colaboradorInternoEntity.setEntradaEmpresa("01-01-2023");
        builder.colaboradorInternoEntity.setSaidaEmpresa(null);
        builder.colaboradorInternoEntity.setCargoEnum(CargoInternoEnum.GESTAO);
        builder.colaboradorInternoEntity.setStatusAtividadeEnum(StatusAtividadeEnum.ATIVO);
        builder.colaboradorInternoEntity.setTelefone(TelefoneEntityBuilder.builder().build());
        builder.colaboradorInternoEntity.setParentescos(new ArrayList<>());
        builder.colaboradorInternoEntity.setChamados(new ArrayList<>());
        return builder;
    }

    public ColaboradorInternoEntityBuilder comParentescos() {
        this.colaboradorInternoEntity.getParentescos().add(ParentescoEntityBuilder.builder().build());
        return this;
    }

    public ColaboradorInternoEntityBuilder comChamados() {
        this.colaboradorInternoEntity.getChamados().add(new ChamadoEntity());
        return this;
    }

    public ColaboradorInternoEntity build() {
        return colaboradorInternoEntity;
    }

}
