/**
 * A classe AnalystVariable emcapsula os dados e os métodos necessarios
 * para a partir da linha recebida analísa se há ou não uma declaração de variável
 * não sendo necessário averiguar sua utilidade no código.
 */
package systemverilogvocabularyextractor;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.ArrayIndexOutOfBoundsException;

/**
 *
 * @author fc.corporation
 */
public class FieldProcessor {
    ArrayList<Variavel> listVariaveis;
    VerificationSintax vfs;
    
    /**
     * O construtor da classe que inicializa os campos listVariaveis e vfs
     * também chama o método e vfs que é setAvlTreeSintax para inicializar a árvore
     * com a sintaxe Systemverilog.
     */
    public FieldProcessor(){
        listVariaveis = new ArrayList<Variavel>();
        vfs = new VerificationSintax();
        vfs.setAvlTreeSintax(vfs.setWordsKeys());
    }
    /**
     * O método setListVariaveis faz toda a filtragem em cima da linha, ela sendo
     * uma variável, daí então chega se há mais de um tipo primitivo ele concatena-os
     * e após isso ele monta a lista de Variaveis.
     * @param linha linha do arquivo que será analisada
     */
    public void setListVariaveis(String linha){
        linha = this.filterValuesReturnedFunctions(linha);
        linha = this.filterComments(linha);
        if(this.isVariable(linha) || this.isTypeWithVector(linha)){
            ArrayList<String> wordsFiltrade = this.filtragem(linha);
            for(int i=1;i < wordsFiltrade.size();i++){
                try{
                    String tiposConcatenados = wordsFiltrade.get(0);
                    while(vfs.sytemVerilogSintax(wordsFiltrade.get(i)) == true){
                        tiposConcatenados += " "+wordsFiltrade.get(i);
                        i++;
                    }
                    if(wordsFiltrade.get(i).contains("[")){
                        tiposConcatenados +=" "+wordsFiltrade.get(i);
                        i+=1;
                    }
                    Variavel tempVar = new Variavel(tiposConcatenados, wordsFiltrade.get(i));
                    this.listVariaveis.add(tempVar);
                }catch(IndexOutOfBoundsException ioe){
                    break;
                }
            }
        }
    }
    /**
     * O método filtragem executa todos os sub-filtros de forma organizada
     * filtrando assim tudo de desnecessario da linha retornando um array
     * @param linha linha que será filtrada
     * @return um array só com tipos e nomes de variáveis
     */
    private ArrayList<String> filtragem(String linha){
        linha = this.filterIdentation(linha);
        String filtradeWord = this.filtroPontoVirgula(this.filtroValores(linha));
        ArrayList<String> listFiltradeWord = this.filtroIdentacao(filtradeWord);
        return this.filterComman(listFiltradeWord);
    }
    /**
     * O método filterComman  faz o slice na vírgula ou retira a vírgula existente no 
     * array que este método recebe retornando um novo array onde não há virgulas.
     * @param listFiltradeWord lista de nomes onde já foram executaos alguns filtros.
     * @return um array onde não há mais virgulas entre as palavras, palavras essas que
     * agora estao divididas em elementos diferentes.
     */
    private ArrayList<String> filterComman(ArrayList<String> listFiltradeWord){
        ArrayList<String> copyListFiltradeWord = new ArrayList<String>();
        final char COMMAN = ',';
        final String STRCOMMAN = ",";
        for(String str: listFiltradeWord){
            if(str.equals(STRCOMMAN))
                continue;
            if(str.contains(STRCOMMAN)){
                String[] listSrtTemp = str.split(STRCOMMAN);
                for(String s: listSrtTemp){
                    copyListFiltradeWord.add(s);
                }
            }
            else copyListFiltradeWord.add(str);
        }
        return copyListFiltradeWord;
    }
    /**
     * O método filtroIdentacao recebe um argumento e retira toda a identação e
     * também todos os espaços desnecessarios
     * @param linha linha que será retirado a identação
     * @return um array com nomes sem espaços
     */
    private ArrayList<String> filtroIdentacao(String linha){
        int i=0;
        final char TAB = 9;
        final char SPACE = 32;
        linha = linha.replace(TAB, SPACE);
        for(;i < linha.length(); i++){
            char teste = linha.charAt(i);
            if(linha.charAt(i) == TAB) {
                continue;
            }else break;
        }
        linha = linha.substring(i);
        String[] listStrline = linha.split(" ");
        ArrayList<String> withoutIndentation = new ArrayList<String>();
        for(String str: listStrline){
            if(!str.equals("")){
                withoutIndentation.add(str);
            }
        }
        return withoutIndentation;
    }
    private String filterIdentation(String linha){
        final String IDENTATION = "  ";
        return linha.replace(IDENTATION, "");
    }
    /**
     * O método filtroPontoVirgula retira o ";" do final de cada linha
     * passada como parâmetro 
     * @param linha linha que será retirao o ";"
     * @return uma String onde não há mais ";"
     */
    private String filtroPontoVirgula(String linha){
        return linha.replace(";", "");
    }
    /**
     * O método filtroValores filtra os valores que ha após o símbolo de "="
     * em todas as variaveis da linha
     * @param linha linha que será retirada os valores
     * @return string só com o tipo e os nomes das variáveis
     */
    private String filtroValores(String linha){
        String withoutValues = linha;
        final String ILLEGALSTRING = "=";
        final char ILLEGALWORDCHAR = '=';
        final char COMMAN = ',';
        if(linha.contains(ILLEGALSTRING)){
            withoutValues = "";
            for(int i=0;i < linha.length();i++){
                if(linha.charAt(i) == ILLEGALWORDCHAR){
                    while(linha.charAt(i) != COMMAN){
                        if(linha.charAt(i) == ';')
                            break;
                        i++;
                    }
                }
                withoutValues += linha.charAt(i);
            }
        }
       return withoutValues; 
    }
    /**
     * O método isVariable verifica se a linha passada como parâmetro pode ter ou
     * não uma variável baseada numa lista de símbolos que caracterizam a ausência
     * de variável.
     * @param linha linha que será averiguada se há ou não variável
     * @return true se há uma variável do contrário retorna false
     */
    private boolean isVariable(String linha){
        boolean state = true;
        String[] isNotVariable = {"class","#","return","(", "{", "+", "-", "/", "*","-",":", "}", ")", "<"};
        if(linha.equals(" ")){
            return false;
        }
        for(String i: isNotVariable){
            if(linha.contains(i)){
                state = false;
                return state;
            }
        }
        return state;
    }
    /**
     * O método filterValuesReturnedFunctions filtra as variáveis que estão 
     * recebendo algum valor do retorno de funções/métodos.
     * @param linha linha que será filtrada
     * @return uma String só com o tipo e o nome da variável
     */
    private String filterValuesReturnedFunctions(String linha){
        final String EQUALS = "=";
        final String PARENTHESES = "(";
        String withoutValuesReturnedOfFunctions = linha;
        if(linha.contains(EQUALS) && linha.contains(PARENTHESES)){
            withoutValuesReturnedOfFunctions = linha.substring(0, linha.indexOf(EQUALS)-1)+";";
        }
        return withoutValuesReturnedOfFunctions;
    }
    private String filterComments(String linha){
        final String ILLEGALCHARSEQUENCE = "//";
        if(linha.contains(ILLEGALCHARSEQUENCE))
            linha = linha.substring(0,linha.indexOf(ILLEGALCHARSEQUENCE));
        return linha;
    }
    public String getTypeWithVector(String linha){
        final String ISVECTOR = "]";
        linha = this.filterIdentation(linha);
        String typeWithVector = linha.substring(0, linha.indexOf("]"));
        String nome = linha.substring(linha.indexOf("]"));
        
        return typeWithVector;
    }
    private boolean isTypeWithVector(String linha){
        boolean state = false;
        linha = this.filterIdentation(linha);
        final String ILLEGALCHAR = "=";
        final String[] VECTOR = {"[", "]"};
        if(linha.contains(VECTOR[0]) && linha.contains(VECTOR[1])){
            String subLine = linha.substring(linha.indexOf(VECTOR[1]));
            if(!subLine.startsWith("] ") || !subLine.startsWith("]")){
                return false;
            }
            else return true;
        }
        return state;
    }
    public String toString(){
        String AnalystVariable = "";
        for(Variavel var: listVariaveis){
            AnalystVariable += var;
        }
        return AnalystVariable;
    }
}