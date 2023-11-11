package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.mocks;

import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.dto.response.ColaboradorResponse;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.ModeloContratacaoEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.ModeloTrabalhoEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.StatusColaboradorEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.TipoOcupacaoEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ColaboradorResponseBuilder {

    ColaboradorResponseBuilder() {
    }

    ColaboradorResponse colaboradorResponse;

    public static ColaboradorResponseBuilder builder() {
        ColaboradorResponseBuilder builder = new ColaboradorResponseBuilder();
        builder.colaboradorResponse = new ColaboradorResponse();
        builder.colaboradorResponse.setId(1L);
        builder.colaboradorResponse.setDataCadastro(LocalDate.of(2023, 2, 13).toString());
        builder.colaboradorResponse.setHoraCadastro(LocalTime.of(10, 44).toString());
        builder.colaboradorResponse.setMatricula("123456");
        builder.colaboradorResponse.setNome("João da Silva");
        builder.colaboradorResponse.setDataNascimento("2021-04-11");
        builder.colaboradorResponse.setEmail("joaosilva@gmail.com");
        builder.colaboradorResponse.setCpfCnpj("12345678910");
        builder.colaboradorResponse.setSalario(2000.0);
        builder.colaboradorResponse.setEntradaEmpresa(LocalDate.of(2023, 2, 13).toString());
        builder.colaboradorResponse.setSaidaEmpresa(null);
        builder.colaboradorResponse.setOcupacao("Técnico Interno");
        builder.colaboradorResponse.setTipoOcupacaoEnum(TipoOcupacaoEnum.TECNICO_INTERNO);
        builder.colaboradorResponse.setModeloContratacaoEnum(ModeloContratacaoEnum.CLT);
        builder.colaboradorResponse.setModeloTrabalhoEnum(ModeloTrabalhoEnum.PRESENCIAL);
        builder.colaboradorResponse.setStatusColaboradorEnum(StatusColaboradorEnum.ATIVO);
        builder.colaboradorResponse.setFotoPerfil(null);
        builder.colaboradorResponse.setContratoContratacao(null);
        builder.colaboradorResponse.setExclusao(null);
        builder.colaboradorResponse.setAcessoSistema(null);
        builder.colaboradorResponse.setConfiguracaoPerfil(null);
        builder.colaboradorResponse.setEndereco(null);
        builder.colaboradorResponse.setTelefone(null);
        builder.colaboradorResponse.setExpediente(null);
        builder.colaboradorResponse.setDispensa(null);
        builder.colaboradorResponse.setPontos(new ArrayList<>());
        builder.colaboradorResponse.setHistoricoFerias(new ArrayList<>());
        builder.colaboradorResponse.setAdvertencias(new ArrayList<>());
        return builder;
    }

    public ColaboradorResponse build() {
        return colaboradorResponse;
    }

}
