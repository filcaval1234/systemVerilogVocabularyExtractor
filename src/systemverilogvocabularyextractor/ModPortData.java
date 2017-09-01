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
public class ModPortData {
    private String nome;
    private CommentProcessor commentsProcesorModPort;
    private FieldProcessor fieldsModPort;
    
    public ModPortData(String nome){
        this.nome = nome;
        this.commentsProcesorModPort = new CommentProcessor();
        this.fieldsModPort = new FieldProcessor();
    }

    public CommentProcessor getCommentsProcesorModPort() {
        return commentsProcesorModPort;
    }
    public void setCommentsProcesorModPort(CommentProcessor commentsProcesorModPort) {
        this.commentsProcesorModPort = commentsProcesorModPort;
    }
    public FieldProcessor getFieldsModPort() {
        return fieldsModPort;
    }
    public void setFieldsModPort(FieldProcessor fieldsModPort) {
        this.fieldsModPort = fieldsModPort;
    }
    
    public String toString(){
        String modPort = "name modPort: "+ this.nome+"\n";
        modPort += "---------param modPort----------\n";
        modPort += this.fieldsModPort;
        return modPort;
    }
}