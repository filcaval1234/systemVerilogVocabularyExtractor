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
public class InterfaceData {
    private String nome;
    private CommentProcessor commentsProcessorInterface;
    private FieldProcessor fieldProcessorInterface;
    private ParamProcessor paramProcessorInterface;
    
    public InterfaceData(String nome){
        this.nome = nome;
        this.commentsProcessorInterface = new CommentProcessor();
        this.fieldProcessorInterface = new FieldProcessor();
        this.paramProcessorInterface = new ParamProcessor();
    }

    public CommentProcessor getCommentsProcessorInterface() {
        return commentsProcessorInterface;
    }

    public void setCommentsProcessorInterface(CommentProcessor commentsProcessorInterface) {
        this.commentsProcessorInterface = commentsProcessorInterface;
    }

    public FieldProcessor getFieldProcessorInterface() {
        return fieldProcessorInterface;
    }

    public void setFieldProcessorInterface(FieldProcessor fieldProcessorInterface) {
        this.fieldProcessorInterface = fieldProcessorInterface;
    }

    public ParamProcessor getParamProcessorInterface() {
        return paramProcessorInterface;
    }

    public void setParamProcessorInterface(ParamProcessor paramProcessorInterface) {
        this.paramProcessorInterface = paramProcessorInterface;
    }
}
