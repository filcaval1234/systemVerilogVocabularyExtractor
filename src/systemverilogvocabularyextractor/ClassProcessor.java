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
    ArrayList<ClassData> csdt;
    private static final String BEGINCLASS = "class";
    private static final String ENDCLASS = "endclass";
    public ClassProcessor(){
        super(BEGINCLASS, ENDCLASS);
        this.csdt = new ArrayList<ClassData>();
    }
    @Override
    public void setFields(String lineOrigin) {
        lineOrigin = this.filterAccessMode(lineOrigin);
        lineOrigin = this.filterIndentation(lineOrigin);
        ClassData csdt;
        if(this.isModule(lineOrigin)){
            
        }
        
    }
    @Override
    public void setVariableAndCommentlocal(String linha) {
        
    }
    public String toString(){
        return "";
    }
}
