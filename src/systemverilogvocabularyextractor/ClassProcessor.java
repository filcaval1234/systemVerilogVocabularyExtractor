/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemverilogvocabularyextractor;

import java.util.ArrayList;

/**
 *
 * @author fc.corporation
 */
public class ClassProcessor extends Modulo{
    private MethodProcessor mdpr;
    private TaskProcessor tkpr;
    private FieldProcessor fields;
    private CommentProcessor comments;
    
    public ClassProcessor(){
        super("class", "endclass");
        mdpr = new MethodProcessor();
        tkpr = new TaskProcessor();
        fields = new FieldProcessor();
        comments = new CommentProcessor();
    }
    @Override
    public void setFields(String originalLinha) {
        
    }

    @Override
    public void setVariableAndCommentlocal(String linha) {
        
    }
}
