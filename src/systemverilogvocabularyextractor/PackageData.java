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
    
    /**
     * O construtor da classe recebe dois argumentos e inicializa todos os campos
     * da classe.
     * @param nome nome do pacote systemverilog
     * @param filesInPackage array de String que são os arquivos do pacote
     */
    public PackageData(String nome, ArrayList<String> filesInPackage){
        this.namePackage = nome;
        this.filesInPackage = filesInPackage;
    }
    /**
     * O construtor da classe que recebe somente um argumento que é o nome do 
     * pacote
     * @param nome nome do pacote
     */
    public PackageData(String nome){
        this.namePackage = nome;
        this.filesInPackage = new ArrayList<String>();
    }
    /**
     * O método setFilesInPackage seta os os nomes dos arquivos através de uma
     * unica String por vez.
     * @param fileInPackage nome do arquivos que vai ser adicionado ao array
     */
    public void setFilesInPackage(String fileInPackage){
        this.filesInPackage.add(fileInPackage);
    }
    /**
     * O método setFilesInPackage seta todos os arquivos que estão no pacote
     * de uma unica vez.
     * @param files referencia a um array que contén os nomes dos arquivos do pacote
     */
    public void setFilesInPackage(ArrayList<String> files){
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