/**
 * A classe MethodProcessor encapsula os dados e os métodos necessarios para
 * retirar tudo de importante de um bloco de código que seja uma função
 * comentários, parâmetros, variáveis locais, enums, constantes, e seu retorno 
 * mesmo sendo void.
 */
package systemverilogvocabularyextractor;
import java.util.ArrayList;
/**
 *
 * @author fc.corporation
 */
public class MethodProcessor extends Modulo{
    private ArrayList<MethodData> mtsd;
    
    public MethodProcessor(){
        super("function", "endfunction");
        this.mtsd = new ArrayList<MethodData>();
    }
    
    public void isCommentFunction(String linha){
        //funçao que tera um finalidade uma hora ou outra
    }
    public void setFields(String originalLinha){
        final String SPACE = " ";
        final String PARENTESES = "(";
        String[] listWord;
        MethodData methodData = null;
        String linha = this.filterIndentation(originalLinha);
        linha = this.filterAccessMode(linha);
        if(isModule(linha)){
            linha = linha.substring(0, linha.indexOf(PARENTESES));
            listWord = linha.split(SPACE);
            if(listWord.length == 3){
                methodData = new MethodData(listWord[listWord.length-1], this.getReturn(listWord));
                methodData.setParam(originalLinha);
                this.mtsd.add(methodData);
            }
        }
        this.setVariableAndCommentlocal(originalLinha);
    }
    /**
     *
     * @param linha
     */
    @Override
    public void setVariableAndCommentlocal(String linha){
        if(beginStruct && !endStruct){
            this.mtsd.get(mtsd.size()-1).setLocalField(linha);
            this.mtsd.get(mtsd.size()-1).setCommentLocal(linha);
        }
        else if(endStruct){
            beginStruct = false;
            endStruct = false;
        }
    }
    private String getReturn(String[] listWord){
        String returnOfFunction = "";
        for(int i=1;i < listWord.length-1;i++){
            returnOfFunction += listWord[i];
        }
        return returnOfFunction;
    }
    public String toString(){
        String methodProcessor = "";
        for(MethodData mtd: this.mtsd){
            methodProcessor += mtd+"\n";
        }
        return methodProcessor;
    }
}