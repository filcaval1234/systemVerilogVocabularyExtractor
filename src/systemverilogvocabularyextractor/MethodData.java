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
public class MethodData {
    protected ParamProcessor param;
    protected String name;
    private String retorno;
    protected CommentProcessor commentsFunction;
    protected FieldProcessor localField;
    
    //private EnumProcessor enums;
    
    public MethodData(String name, String retorno){
        this.name = name;
        this.retorno = retorno;
        this.commentsFunction = new CommentProcessor();
        this.localField = new FieldProcessor();
        this.param = new ParamProcessor();
    }
    public MethodData(String name){
        this.name = name;
        this.commentsFunction = new CommentProcessor();
        this.localField = new FieldProcessor();
        this.param = new ParamProcessor();
    }
    public void setParam(String linha){
        param.setParametersFormal(linha);
    }
    public void setLocalField(String linha){
        localField.setListVariaveis(linha);
    }
    public void setCommentLocal(String linha){
        commentsFunction.setComments(linha);
    }
    public String toString(){
        String metodo ="nome da função: "+this.name+" retorno: "+ this.retorno+"\n";
        metodo += "----------------parametros----------------------------+\n";
        metodo += this.param;
        metodo += "--------------variaveis Locais----------------------\n";
        metodo += this.localField+"\n";
        metodo += "--------------Comentarios----------------------\n";
        metodo += this.commentsFunction;
        metodo += "-----------------------------------------------------\n";
        return metodo;
    }
}