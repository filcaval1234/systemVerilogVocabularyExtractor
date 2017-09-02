/**
 * A classe SystemVerilogVocabularyExtractor é a classe  pricipal do projeto
 * é há partir dela que é executado todas as partes do projeto.
 */
package systemverilogvocabularyextractor;

import java.util.ArrayList;

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
    //private PackageProcessor packageProcessor;
    
    /**
     * O construtor da classe recebe um argumento que é o diretorio do projeto
     * daí é instanciado os objetos que fazem parte do SystemVerilogVocabularyExtractor
     * @param diretorio diretorio do projeto
     */
    public SystemVerilogVocabularyExtractor(String diretorio){
        this.fileAnalyst = new FileAnalyst(diretorio);
        this.genericsComments = new CommentProcessor();
        this.projectSource = fileAnalyst.toStringFiles();
        this.classProcessor = new ClassProcessor();
        this.moduleProcessor = new ModuleProcessor();
        this.run();
        //this.packageProcessor = new PackageProcessor();
    }
    /**
     * O métedo run, é nele que é executado tudo.
     */
    public void run(){
        int index = 0;
        for(;index < this.projectSource.size();index++){
            String sourceLine = this.projectSource.get(index);
            this.genericsComments.setComments(sourceLine);
            this.classProcessor.setClassesProperties(sourceLine);
            this.moduleProcessor.setModuleProperties(sourceLine);
            if(this.classProcessor.isModule(sourceLine)){
                if(!sourceLine.contains(";"))
                    this.classProcessor.setSuperClass(this.projectSource.get(index+1));
                this.classProcessor.setClassComents(genericsComments);
                this.genericsComments = new CommentProcessor();
            }
            else if(this.moduleProcessor.isModule(sourceLine)){
                this.moduleProcessor.setModuleComments(genericsComments);
                this.genericsComments = new CommentProcessor();
            }
        }
    }
    public String toString(){
        String svve;// = this.classProcessor.toString();
        svve = this.moduleProcessor.toString();
        //svve += this.classProcessor;
        
        //svve += packageProcessor.toString();
        return svve;
    }
    /**
     * O main do projeto
     * @param args argumentos caso o projeto for executado da linha de comando
     */
    public static void main(String[] args) {
        /*if(args.length != 1){
            System.err.println("built.: java SystemVerilogVocabularyExtractor"
                    + " absolute path your project.");
            System.err.println("ERROR: Unknown absolute path");
            System.exit(-1);
        }
        SystemVerilogVocabularyExtractor svve = new SystemVerilogVocabularyExtractor(args[0]);*/
        //D:\\Nova pasta (2)\\Testes_Extractor\\apb_test
        //D:\\Nova pasta (2)\\Testes_Extractor\\ahb2_uvm_tb
        //D:\\Nova pasta (2)\\Testes_Extractor\\sha3_uvm_tb
        //FileAnalyst fa = new FileAnalyst("D:\\Nova pasta (2)\\arquivostestbenchfelipegonalves");
        //FileAnalyst fa = new FileAnalyst("D:\\Nova pasta (2)\\result (9)");
        //FileAnalyst fa = new FileAnalyst("D:\\Nova pasta (2)\\Testes_Extractor\\apb_test");
        //FileAnalyst fa = new FileAnalyst("D:\\Nova pasta (2)\\Testes_Extractor\\ahb2_uvm_tb");
        //FileAnalyst fa = new FileAnalyst("D:\\Nova pasta (2)\\Testes_Extractor\\ahb_apb_bridge_uvm_tb");
        //FileAnalyst fa = new FileAnalyst("D:\\Nova pasta (2)\\Testes_Extractor\\sha3_uvm_tb");
        //SystemVerilogVocabularyExtractor svve = new SystemVerilogVocabularyExtractor("D:\\Nova pasta (2)\\Testes_Extractor\\sha3_uvm_tb");
        SystemVerilogVocabularyExtractor svve = new SystemVerilogVocabularyExtractor("D:\\Nova pasta (2)\\result (9)");        
        //SystemVerilogVocabularyExtractor svve = new SystemVerilogVocabularyExtractor("D:\\Nova pasta (2)\\arquivostestbenchfelipegonalves");
        System.out.println(svve);
        
    }
}