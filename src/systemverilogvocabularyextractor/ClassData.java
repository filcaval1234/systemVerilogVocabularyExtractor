/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemverilogvocabularyextractor;

/**
 *
 * @author fc.corporation
 */
public class ClassData {
    private String name;
    private String superclass;
    private FieldProcessor fdpr;
    private MethodProcessor mdpr;
    private TaskProcessor tkpr;
    private CommentProcessor ctpr;
    private ParamProcessor pmpr;
    
    ClassData(String name, String superclass){
        this.name = name;
        this.superclass = superclass;
        this.fdpr = new FieldProcessor();
        this.mdpr = new MethodProcessor();
        this.tkpr = new TaskProcessor();
        this.ctpr = new CommentProcessor();
        this.pmpr = new ParamProcessor();
    }
    ClassData(){
        this.fdpr = new FieldProcessor();
        this.mdpr = new MethodProcessor();
        this.tkpr = new TaskProcessor();
        this.ctpr = new CommentProcessor();
        this.pmpr = new ParamProcessor();
    }
    public FieldProcessor getFdpr() {
        return fdpr;
    }
    public MethodProcessor getMdpr() {
        return mdpr;
    }
    public TaskProcessor getTkpr() {
        return tkpr;
    }
    public CommentProcessor getCtpr() {
        return ctpr;
    }
    public ParamProcessor getPmpr() {
        return pmpr;
    }
    public void setName(String nome){
        this.name = name;
    }
    public void setFdpr(String linha) {
        this.fdpr.setListVariaveis(linha);
    }
    public void setMdpr(String linha) {
        this.mdpr.setFields(linha);
    }
    public void setTkpr(String linha) {
        this.tkpr.setFields(linha);
    }
    public void setCtpr(String linha) {
        this.ctpr.setComments(linha);
    }
    public void setPmpr(String linha){
        this.pmpr.setParametersFormal(linha);
    }
}