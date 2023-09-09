package br.akd.svc.akadia.models.dto.sistema.produtos.request;

import lombok.*;

import javax.persistence.Entity;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaProdutoRequest {
    private String nome;
    private String descricao;
}
