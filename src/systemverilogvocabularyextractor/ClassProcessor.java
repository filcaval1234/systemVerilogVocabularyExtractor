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
        ClassData csdt = new ClassData();
        ArrayList<String> properties = new ArrayList<String>();
        if(this.isModule(lineOrigin)){
            String[] wordsInLine = lineOrigin.split(" ");
            int index=0;
            properties.add(wordsInLine[1]);
            for(;index < wordsInLine.length;index++){
            try{
                if(wordsInLine[index].equals("extends")){
                    properties.add(wordsInLine[index+1]);
                    break;
                }
            }catch(ArrayIndexOutOfBoundsException aio){
            }
            }
            csdt.setName(properties.get(0));
            csdt.setSuperClasse(properties.get(properties.size()-1));
            this.csdt.add(csdt);
        }
    }
    @Override
    public void setVariableAndCommentlocal(String linha) {
        
    }
    public void filterParameter(String linha){
        linha.subSequence(linha.indexOf("#"), linha.indexOf(")"));
    }
    public String toString(){
        String classProc = "";
        for(ClassData str: this.csdt){
            classProc += str+"\n";
        }
        return classProc;
    }
}