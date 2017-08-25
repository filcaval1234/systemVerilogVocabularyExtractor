/**
 * A classe SearchPackage recebe o diretorio do projeto daí cria uma lista
 * com os pacotes do projeto.
 */
package systemverilogvocabularyextractor;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author fc.corporation
 */
public class SearchPackage {
    Package[] packages;
    ArrayList<FileReader> allFilesProject;
    ArrayList<BufferedReader> bufferFiles;
    
    public SearchPackage(String diretorio){
        this.packages = new Package[1]; 
        this.allFilesProject = new ArrayList<FileReader>();
        this.bufferFiles = new ArrayList<BufferedReader>();
        this.setPackages(diretorio);
        
    }
    public Package[] getPackage(){
        return this.packages;
    }
    /**
     * O método setPackages recebe um argumento que é o diretorio daí através de
     * uma função recursiva varre esse diretório procurando por arquivos com o 
     * final pkg.sv ou pkg.svh salvando o caminho absoluto e o nome do pacote
     * @param diretorio diretório do projeto, sendo este o caminho absoluto
     */
    public void setPackages(String diretorio){
        //ArrayList<File> absolutePathPackage = new ArrayList<File>();
        ArrayList<String> namePackage = new ArrayList<String>();
        this.setPackages(diretorio, namePackage);
        //this.packages = new Package[absolutePathPackage.size()];
        this.inBufferFiles();
        this.packages[0] = new Package(bufferFiles);
        /*for(int i=0;i < this.allFilesProject.size();i++){
            this.inBufferFiles();
            this.packages[i] = new Package(this.bufferFiles);
            //this.packages[i].setArquivosDoPacote();
        }*/
    }
    public void inBufferFiles(){
        for(int i=0;i<allFilesProject.size();i++){
            BufferedReader bufferFile = null;
            bufferFile = new BufferedReader(allFilesProject.get(i));
            bufferFiles.add(bufferFile);
        }
        this.allFilesProject = null;
    }
    private void setPackages(String diretorio, ArrayList<String> namePackage){
        File dirProject = new File(diretorio);
        File[] aFile = dirProject.listFiles();
        String[] sufixName = {".sv", ".svh"};
        for(int i=0;i < aFile.length;i++){
            if(aFile[i].isFile() && aFile[i].getName().endsWith(sufixName[0]) || aFile[i].getName().endsWith(sufixName[1])){
                try {
                    FileReader file = new FileReader(aFile[i]);
                    allFilesProject.add(file);
                } catch (FileNotFoundException ex) {
                    //Logger.getLogger(SearchPackage.class.getName()).log(Level.SEVERE, null, ex);
                    continue;
                }
                namePackage.add(aFile[i].getName());
            }
            else if(aFile[i].isDirectory()){
                this.setPackages(aFile[i].getAbsolutePath(), namePackage);
            }
        }
    }
}