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
    private int size;
    private ArrayList<ClassData> csdt;
    private VerificationSintax vfs;
    private CommentProcessor commentsFunction;
    private static final String BEGINCLASS = "class";
    private static final String ENDCLASS = "endclass";
    
    public ClassProcessor(){
        super(BEGINCLASS, ENDCLASS);
        this.csdt = new ArrayList<ClassData>();
        this.commentsFunction = new CommentProcessor();
        vfs = new VerificationSintax();
        vfs.setAvlTreeSintax(vfs.setWordsKeys());
    }
    public void setClassComents(CommentProcessor classComments){
        this.csdt.get(size).setCtpr(classComments);
    }
    public void setClassesProperties(String sourceLine){
        if(this.isModule(sourceLine)){
            this.setFields(sourceLine);
            size = this.csdt.size()-1;
        }
        this.setVariableAndCommentlocal(sourceLine);
    }    
    @Override
    public void setFields(String lineOrigin) {
        lineOrigin = this.filterAccessMode(lineOrigin);
        lineOrigin = this.filterIndentation(lineOrigin);
        ClassData csdt = new ClassData();
        ArrayList<String> properties = new ArrayList<String>();
        String[] wordsInLine = lineOrigin.split(" ");
        int index=1;
        properties.add(wordsInLine[1]);
        for(;index < wordsInLine.length;index++){
        try{
            if(wordsInLine[index].equals("extends") && index != wordsInLine.length-1){
                properties.add(wordsInLine[index+1]);
                break;
            }
        }catch(ArrayIndexOutOfBoundsException aio){}
        }
        csdt.setName(properties.get(0));
        if(properties.size()== 2)
            csdt.setSuperClasse(properties.get(properties.size()-1));
        this.csdt.add(csdt);
    }
    @Override
    public void setVariableAndCommentlocal(String linha) {
        if(beginStruct && !endStruct){
            ClassData classTemp = this.csdt.get(this.csdt.size()-1);
            if(this.commentsFunction.isCommentBlock(linha))
                this.commentsFunction.setComments(linha);
            else{
                classTemp.setMdpr(linha);
                classTemp.setTkpr(linha);
                if(!classTemp.getMdpr().isModule() && 
                        !classTemp.getTkpr().isModule())
                    classTemp.setFdpr(linha);
                if(classTemp.getMdpr().isModule(linha)){
                    this.commentsFunction.setBeginComments(false);
                    this.commentsFunction.setEndComments(false);
                    classTemp.getMdpr().getUltimateClass().setCommentLocal(this.commentsFunction);
                    this.commentsFunction = new CommentProcessor();
                }
            }
        }
        else if(endStruct){
            beginStruct = false;
            endStruct = false;
        }
    }
    public void setSuperClass(String linha){
        final String EXTENDS = "extends";
        final int SIZEEXTENDS = EXTENDS.length();
        final char BEGINPARAM = 35; //in ascii 35 = #
        final String STRINGBEGINPARAM = "#";
        String superClass = null;
        if(linha.contains(EXTENDS)){
            //if(linha.contains(STRINGBEGINPARAM))
                //superClass = linha.substring(linha.indexOf(EXTENDS)+SIZEEXTENDS, 
                    //linha.indexOf(BEGINPARAM));
            //else
                superClass = linha.substring(linha.indexOf(EXTENDS)+SIZEEXTENDS);
        }
        this.csdt.get(this.size).setSuperClasse(superClass);
    }
    public String getSuperClass(){
        return this.csdt.get(size).getSuperClass();
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