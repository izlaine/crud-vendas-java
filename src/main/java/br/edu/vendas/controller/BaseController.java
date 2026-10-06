package br.edu.vendas.controller;
import jakarta.faces.application.FacesMessage; import jakarta.faces.context.FacesContext; import java.io.Serializable;
public abstract class BaseController implements Serializable {
 protected void info(String msg){message(FacesMessage.SEVERITY_INFO,"Sucesso",msg);} protected void warn(String msg){message(FacesMessage.SEVERITY_WARN,"Atenção",msg);} protected void error(String msg){message(FacesMessage.SEVERITY_ERROR,"Erro",msg);}
 private void message(FacesMessage.Severity s,String t,String d){FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(s,t,d));}
}
