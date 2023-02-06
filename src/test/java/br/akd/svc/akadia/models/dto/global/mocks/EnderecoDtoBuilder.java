package br.akd.svc.akadia.models.dto.global.mocks;

import br.akd.svc.akadia.models.dto.global.EnderecoDto;
import br.akd.svc.akadia.models.enums.global.EstadoEnum;

public class EnderecoDtoBuilder {

    EnderecoDtoBuilder(){}
    EnderecoDto enderecoDto;

    public static EnderecoDtoBuilder builder() {
        EnderecoDtoBuilder builder = new EnderecoDtoBuilder();
        builder.enderecoDto = new EnderecoDto();
        builder.enderecoDto.setId(1L);
        builder.enderecoDto.setLogradouro("Avenida Coronel Manuel Py");
        builder.enderecoDto.setNumero(583);
        builder.enderecoDto.setCodigoPostal("02442-090");
        builder.enderecoDto.setBairro("Lauzane Paulista");
        builder.enderecoDto.setCidade("São Paulo");
        builder.enderecoDto.setComplemento("Casa 4");
        builder.enderecoDto.setEstadoEnum(EstadoEnum.SP);
        return builder;
    }

    public EnderecoDto build() {
        return enderecoDto;
    }

}
