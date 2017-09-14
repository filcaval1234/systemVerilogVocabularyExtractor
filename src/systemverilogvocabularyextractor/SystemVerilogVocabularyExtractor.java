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
    private InterfaceProcessor interfaceProcessor;
    private ModPortProcessor modPort;
    private PackageProcessor packageProcessor;
    //*********************************inserir testes aqui******************************
        FieldProcessor fd = new FieldProcessor();
    //*********************************fim da inserção**********************************
    
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
        this.interfaceProcessor = new InterfaceProcessor();
        this.modPort = new ModPortProcessor();
        this.packageProcessor = new PackageProcessor();
        this.run();
        
    }
    /**
     * O métedo run, é nele que é executado tudo.
     */
    public void run(){
        int index = 0;
        for(;index < this.projectSource.size();index++){
            String sourceLine = this.projectSource.get(index);
            if(sourceLine.equals(" ") || sourceLine.equals(""))
                continue;
            //fd.setListVariaveis(sourceLine);
            this.packageProcessor.setFields(sourceLine);
            this.interfaceProcessor.setFields(sourceLine);
            this.genericsComments.setComments(sourceLine);
            this.classProcessor.setClassesProperties(sourceLine);
            this.moduleProcessor.setModuleProperties(sourceLine);
            //analisar este trecho de codigo...
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
        
        String svve = this.classProcessor.toString();
        svve += this.moduleProcessor;
        svve += this.interfaceProcessor;
        svve += this.classProcessor;
        svve += packageProcessor;
        
        //String svve = fd.toString();
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
        //SystemVerilogVocabularyExtractor svve = new SystemVerilogVocabularyExtractor("D:\\Nova pasta (2)\\result (9)");        
        SystemVerilogVocabularyExtractor svve = new SystemVerilogVocabularyExtractor("D:\\Nova pasta (2)\\arquivostestbenchfelipegonalves");
        //SystemVerilogVocabularyExtractor svve = new SystemVerilogVocabularyExtractor("D:\\Nova pasta (2)\\result (9)\\diff_pkg\\testeClass");
        
        System.out.println(svve);
        
    }
}