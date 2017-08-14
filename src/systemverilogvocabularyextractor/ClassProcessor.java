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
    private ArrayList<ClassData> csdt;
    private ArrayList<String> UVMsource;
    private VerificationSintax vfs;
    private FileAnalyst fileAnalyst;
    private static final String BEGINCLASS = "class";
    private static final String ENDCLASS = "endclass";
    
    public ClassProcessor(String diretorio){
        super(BEGINCLASS, ENDCLASS);
        this.csdt = new ArrayList<ClassData>();
        this.fileAnalyst = new FileAnalyst(diretorio);
        this.UVMsource = this.fileAnalyst.toStringFiles();
        vfs = new VerificationSintax();
        vfs.setAvlTreeSintax(vfs.setWordsKeys());
    }
    public void print(){
        for(String str: UVMsource){
            System.err.println(str);
        }
    }
    public void setClassesProperties(){
        CommentProcessor comments = new CommentProcessor();
        MethodProcessor methods = new MethodProcessor();
        for(int index=0;index < this.UVMsource.size();index++){
            String line = this.UVMsource.get(index);
            if(comments.isCommentBlock(this.UVMsource.get(index)) && !this.isModule(this.UVMsource.get(index)))
                comments.setComments(this.UVMsource.get(index));
            else if(this.isModule(line)){
                this.setFields(this.UVMsource.get(index), index);
                if(!csdt.isEmpty()){
                    this.csdt.get(csdt.size()-1).setCtpr(comments);
                }
                comments = new CommentProcessor();
            }
            this.setVariableAndCommentlocal(line);
        }
    }
    
    @Override
    public void setFields(String lineOrigin){}
    
    public void setFields(String lineOrigin, int index_UVM) {
        lineOrigin = this.filterAccessMode(lineOrigin);
        lineOrigin = this.filterIndentation(lineOrigin);
        ClassData csdt = new ClassData();
        ArrayList<String> properties = new ArrayList<String>();
        String[] wordsInLine = lineOrigin.split(" ");
        int index=0;
        properties.add(wordsInLine[1]);
        for(;index < wordsInLine.length;index++){
        try{
            if(wordsInLine[index].equals("extends") && index != wordsInLine.length-1){
                properties.add(wordsInLine[index+1]);
                break;
            }
            else{
                String superClass = this.UVMsource.get(index_UVM+1);
                superClass = this.filterIndentation(superClass);
                try{
                superClass = superClass.substring(0, superClass.indexOf(" "));
                }catch(StringIndexOutOfBoundsException sio){}
                properties.add(superClass);
            }
        }catch(ArrayIndexOutOfBoundsException aio){}
        }
        csdt.setName(properties.get(0));
        csdt.setSuperClasse(properties.get(properties.size()-1));
        this.csdt.add(csdt);
    }
    @Override
    public void setVariableAndCommentlocal(String linha) {
        if(beginStruct && !endStruct){
            ClassData classTemp = this.csdt.get(this.csdt.size()-1);
            classTemp.setMdpr(linha);
            classTemp.setTkpr(linha);
            if(!classTemp.getMdpr().isModule() && 
                    !classTemp.getTkpr().isModule())
                classTemp.setFdpr(linha);
        }
        else if(endStruct){
            beginStruct = false;
            endStruct = false;
        }
        
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