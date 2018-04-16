/**
 * A classe SystemVerilogVocabularyExtractor é a classe  pricipal do projeto
 * é há partir dela que é executado todas as partes do projeto.
 */
package systemverilogvocabularyextractor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import handleEventesVocabularyExtractor.HandleEventsComments;

/**
 *
 * @author fc.corporation
 */
public class SystemVerilogVocabularyExtractor {
    private FileAnalyst fileAnalyst;
    private CommentProcessor genericsComments;
    private ClassProcessor classProcessor;
    private ModuleProcessor moduleProcessor;
    private ArrayList<String> projectSource;
    private InterfaceProcessor interfaceProcessor;
    private ModPortProcessor modPortProcessor;
    private PackageProcessor packageProcessor;
    private HandleEventsComments handleEventsComments;
    private boolean commentBlock = false;
    /**
     * O construtor da classe recebe um argumento que é o diretorio do projeto
     * daí é instanciado os objetos que fazem parte do SystemVerilogVocabularyExtractor
     * @param diretorio diretorio do projeto
     */
    public SystemVerilogVocabularyExtractor(String diretorio){
        this.fileAnalyst = new FileAnalyst(diretorio);
        this.genericsComments = new CommentProcessor();
        this.projectSource = fileAnalyst.toStringFiles();
        this.handleEventsComments = new HandleEventsComments(genericsComments);
        this.classProcessor = new ClassProcessor();
        this.moduleProcessor = new ModuleProcessor();
        //this.moduleProcessor = new ModuleProcessor(this.handleEventsComments);
        this.interfaceProcessor = new InterfaceProcessor();
        this.modPortProcessor = new ModPortProcessor();
        this.packageProcessor = new PackageProcessor();
        this.run();
    }
    public void setCommentBlock (boolean isCommentBlock){
        this.commentBlock = isCommentBlock;
    }
    /**
     * O métedo run, é nele que é executado tudo.
     */
    public void run(){
        int index = 0;
        final String[] PARENTESES = {"(", ")"};
        boolean stateParam = false;
        for(;index < this.projectSource.size();index++){
            String sourceLine = this.projectSource.get(index);
            sourceLine = this.withOutComments(sourceLine);
            if(sourceLine.equals(" ") || sourceLine.equals(""))
                continue;
            if (sourceLine.contains(PARENTESES[0]) && !sourceLine.contains(PARENTESES[1]) || stateParam){
                stateParam = true;
                while(stateParam){
                    sourceLine += this.concatGenericParam(this.projectSource.get(index+1), true);
                    if(sourceLine.contains(PARENTESES[1])){
                        stateParam = false;
                    }
                    index += 1;
                }
            }
            this.packageProcessor.setFields(sourceLine);
            this.interfaceProcessor.setFields(sourceLine);
            this.classProcessor.setClassesProperties(sourceLine);
            this.moduleProcessor.setModuleProperties(sourceLine);
        }
    }
    public String toString(){
        String svve = this.classProcessor.toString();
        svve += this.moduleProcessor;
        svve += this.interfaceProcessor;
        svve += this.classProcessor;
        svve += packageProcessor;
        return svve;
    }
    public String toXML(String saveDir, String nameProject){
        FileWriter arquivo;
        String toXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        toXML += this.classProcessor.toXML();
        toXML += this.interfaceProcessor.toXML();
        toXML += this.moduleProcessor.toXML();
        toXML += this.packageProcessor.toXML();
        try {
            arquivo = new FileWriter(new File(saveDir+nameProject+".vxl"));
            arquivo.write(toXML);
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toXML;
    }
    /**
     * O método concatGenericParam concatena parâmetros que estiverem em varias
     * linhas
     * @param sourceLine linha de código original
     * @param sourceLineToConcat linha de código que talvez será concatenada
     * @param permissionConcat se true permite a concatenação, caso contrário não
     * @return a string original (sourceLine) caso não seja dado permissão ou a 
     * concatenação das duas Strings passadas como parâmetro
     */
    private String concatGenericParam(String sourceLineToConcat,
            boolean permissionConcat){
        String concatString = "";
        if(permissionConcat)
            concatString += sourceLineToConcat.trim();
        return concatString;
    }
    /**
     * The method WithOutComments collect all comments where isCommets is true
     * @param sourceLine source line of code
     * @return the sourceLine with out comments
     */
    public String withOutComments (String sourceLine){
        final String[] COMMENTS = {"//", "/*", "*/"};
        String sourceLineWithOutComments = "";
        if (this.commentBlock == true && !sourceLine.contains(COMMENTS[2])){
            //this.genericsComments.setManualComments(sourceLine);
            sourceLineWithOutComments +="";
        }
        else {
            if (sourceLine.contains(COMMENTS[0])){
                sourceLineWithOutComments += sourceLine.substring(0, sourceLine.indexOf(COMMENTS[0]));
                //this.genericsComments.setManualComments(sourceLine.substring(sourceLine.indexOf(COMMENTS[0])));
            }
            else if (sourceLine.contains(COMMENTS[1])){
                sourceLineWithOutComments += sourceLine.substring(0, sourceLine.indexOf(COMMENTS[1]));
                //this.genericsComments.setManualComments(sourceLine.substring(sourceLine.indexOf(COMMENTS[1])));
                this.commentBlock = true;
            }
            if (sourceLine.contains(COMMENTS[2])){
                this.commentBlock = false;
            }
            else {
                sourceLineWithOutComments = sourceLine;
            }
        }
        return sourceLineWithOutComments;
    }
    
    /**
     * O main do projeto
     * @param args argumentos caso o projeto for executado da linha de comando
     * arg[0] endereço do projeto a ser extraido
     * arg[1] diretorio para salvar o arquivo XML
     * arg[2] nome do arquivo XML
     */
    public static void main(String[] args) {
        //String[] args2 = {"/home/ifcardio/projects_/zynq-sandbox-master/","/home/ifcardio/extracteds_projects/","zynq-sandbox-master", "-mem", "-te"};
        long initialTime = System.currentTimeMillis();
        SystemVerilogVocabularyExtractor svve = new SystemVerilogVocabularyExtractor(args[0]);
        long finalTime = System.currentTimeMillis();
        svve.toXML(args[1], args[2]);
        System.gc();
        Runtime rt = Runtime.getRuntime();
        for(String arg: args){
            if (arg.equals("-mem")){
                System.out.println("Used Memory (KB): "+(rt.totalMemory() - rt.freeMemory())/1024);
            }
            if (arg.equals("-te")){
                System.out.println("Execution Time (ms): "+ (finalTime-initialTime));
            }
        }
    }
}