/**
 * A classe Package emcapsula os dados e os métodos necessarios
 * para buscar todos os arquivos *.sv/*.svh pertencentes ao pacote 
 * abri-los e salva-los em um ArrayList<>.
 */
package systemverilogvocabularyextractor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author fc.corporation
 */
public class PackageProcessor {
    private ArrayList<PackageData> arrayPackage; 
    private VerificationSintax verificationSimtas;
    /**
     * O construtor da classe não recebe nemhum argumento e inicializa todos os 
     * campos da classe
     */
    public PackageProcessor( ){
        arrayPackage = new ArrayList<PackageData>();
        this.verificationSimtas = new VerificationSintax();
        verificationSimtas.setAvlTreeSintax(verificationSimtas.setWordsKeys());
    }
    /**
     * O método getLinhasArquivosDoPacote abre o arquivo e lê suas linhas
     * retornand o um ArrayList<String>
     * @return um ArrayList<String> contendo as linhas do arquivo. 
     */
    private ArrayList<String> getLinhasArquivosDoPacote(){
        return new ArrayList<>();
    }
    /**
     * O método nameFilesInPackage faz uma filtragem das palavras reservadas de
     * Systemverilog e de UVM retornando um ArrayList<String> só com os nomes dos
     * arquivos.
     * @param linhasDoArquivo array com todas as linhas do arquivo pkg.sv/svh.
     * @return um array com os nomes dos arquivos.
     */
    private ArrayList<String> nameFilesInPackage(ArrayList<String> linhasDoArquivo){
        String[] sufixName = {".sv\"", ".svh\""};
        ArrayList<String> nomes = new ArrayList<String>();
        for(int i=0;i < linhasDoArquivo.size();i++){
            String[] listWords = linhasDoArquivo.get(i).split(" ");
            for(int j=0;j < listWords.length;j++){
                try {
                    if(verificationSimtas.sytemVerilogSintax(listWords[j]) == false && verificationSimtas.UVMsintax(listWords[j]) == false){
                        if(listWords[j].endsWith(sufixName[0]) || listWords[j].endsWith(sufixName[1]))
                            nomes.add(listWords[j]);
                    }
                }catch(StringIndexOutOfBoundsException ioe){
                    continue;
                }
            }
        }
        return nomes;
    }
    /**
     * O método setArquivosDoPacote atráves de seus métodos privates
     * e abre todos os arquivos contidos no pacote e salva-os em um array.
     */
    public void setArquivosDoPacote(){
        ArrayList<String> linhasDoFilePackage = this.getLinhasArquivosDoPacote();
        ArrayList<String> nomeDosArquivos = this.nameFilesInPackage(linhasDoFilePackage);
        this.setArquivosDoPacote(nomeDosArquivos);
    }
    /**
     * O método setArquivosDoPacote recebe os nomes dos arquivos e a partir do 
     * caminho absoluto do arquivo nome_pkg.sv/svh abre os arquivos 
     * @param nomesDosArquivos array com o nome dos arquivos 
     */
    private void setArquivosDoPacote(ArrayList<String> nomesDosArquivos){
        
    }
    /**
     * O método filtroAspas recebe um array contendo o nome dos arquivos, no entanto
     * os nomes ainda possuem aspas daí o filtro de aspas retirar-ló-ei-las 
     * @param nomesDosArquivos array com todos os nomes dos arquivos
     * @return um array com os nomes dos arquivos sem aspas
     */
    private ArrayList<String> filtroAspas(ArrayList<String> nomesDosArquivos){
        ArrayList<String> wordsFiltrade = new ArrayList<String>();
        for(int i=0;i < nomesDosArquivos.size();i++){
            String strTemp = nomesDosArquivos.get(i).replace("\"", "");
            wordsFiltrade.add(i, strTemp);
        }
        return wordsFiltrade;
    }
    public String toString(){
        String pacote = "";
        return pacote;
    }
}