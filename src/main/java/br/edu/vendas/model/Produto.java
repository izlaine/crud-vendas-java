package br.edu.vendas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity @Table(name="tb_produto")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Produto {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @EqualsAndHashCode.Include
    private Long id;
    @NotBlank @Size(max=180) @Column(nullable=false,length=180) private String nome;
    @Column(columnDefinition="text") private String descricao;
    @NotNull @DecimalMin(value="0.01") @Column(nullable=false, precision=15, scale=2) private BigDecimal preco;
    @NotNull @Min(0) @Column(nullable=false) private Integer quantidadeEstoque;
    @NotNull @ManyToOne(fetch=FetchType.EAGER, optional=false) @JoinColumn(name="marca_id", nullable=false) private Marca marca;
    @NotNull @ManyToOne(fetch=FetchType.EAGER, optional=false) @JoinColumn(name="categoria_id", nullable=false) private Categoria categoria;
    @Size(max=500) private String caminhoImagem;
}
