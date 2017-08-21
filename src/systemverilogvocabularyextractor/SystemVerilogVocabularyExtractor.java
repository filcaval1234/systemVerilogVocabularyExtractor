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
public class SystemVerilogVocabularyExtractor {
    private FileAnalyst fileAnalyst;
    private CommentProcessor genericsComments;
    private ClassProcessor classProcessor;
    private ModuleProcessor moduleProcessor;
    private ArrayList<String> projectSource;
    //private PackageProcessor packageProcessor;
    
    public SystemVerilogVocabularyExtractor(String diretorio){
        this.fileAnalyst = new FileAnalyst(diretorio);
        this.genericsComments = new CommentProcessor();
        this.projectSource = fileAnalyst.toStringFiles();
        this.classProcessor = new ClassProcessor();
        this.moduleProcessor = new ModuleProcessor();
        this.run();
        //this.packageProcessor = new PackageProcessor();
    }
    public void run(){
        int index = 0;
        for(;index < this.projectSource.size();index++){
            String sourceLine = this.projectSource.get(index);
            this.genericsComments.setComments(sourceLine);
            this.classProcessor.setClassesProperties(sourceLine);
            if(this.classProcessor.isModule(sourceLine)){
                this.classProcessor.setClassComents(genericsComments);
                this.genericsComments = new CommentProcessor();
            }                
        }
    }
    public String toString(){
        String svve = this.classProcessor.toString();
        svve += this.classProcessor;
        //svve += packageProcessor.toString();
        return svve;
    }
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
        SystemVerilogVocabularyExtractor svve = new SystemVerilogVocabularyExtractor("D:\\Nova pasta (2)\\Testes_Extractor\\sha3_uvm_tb");
        //SystemVerilogVocabularyExtractor svve = new SystemVerilogVocabularyExtractor("D:\\Nova pasta (2)\\arquivostestbenchfelipegonalves");
        System.out.println(svve);
        
    }
}