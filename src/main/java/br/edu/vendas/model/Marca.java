package br.edu.vendas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name="tb_marca", uniqueConstraints=@UniqueConstraint(columnNames="nome"))
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Marca {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @EqualsAndHashCode.Include
    private Long id;
    @NotBlank @Size(max=120) @Column(nullable=false, unique=true, length=120)
    private String nome;
    @Column(columnDefinition="text") private String descricao;
    @Column(nullable=false) private Boolean ativo = true;
    @Override public String toString() { return nome; }
}
