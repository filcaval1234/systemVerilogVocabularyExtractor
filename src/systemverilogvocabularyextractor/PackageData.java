/**
 * A classe PackageData encapsula os dados e os métodos necessarios para a modelagem
 * de um package em systemverilog
 */
package systemverilogvocabularyextractor;
import java.util.ArrayList;
/**
 *
 * @author fc.corporation
 */
public class PackageData {
    private String namePackage;
    private ArrayList<String> filesInPackage;
    
    public PackageData(String nome, ArrayList<String> filesInPackage){
        this.namePackage = nome;
        this.filesInPackage = filesInPackage;
    }
    public PackageData(String nome){
        this.namePackage = nome;
        this.filesInPackage = new ArrayList<String>();
    }
    public void setFilesInPackage(String fileInPackage){
        this.filesInPackage.add(fileInPackage);
    }
    public void setFilesInPacage(ArrayList<String> files){
        this.filesInPackage = files;
    }
    public String toString(){
        String packages = "-----------nome do pacote-----------\n";
        packages += this.namePackage+"\n";
        packages += "----------arquivos do pacote------------\n";
        for(String str: filesInPackage){
            packages += str+"\n";
        }
        return packages;
    }
}
