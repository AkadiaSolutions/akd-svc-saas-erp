package br.akd.svc.akadia.modules.erp.compras.models.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_compra")
public class CompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
