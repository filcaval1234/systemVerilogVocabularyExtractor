/**
 * A classe InterfaceProcessor encapsula os campos e os método necessarios para
 * a modelagem de um processador de interface extendendo da classe abstrata Modulo.
 */
package systemverilogvocabularyextractor;
import java.util.ArrayList;
/**
 *
 * @author fc.corporation
 */
public class InterfaceProcessor extends Modulo{
    private ArrayList<InterfaceData> arrayInterface;
    private CommentProcessor genericCommentProcessorInterface;
    private static final String BEGIMINTERFACE = "interface";
    private static final String ENDINTERFACE = "endinterface";
    private int size;
    
    /**
     * O construtor da classe que não recebe nemhum argumento e instacia 
     * seus objetos.
     */
    public InterfaceProcessor(){
        super(InterfaceProcessor.BEGIMINTERFACE, InterfaceProcessor.ENDINTERFACE);
        this.arrayInterface = new ArrayList<InterfaceData>();
        this.genericCommentProcessorInterface = new CommentProcessor();
    }   
    /**
     * O método
     * @param sourceLine 
     */
    @Override
    void setFields(String sourceLine) {
        InterfaceData tempInterfece;
        if(this.isModule(sourceLine)){
            String[] wordsInline = sourceLine.split(" ");
            tempInterfece = new InterfaceData(wordsInline[1]);
            this.size++;
        }
    }
    /**
     * 
     * @param sourceLine 
     */
    @Override
    void setVariableAndCommentlocal(String sourceLine) {
        if(beginStruct && !endStruct){
            InterfaceData interfaceTemp = this.arrayInterface.get(size-1);
            if(this.genericCommentProcessorInterface.isCommentBlock(sourceLine))
                this.genericCommentProcessorInterface.setComments(sourceLine);
            else{
                interfaceTemp.setMethodProcessorInterface(sourceLine);
                interfaceTemp.setTaskProcessorInterface(sourceLine);
                interfaceTemp.setModPortProcessorInterface(sourceLine);
                if(!interfaceTemp.getMethodProcessorInterface().isModule() && 
                        !interfaceTemp.getTaskProcessorInterface().isModule() &&
                        !interfaceTemp.getModPortProcessorInterface().isModule())
                    interfaceTemp.setFieldProcessorInterface(sourceLine);
                if(interfaceTemp.getMethodProcessorInterface().isModule(sourceLine)){
                    this.genericCommentProcessorInterface.setBeginComments(false);
                    this.genericCommentProcessorInterface.setEndComments(false);
                    interfaceTemp.getMethodProcessorInterface().
                            getUltimateMethod().setCommentLocal(this.genericCommentProcessorInterface);
                    this.genericCommentProcessorInterface = new CommentProcessor();
                }
            }
        }
        else if(endStruct){
            beginStruct = false;
            endStruct = false;
        }
    }
}