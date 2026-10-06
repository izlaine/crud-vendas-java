package br.edu.vendas.controller;
import br.edu.vendas.model.*; import br.edu.vendas.service.*; import jakarta.annotation.PostConstruct; import jakarta.faces.view.ViewScoped; import jakarta.inject.Inject; import jakarta.inject.Named; import org.primefaces.event.FileUploadEvent; import org.primefaces.model.file.UploadedFile; import java.io.*; import java.nio.file.*; import java.util.*;
@Named @ViewScoped public class ProdutoController extends BaseController {
 @Inject private ProdutoService service; @Inject private MarcaService marcaService; @Inject private CategoriaService categoriaService;
 private Produto produto; private List<Produto> produtos,marcasDummy; private List<Marca> marcas; private List<Categoria> categorias; private boolean editando;
 @PostConstruct public void init(){listar(); carregarOpcoes(); novo();}
 public void listar(){produtos=service.listar();}
 public void carregarOpcoes(){marcas=marcaService.listar();categorias=categoriaService.listar();}
 public void novo(){produto=new Produto();produto.setQuantidadeEstoque(0);editando=false;}
 public void editar(Produto p){produto=p;editando=true;}
 public void salvar(){try{if(produto.getMarca()==null||produto.getCategoria()==null)throw new IllegalArgumentException("Informe marca e categoria.");service.salvar(produto);info("Produto salvo com sucesso.");listar();carregarOpcoes();novo();}catch(Exception e){error(e.getMessage()!=null?e.getMessage():"Não foi possível salvar o produto.");}}
 public void excluir(Produto p){try{service.excluir(p);info("Produto excluído.");listar();}catch(Exception e){error("Não foi possível excluir o produto.");}}
 public void upload(FileUploadEvent event){UploadedFile file=event.getFile(); if(file==null)return; String nome=file.getFileName().toLowerCase(Locale.ROOT); String contentType = file.getContentType() == null ? "" : file.getContentType().toLowerCase(Locale.ROOT); if(!(nome.endsWith(".jpg")||nome.endsWith(".jpeg")||nome.endsWith(".png")||nome.endsWith(".webp"))){error("Formato inválido. Use JPG, PNG ou WEBP.");return;} if(!(contentType.equals("image/jpeg")||contentType.equals("image/png")||contentType.equals("image/webp"))){error("O arquivo enviado não é uma imagem JPG, PNG ou WEBP válida.");return;} if(file.getSize()>5_000_000){error("A imagem deve ter no máximo 5 MB.");return;} try{String ext=nome.substring(nome.lastIndexOf('.'));String arquivo=UUID.randomUUID()+ext;String realPath=jakarta.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/uploads"); if(realPath==null) throw new IOException("Diretório de upload indisponível"); Path dir=Paths.get(realPath); Files.createDirectories(dir); Files.write(dir.resolve(arquivo),file.getContent()); produto.setCaminhoImagem("/resources/uploads/"+arquivo);info("Imagem carregada.");}catch(IOException e){error("Falha ao gravar a imagem.");}}
 public String getImagemPreview(){return produto!=null?produto.getCaminhoImagem():null;}
 public String getIndentacao(Categoria c){int nivel=0;Categoria p=c.getCategoriaPai();while(p!=null&&nivel<50){nivel++;p=p.getCategoriaPai();}return "-- ".repeat(nivel)+c.getNome();}
 public Produto getProduto(){return produto;} public List<Produto> getProdutos(){return produtos;} public List<Marca> getMarcas(){return marcas;} public List<Categoria> getCategorias(){return categorias;} public boolean isEditando(){return editando;}
}
