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
    VerificationSintax vfs;
    private static final String BEGINCLASS = "class";
    private static final String ENDCLASS = "endclass";
    
    public ClassProcessor(){
        super(BEGINCLASS, ENDCLASS);
        this.csdt = new ArrayList<ClassData>();
        vfs = new VerificationSintax();
        vfs.setAvlTreeSintax(vfs.setWordsKeys());
    }
    @Override
    public void setFields(String lineOrigin) {
        lineOrigin = this.filterAccessMode(lineOrigin);
        lineOrigin = this.filterIndentation(lineOrigin);
        ClassData csdt = new ClassData();;
        if(this.isModule(lineOrigin)){
            String[] wordsInLine = lineOrigin.split(" ");
            int index=0;
            for(;index < wordsInLine.length;index++){
                if(vfs.sytemVerilogSintax(wordsInLine[index]) || wordsInLine[index].contains("#"))
                    index += 1;
                csdt.setName(wordsInLine[index]);
            }
        }
    }
    @Override
    public void setVariableAndCommentlocal(String linha) {
        
    }
    public String toString(){
        return "";
    }
}