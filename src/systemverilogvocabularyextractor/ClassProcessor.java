/**
 * A classe ClassProcessor tem atributos e métodos suficientes para todo o processamento
 * há partir de linhas de codigos montar tudo de relativo a uma classe de 
 * systemVerilog.
 */
package systemverilogvocabularyextractor;

import java.util.ArrayList;

/**
 *
 * @author fc.corporation
 */
public class ClassProcessor extends Modulo{
    private int size;
    private ArrayList<ClassData> arrayClassData;
    private VerificationSintax vfs;
    private CommentProcessor commentsFunction;
    private static final String BEGINCLASS = "class";
    private static final String ENDCLASS = "endclass";
    
    /**
     * O construtor da classe que inicializa todos os campos e chama o método
     * setAvlTreeSintax de vfs passando como argumento o retorno da função
     * setWordsKeys também de vfs.
     */
    public ClassProcessor(){
        super(BEGINCLASS, ENDCLASS);
        this.arrayClassData = new ArrayList<ClassData>();
        this.commentsFunction = new CommentProcessor();
        vfs = new VerificationSintax();
        vfs.setAvlTreeSintax(vfs.setWordsKeys());
    }
    /**
     * O método setClassComents recebe um argumento do tipo CommentProcessor
     * e então passa sua referencia para o ultimo elemento de arrayClassData
     * @param classComments referencia do processador de commentarios 
     */
    public void setClassComents(CommentProcessor classComments){
        this.arrayClassData.get(size).setCommentProcessorClassData(classComments);
    }
    /**
     * O método setClassesProperties verifica se inicio uma classe iniciando-a
     * chama o método setFields e apos isso incrementa o tamanho
     * @param sourceLine linha de codigo que será analisada
     */
    public void setClassesProperties(String sourceLine){
        if(this.isModule(sourceLine)){
            this.setFields(sourceLine);
            size = this.arrayClassData.size()-1;
        }
        this.setVariableAndCommentlocal(sourceLine);
    }
    /**
     * 
     * @param lineOrigin 
     */
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
        this.arrayClassData.add(csdt);
    }
    @Override
    public void setVariableAndCommentlocal(String linha) {
        if(beginStruct && !endStruct){
            ClassData classTemp = this.arrayClassData.get(this.arrayClassData.size()-1);
            if(this.commentsFunction.isCommentBlock(linha))
                this.commentsFunction.setComments(linha);
            else{
                classTemp.setMethodProcessorClassData(linha);
                classTemp.setTaskProcessorClassData(linha);
                if(!classTemp.getMethodProcessorClassData().isModule() && 
                        !classTemp.getTaskProcessorClassData().isModule())
                    classTemp.setFieldProcessorClassData(linha);
                if(classTemp.getMethodProcessorClassData().isModule(linha)){
                    this.commentsFunction.setBeginComments(false);
                    this.commentsFunction.setEndComments(false);
                    classTemp.getMethodProcessorClassData().getUltimateMethod().setCommentLocal(this.commentsFunction);
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
        linha = this.filterIndentation(linha);
        final String EXTENDS = "extends";
        final int SIZEEXTENDS = EXTENDS.length();
        final char BEGINPARAM = 35; //in ascii 35 = #
        final String STRINGBEGINPARAM = "#";
        String superClass = null;
        if(linha.contains(EXTENDS)){
            superClass = linha.substring(linha.indexOf(EXTENDS)+SIZEEXTENDS);
        }
        else superClass = linha.substring(0, linha.indexOf(" "));
        this.arrayClassData.get(this.size).setSuperClasse(superClass);
    }
    public String getSuperClass(){
        return this.arrayClassData.get(size).getSuperClass();
    }
    public String toString(){
        String classProc = "";
        for(ClassData str: this.arrayClassData){
            classProc += str+"\n";
        }
        return classProc;
    }
}