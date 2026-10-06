package br.edu.vendas.controller;
import br.edu.vendas.model.Categoria; import br.edu.vendas.service.CategoriaService; import jakarta.annotation.PostConstruct; import jakarta.faces.view.ViewScoped; import jakarta.inject.Inject; import jakarta.inject.Named; import java.util.*;
@Named @ViewScoped public class CategoriaController extends BaseController {
 @Inject private CategoriaService service; private Categoria categoria; private List<Categoria> categorias; private List<Categoria> categoriasPais; private boolean editando;
 @PostConstruct public void init(){listar();novo();}
 public void listar(){categorias=service.listar(); categoriasPais=new ArrayList<>(categorias);}
 public void novo(){categoria=new Categoria();editando=false;}
 public void editar(Categoria c){categoria=c;editando=true;}
 public void salvar(){try{service.salvar(categoria);info("Categoria salva com sucesso.");listar();novo();}catch(Exception e){error(e.getMessage()!=null?e.getMessage():"Não foi possível salvar a categoria.");}}
 public void excluir(Categoria c){try{service.excluir(c);info("Categoria excluída.");listar();}catch(Exception e){error(e.getMessage()!=null?e.getMessage():"Não foi possível excluir a categoria.");}}
 public String getIndentacao(Categoria c){int nivel=0; Categoria p=c.getCategoriaPai(); while(p!=null && nivel<50){nivel++; p=p.getCategoriaPai();} return "-- ".repeat(nivel)+c.getNome();}
 public Categoria getCategoria(){return categoria;} public List<Categoria> getCategorias(){return categorias;} public List<Categoria> getCategoriasPais(){return categoriasPais;} public boolean isEditando(){return editando;}
}
