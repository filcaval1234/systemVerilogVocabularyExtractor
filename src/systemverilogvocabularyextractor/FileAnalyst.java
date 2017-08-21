/**
 * A classe FileAnalyst encapsula os dados e os métodos necessarios para 
 * receber pacotes ler os arquivos do pacote apartir daí chama as entidades
 * para começar a retirada e a modelagem dos dados no arquivos do projeto.
 */
package systemverilogvocabularyextractor;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author fc.corporation
 */
public class FileAnalyst {
    SearchPackage srpkg;
    FieldProcessor anlv;
    CommentProcessor cmt;
    Modulo clpr;
    
    /**
     * O construtor da classe recebe um argumento e inicializa o campo srpkg
     * passando o diretorio do projeto
     * @param diretorio diretorio do projeto
     */
    public FileAnalyst(String diretorio){
        srpkg = new SearchPackage(diretorio); 
        //anlv = new FieldProcessor();
        //cmt = new CommentProcessor();
        //clpr = new ClassProcessor(diretorio);
        
    }
    /**
     * O método toStringFiles chama o método private toStringFiles passando a lista de
     * pacotes daí então lê o arquivo linha por linha e chama as entidades que analisam
     * essas linhas modelando esses dados.
     */
    public ArrayList<String> toStringFiles(){
        return this.toStringFiles(srpkg.getPackage());
    }
    //possivelmente a função toStringFiles poderá retornar Strings com o conteúdo
    //dos arquivos ou não( analisar com calma isso mais tarde) DATTEBAYO!!!!!
    private ArrayList<String> toStringFiles(Package[] pkg){
        String strFile;
        ArrayList<String> lineInFile = new ArrayList<String>();
        int i=0;
        for(Package pacote: pkg){
            ArrayList<BufferedReader> listArq = pacote.getFile();
            for(BufferedReader fileInPackage: listArq){
                try {
                    while(fileInPackage.ready()){
                    lineInFile.add(fileInPackage.readLine());
                    i++;
                    }
                    fileInPackage.close();
                } catch(IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
        return lineInFile;
    }
    /*public static void main(String[] args) {
        //D:\\Nova pasta (2)\\Testes_Extractor\\apb_test
        //D:\\Nova pasta (2)\\Testes_Extractor\\ahb2_uvm_tb
        //D:\\Nova pasta (2)\\Testes_Extractor\\sha3_uvm_tb
        //FileAnalyst fa = new FileAnalyst("D:\\Nova pasta (2)\\arquivostestbenchfelipegonalves");
        //FileAnalyst fa = new FileAnalyst("D:\\Nova pasta (2)\\result (9)");
        //FileAnalyst fa = new FileAnalyst("D:\\Nova pasta (2)\\Testes_Extractor\\apb_test");
        //FileAnalyst fa = new FileAnalyst("D:\\Nova pasta (2)\\Testes_Extractor\\ahb2_uvm_tb");
        //FileAnalyst fa = new FileAnalyst("D:\\Nova pasta (2)\\Testes_Extractor\\ahb_apb_bridge_uvm_tb");
        //FileAnalyst fa = new FileAnalyst("D:\\Nova pasta (2)\\Testes_Extractor\\sha3_uvm_tb");
        //ClassProcessor clpr = new ClassProcessor("D:\\Nova pasta (2)\\result (9)");
        //ClassProcessor clpr = new ClassProcessor("D:\\Nova pasta (2)\\arquivostestbenchfelipegonalves");
        //ClassProcessor clpr = new ClassProcessor("D:\\Nova pasta (2)\\Testes_Extractor\\sha3_uvm_tb");
        //clpr.setClassesProperties();
        //System.out.println(clpr);
    }*/
}