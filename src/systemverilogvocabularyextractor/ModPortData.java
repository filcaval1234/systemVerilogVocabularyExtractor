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
    private ParamProcessor paramModport;
    
    public ModPortData(String nome){
        this.nome = nome;
        this.commentsProcesorModPort = new CommentProcessor();
        this.paramModport = new ParamProcessor();
    }

    public CommentProcessor getCommentsProcesorModPort() {
        return commentsProcesorModPort;
    }
    public void setCommentsProcesorModPort(CommentProcessor commentsProcesorModPort) {
        this.commentsProcesorModPort = commentsProcesorModPort;
    }
    public ParamProcessor getParamModport() {
        return paramModport;
    }
    public void setParamModport(ParamProcessor paramModport) {
        this.paramModport = paramModport;
    }
}
