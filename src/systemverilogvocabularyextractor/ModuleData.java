/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemverilogvocabularyextractor;

/**
 *
 * @author fc.corporation
 *      All rights reserveds.
 */
public class ModuleData {
    private String nome;
    private MethodProcessor mdpr;
    private FieldProcessor fdpr;
    private CommentProcessor ctpr;
    private static final String BEGINMODULE = "module";
    private static final String ENDMODULE = "endmodule";
    //private EnumProcessor empr;
    //private InterfaceProcessor iepr;
    
    public ModuleData(String nome){
        this.nome = nome;
        this.mdpr = new MethodProcessor();
        this.fdpr = new FieldProcessor();
        this.ctpr = new CommentProcessor();
    }
    public MethodProcessor getMdpr() {
        return mdpr;
    }
    public void setMdpr(MethodProcessor mdpr) {
        this.mdpr = mdpr;
    }
    public FieldProcessor getFdpr() {
        return fdpr;
    }
    public void setFdpr(FieldProcessor fdpr) {
        this.fdpr = fdpr;
    }
    public CommentProcessor getCtpr() {
        return ctpr;
    }
    public void setCtpr(CommentProcessor ctpr) {
        this.ctpr = ctpr;
    }
}