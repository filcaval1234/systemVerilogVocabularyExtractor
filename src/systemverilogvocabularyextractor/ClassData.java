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
    public String getSuperClass(){
        return this.superclass;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSuperClasse(String superClasse){
        this.superclass = superClasse;
    }
    public void setFdpr(String linha) {
        this.fdpr.setListVariaveis(linha);
    }
    public void setMdpr(String linha){
        this.mdpr.setFields(linha);
    }
    public void setMdpr(MethodProcessor mdpr) {
        this.mdpr = mdpr; 
    }
    public void setTkpr(String linha) {
        this.tkpr.setFields(linha);
    }
    public void setCtpr(CommentProcessor comments) {
        this.ctpr = comments;
    }
    public void setPmpr(String linha){
        this.pmpr.setParametersFormal(linha);
    }
    public String toString(){
           String classData = "class :"+this.name+" classExtends :"+this.superclass+"\n";
           classData += "----------------ClassFields----------------\n";
           classData += this.fdpr+"\n";
           classData += "----------------ClassComments----------------\n";
           classData += this.ctpr+"\n";
           classData += "----------------ClassMethods------------------\n";
           classData += this.mdpr+"\n";
           classData += "------------Classtasks---------------------\n";
           classData += this.tkpr;
           return classData;
    }            
}