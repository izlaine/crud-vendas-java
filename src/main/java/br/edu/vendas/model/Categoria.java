package br.edu.vendas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="tb_categoria")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Categoria {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @EqualsAndHashCode.Include
    private Long id;
    @NotBlank @Size(max=120) @Column(nullable=false, length=120) private String nome;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="categoria_id") private Categoria categoriaPai;
    @OneToMany(mappedBy="categoriaPai", cascade=CascadeType.ALL, orphanRemoval=false) private List<Categoria> subcategorias = new ArrayList<>();
    public String getCaminhoHierarquico() {
        return categoriaPai == null ? nome : categoriaPai.getCaminhoHierarquico() + " / " + nome;
    }
    @Override public String toString() { return getCaminhoHierarquico(); }
}
