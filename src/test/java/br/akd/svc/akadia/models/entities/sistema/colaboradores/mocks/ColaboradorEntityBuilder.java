package br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModeloContratacaoEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModeloTrabalhoEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.StatusColaboradorEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TipoOcupacaoEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class ColaboradorEntityBuilder {

    ColaboradorEntityBuilder() {
    }

    ColaboradorEntity colaboradorEntity;

    public static ColaboradorEntityBuilder builder() {
        ColaboradorEntityBuilder builder = new ColaboradorEntityBuilder();
        builder.colaboradorEntity = new ColaboradorEntity();
        builder.colaboradorEntity.setId(1L);
        builder.colaboradorEntity.setDataCadastro(LocalDate.of(2023, 2, 13).toString());
        builder.colaboradorEntity.setHoraCadastro(LocalTime.of(10, 44).toString());
        builder.colaboradorEntity.setFotoPerfil(new byte[]{});
        builder.colaboradorEntity.setNome("João da Silva");
        builder.colaboradorEntity.setDataNascimento("2021-04-11");
        builder.colaboradorEntity.setEmail("joaosilva@gmail.com");
        builder.colaboradorEntity.setCpfCnpj("12345678910");
        builder.colaboradorEntity.setAtivo(true);
        builder.colaboradorEntity.setExcluido(false);
        builder.colaboradorEntity.setSalario(2000.0);
        builder.colaboradorEntity.setEntradaEmpresa(LocalDate.of(2023, 2, 13).toString());
        builder.colaboradorEntity.setSaidaEmpresa(null);
        builder.colaboradorEntity.setContratoContratacao(new byte[]{});
        builder.colaboradorEntity.setOcupacao("Técnico Interno");
        builder.colaboradorEntity.setTipoOcupacaoEnum(TipoOcupacaoEnum.TECNICO_INTERNO);
        builder.colaboradorEntity.setModeloContratacaoEnum(ModeloContratacaoEnum.CLT);
        builder.colaboradorEntity.setModeloTrabalhoEnum(ModeloTrabalhoEnum.PRESENCIAL);
        builder.colaboradorEntity.setStatusColaboradorEnum(StatusColaboradorEnum.ATIVO);
        builder.colaboradorEntity.setAcessoSistema(null);
        builder.colaboradorEntity.setConfiguracaoPerfil(null);
        builder.colaboradorEntity.setEndereco(null);
        builder.colaboradorEntity.setTelefone(null);
        builder.colaboradorEntity.setExpediente(null);
        builder.colaboradorEntity.setDispensa(null);
        builder.colaboradorEntity.setPontos(null);
        builder.colaboradorEntity.setHistoricoFerias(null);
        builder.colaboradorEntity.setAdvertencias(null);
        builder.colaboradorEntity.setParentescos(null);
        builder.colaboradorEntity.setEmpresa(null);
        return builder;
    }

    public ColaboradorEntity build() {
        return colaboradorEntity;
    }
}
