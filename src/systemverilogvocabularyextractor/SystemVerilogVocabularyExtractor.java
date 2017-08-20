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
    private ClassProcessor classProcessor;
    private ModuleProcessor moduleProcessor;
    private ArrayList<String> projectSource;
    //private PackageProcessor packageProcessor;
    //private 
    
    public SystemVerilogVocabularyExtractor(String diretorio){
        this.fileAnalyst = new FileAnalyst(diretorio);
        this.projectSource = fileAnalyst.toStringFiles();
        this.classProcessor = new ClassProcessor();
        this.moduleProcessor = new ModuleProcessor();
        //this.packageProcessor = new PackageProcessor();
    }
    public String toString(){
        String svve = this.classProcessor.toString();
        svve += this.classProcessor;
        //svve += packageProcessor.toString();
        return svve;
    }
    public static void main(String[] args) {
        if(args.length != 1){
            System.err.println("built.: java SystemVerilogVocabularyExtractor"
                    + " absolute path your project.");
            System.err.println("ERROR: Unknown absolute path");
            System.exit(-1);
        }
        SystemVerilogVocabularyExtractor svve = new SystemVerilogVocabularyExtractor(args[0]);
        
        
    }
}