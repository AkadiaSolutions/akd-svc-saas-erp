package br.akd.svc.akadia.models.dto.global;

import br.akd.svc.akadia.models.dto.global.mocks.EnderecoDtoBuilder;
import br.akd.svc.akadia.models.enums.global.EstadoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("DTO: Endereco")
class EnderecoDtoTest {
    @Test
    @DisplayName("Deve testar dataBuilder")
    void deveTestarDataBuilder() {
        Assertions.assertEquals(
                "EnderecoDto(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, " +
                        "codigoPostal=02442-090, cidade=São Paulo, complemento=Casa 4, estado=SP)",
                EnderecoDtoBuilder.builder().build().toString()
        );
    }

    @Test
    @DisplayName("Deve testar @AllArgsConstructor")
    void deveTestarAllArgsConstructor() {
        EnderecoDto enderecoDto = new EnderecoDto(
                1L,
                "Rua 9",
                544,
                "Lauzane Paulista",
                "02442-050",
                "São Paulo",
                "Casa 4",
                EstadoEnum.SP
        );
        Assertions.assertEquals(
                "EnderecoDto(id=1, logradouro=Rua 9, numero=544, bairro=Lauzane Paulista, " +
                        "codigoPostal=02442-050, cidade=São Paulo, complemento=Casa 4, estado=SP)",
                enderecoDto.toString()
        );
    }

    @Test
    @DisplayName("Deve testar @Builder")
    void deveTestarBuilder() {
        EnderecoDto enderecoDto = EnderecoDto.builder()
                .id(1L)
                .logradouro("Rua Said Saad")
                .numero(145)
                .bairro("Lauzane Paulista")
                .codigoPostal("02324-312")
                .cidade("São Paulo")
                .estado(EstadoEnum.SP)
                .build();

        Assertions.assertEquals(
                "EnderecoDto(id=1, logradouro=Rua Said Saad, numero=145, bairro=Lauzane Paulista, " +
                        "codigoPostal=02324-312, cidade=São Paulo, complemento=null, estado=SP)",
                enderecoDto.toString()
        );
    }
}
