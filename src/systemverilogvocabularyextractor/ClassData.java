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
    
    ClassData(String name, String superclass){
        this.name = name;
        this.superclass = superclass;
        this.fdpr = new FieldProcessor();
        this.mdpr = new MethodProcessor();
        this.tkpr = new TaskProcessor();
        this.ctpr = new CommentProcessor();
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
}
