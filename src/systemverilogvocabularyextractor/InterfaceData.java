/**
 * A classe InterfaceData encapsula os dados e os métodos necessarios para 
 * a modelagem de uma interface em systemverilog.
 */
package systemverilogvocabularyextractor;

/**
 *
 * @author fc.corporation
 */
public class InterfaceData {
    private String nome;
    private CommentProcessor commentsProcessorInterface;
    private FieldProcessor fieldProcessorInterface;
    private ParamProcessor paramProcessorInterface;
    private MethodProcessor methodProcessorInterface;
    private TaskProcessor taskProcessorInterface;
    private ModPortProcessor arrayModPort;
    
    /**
     * O contrutor da classe recebe uma String que é o nome da interface
     * daí, cria instancias dos seus atributos
     * @param nome nome da interface
     */
    public InterfaceData(String nome){
        this.nome = nome;
        this.commentsProcessorInterface = new CommentProcessor();
        this.fieldProcessorInterface = new FieldProcessor();
        this.paramProcessorInterface = new ParamProcessor();
        this.methodProcessorInterface = new MethodProcessor();
        this.taskProcessorInterface = new TaskProcessor();
        this.arrayModPort = new ModPortProcessor();
    }
    /**
     * O método getMethodProcessorInterface retorna o processador de métodos
     * da interface.
     * @return retorna o processador de métodos da interface
     */
    public MethodProcessor getMethodProcessorInterface() {
        return methodProcessorInterface;
    }
    /**
     * O método setMethodProcessorInterface (seta) o processador de métodos 
     * @param methodProcessorInterface referencia de um processador de método 
     * que será passada ao atributo methodProcessorInterface.
     */
    public void setMethodProcessorInterface(MethodProcessor methodProcessorInterface) {
        this.methodProcessorInterface = methodProcessorInterface;
    }
    /**
     * O método getTaskProcessorInterface retorna o processador de tasks da interface.
     * @return o processador de método da interface.
     */
    public TaskProcessor getTaskProcessorInterface() {
        return taskProcessorInterface;
    }
    /**
     * O método setTaskProcessorInterface (seta) o atributo taskProcessorInterface.
     * @param taskProcessorInterface referencia de um processador de tasks
     * que será passada ao atributo taskProcessorInterface
     */
    public void setTaskProcessorInterface(TaskProcessor taskProcessorInterface) {
        this.taskProcessorInterface = taskProcessorInterface;
    }
    /**
     * O método getCommentsProcessorInterface retorna o processador de
     * comentários da interface
     * @return o processador de comentários da interface
     */
    public CommentProcessor getCommentsProcessorInterface() {
        return commentsProcessorInterface;
    }
    /**
     * O método setCommentsProcessorInterface (seta) o atributo commentsProcessorInterface.
     * @param commentsProcessorInterface referencia de um processador de commentarios.
     */
    public void setCommentsProcessorInterface(CommentProcessor commentsProcessorInterface) {
        this.commentsProcessorInterface = commentsProcessorInterface;
    }
    /**
     * O método getFieldProcessorInterface retorna o processador de atributos
     * da interface
     * @return o processador de atributos da interface
     */
    public FieldProcessor getFieldProcessorInterface() {
        return fieldProcessorInterface;
    }
    /**
     * O método setFieldProcessorInterface (seta) o atributo fieldProcessorInterface
     * @param fieldProcessorInterface referencia de um processador de atributos.
     */
    public void setFieldProcessorInterface(FieldProcessor fieldProcessorInterface) {
        this.fieldProcessorInterface = fieldProcessorInterface;
    }
    /**
     * O método getParamProcessorInterface retorna o processador de parâmetos 
     * da interface
     * @return retorna o processador de parâmetros da interface
     */
    public ParamProcessor getParamProcessorInterface() {
        return paramProcessorInterface;
    }
    /**
     * O método setParamProcessorInterface (seta) o atributo paramProcessorInterface.
     * @param paramProcessorInterface referencia de um processador de parâmetros.
     */
    public void setParamProcessorInterface(ParamProcessor paramProcessorInterface) {
        this.paramProcessorInterface = paramProcessorInterface;
    }
}