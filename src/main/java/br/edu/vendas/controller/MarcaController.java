package br.edu.vendas.controller;
import br.edu.vendas.model.Marca; import br.edu.vendas.service.MarcaService; import jakarta.annotation.PostConstruct; import jakarta.faces.view.ViewScoped; import jakarta.inject.Inject; import jakarta.inject.Named; import java.util.List;
@Named @ViewScoped public class MarcaController extends BaseController {
 @Inject private MarcaService service; private Marca marca; private List<Marca> marcas; private boolean editando;
 @PostConstruct public void init(){listar(); novo();}
 public void listar(){marcas=service.listar();}
 public void novo(){marca=new Marca(); marca.setAtivo(true); editando=false;}
 public void editar(Marca m){marca=m;editando=true;}
 public void salvar(){try{service.salvar(marca);info("Marca salva com sucesso.");listar();novo();}catch(Exception e){error(e.getMessage()!=null?e.getMessage():"Não foi possível salvar a marca.");}}
 public void excluir(Marca m){try{service.excluir(m);info("Marca excluída.");listar();}catch(Exception e){error("Não foi possível excluir a marca.");}}
 public Marca getMarca(){return marca;} public List<Marca> getMarcas(){return marcas;} public boolean isEditando(){return editando;}
}
