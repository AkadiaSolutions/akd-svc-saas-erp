package br.akd.svc.akadia.models.dto.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.ColaboradorDto;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModeloContratacaoEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModeloTrabalhoEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.StatusColaboradorEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TipoOcupacaoEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

class ColaboradorDtoBuilder {

    ColaboradorDtoBuilder() {
    }

    ColaboradorDto colaboradorDto;

    public static ColaboradorDtoBuilder builder() {
        ColaboradorDtoBuilder builder = new ColaboradorDtoBuilder();
        builder.colaboradorDto = new ColaboradorDto();
        builder.colaboradorDto.setId(1L);
        builder.colaboradorDto.setDataCadastro(LocalDate.of(2023, 2, 13).toString());
        builder.colaboradorDto.setHoraCadastro(LocalTime.of(10, 44).toString());
        builder.colaboradorDto.setFotoPerfil(new byte[]{});
        builder.colaboradorDto.setNome("João da Silva");
        builder.colaboradorDto.setDataNascimento("2021-04-11");
        builder.colaboradorDto.setEmail("joaosilva@gmail.com");
        builder.colaboradorDto.setCpfCnpj("12345678910");
        builder.colaboradorDto.setAtivo(true);
        builder.colaboradorDto.setExcluido(false);
        builder.colaboradorDto.setSalario(2000.0);
        builder.colaboradorDto.setEntradaEmpresa(LocalDate.of(2023, 2, 13).toString());
        builder.colaboradorDto.setSaidaEmpresa(null);
        builder.colaboradorDto.setContratoContratacao(new byte[]{});
        builder.colaboradorDto.setOcupacao("Técnico Interno");
        builder.colaboradorDto.setTipoOcupacaoEnum(TipoOcupacaoEnum.TECNICO_INTERNO);
        builder.colaboradorDto.setModeloContratacaoEnum(ModeloContratacaoEnum.CLT);
        builder.colaboradorDto.setModeloTrabalhoEnum(ModeloTrabalhoEnum.PRESENCIAL);
        builder.colaboradorDto.setStatusColaboradorEnum(StatusColaboradorEnum.ATIVO);
        builder.colaboradorDto.setAcessoSistema(null);
        builder.colaboradorDto.setConfiguracaoPerfil(null);
        builder.colaboradorDto.setEndereco(null);
        builder.colaboradorDto.setTelefone(null);
        builder.colaboradorDto.setExpediente(null);
        builder.colaboradorDto.setDispensa(null);
        builder.colaboradorDto.setPontos(new ArrayList<>());
        builder.colaboradorDto.setHistoricoFerias(new ArrayList<>());
        builder.colaboradorDto.setAdvertencias(new ArrayList<>());
        builder.colaboradorDto.setParentescos(new ArrayList<>());
        builder.colaboradorDto.setEmpresa(null);
        return builder;
    }

    public ColaboradorDto build() {
        return colaboradorDto;
    }
}
