package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/* 
* Classe responsável por montar todos os comandos referentes ao banco de dados SQLite
*/
public class SQLiteTableManager
{
    
    /* 
    * Construtores
    */
    public SQLiteTableManager()
    {
        inicializarBanco();
    }
    
    /* 
    * FUNÇÕES DO SQLITE
    */
    
    /* 
    * Método responsável por inicializar o banco de dados.
    * Ele lê o script de criação de tabelas que está em um txt e enviar comandos de Create Table um a um para criar as tabelas,
    * caso elas não estejam criadas.
    *
    * Embora esse método não gera um comando, acho que essa classe é o melhor local para colocá-la. 
    */
    public void inicializarBanco()
    {
        this.scriptPath = "src/main/resources/database/script.txt";
                
        ArrayList<String> instrucoes = new ArrayList<String>();
        instrucoes = SQLiteTableManager.lerScriptSQL(scriptPath);
        
        for(String instrucao : instrucoes)
        {   
            SQLiteConnectionManager.enviarQuery(instrucao);
        }
    }

     /* 
      * Esse método retorna um comando para fazer insert com a tabela, colunas e valores passados por parâmetro
    */
    public static String insertTo(String tabela, String colunas, String valores)
    {
        String instrucao = new String();
        instrucao =
        "INSERT INTO "+ tabela +"(" + colunas +") " +
        "VALUES "+ "(" + valores + ")" + ";";

        return instrucao;
    }

    /* 
     * Esse método irá retornar um comando para dar um select * na tabela passada por parâmetro
     */
    public static String selectAll(String tabela)
    {
        String instrucao =
        "SELECT * FROM " + tabela + ";";
        
        return instrucao;
    }

    /* 
     * Método responsável por retornar um comando para dar um UPDATE em uma tabela , em campos especificados por uma condicao, passados por parâmetro
     */
    public static String update(String tabela, String colunas_novosValores, String condicao)
    {
        String instrucao = 
        "UPDATE " + tabela + " SET " + colunas_novosValores + " WHERE " + condicao + ";";

        return instrucao;
    }

    /* 
     * Método responsável por retornar um comando para dar DELETE em uma linha de uma tabela passados por parâmetro
     */
    public static String delete(String tabela, String condicao)
    {
        String instrucao=
        "DELETE FROM " + tabela + " WHERE " + condicao + ";";

        return instrucao;
    }

    /*
     * Esse método seleciona atributos específicos de uma tabela
     */
    public static String select(String tabela, String atributos)
    {
        String instrucao = new String();
        instrucao = 
        "SELECT " + atributos + " FROM " + tabela + ";";

        return instrucao;
    }

    /* 
     * Esse método utiliza o where para selecionar atributos com uma condição
     */
    public static String select(String tabela, String atributos, String condicao)
    {
        String instrucao = new String();
        instrucao = 
        "SELECT " + atributos + " FROM " + tabela + " WHERE " + condicao + ";";

        return instrucao;
    }

    /* 
     * Método que utiliza o Join para mesclar tabelas e seleciona todos os dados a partir dessa tabela com uma condição
     */
    public static String selectAllJoin(String tabelaDeRetorno, String tabelaMesclada, String comparacaoDeAtributos,  String condicao)
    {
        String instrucao =
        "SELECT " + tabelaDeRetorno + ".* " + " FROM " + tabelaDeRetorno + " JOIN " + tabelaMesclada +
        " ON " + comparacaoDeAtributos + " WHERE " + condicao + ";";

        return instrucao;
    }

    /* 
     * Método responsável por contar todos os elementos de uma tabela
     */
    public static String count(String tabela)
    {
        String instrucao =
        "SELECT COUNT (*) FROM " + tabela + ";";

        return instrucao;
    }
    

    /* 
    * Método responsável por contar determinados elementos de uma tabela
    * 
    * Exemplo:
    * Contar todos os produtos de um usuário
    */
    public static String count(String tabela, String condicao, String colunaContada)
    {
        String instrucao =
        "SELECT COUNT (" + colunaContada + ")" +  " FROM " + tabela + " WHERE " + condicao + ";" ;

        return instrucao;
    }
    
    /* 
     * Método responsável por contar quantas linhas de uma especificada coluna há em uma determinada tabela, utilizando
     * o join para mesclar várias tabelas
     */
    public static String count(String tabela, String condicao, String colunaContada, String join)
    {
        String instrucao =
        "SELECT COUNT (" + colunaContada + ")" +  " FROM " + tabela + join + " WHERE " + condicao + ";" ;

        return instrucao;
    }
    
    /* 
     * Método responsável por retornar um comando select com order by e limit para retornar uma quantidade x de elemento ordenados em ordem decrescente
     */
    public static String selectOrderByLimitDec(String tabela,  String atributo, String qtdDeProdutos)
    {
        String instrucao = 
        "SELECT * FROM " + tabela + " ORDER BY " + atributo +  " DESC LIMIT " + qtdDeProdutos + ";";

        return instrucao;
    }

    /* 
     * Método responsável por retornar um comando select com limit, para limitar a quantidade de linhas vindas do banco 
     */
    public static String selectLimit(String tabela, String atributos, String condicao, String qtdDeProdutos)
    {
        String instrucao = new String();
        instrucao = 
        "SELECT " + atributos + " FROM " + tabela + " WHERE " + condicao + " LIMIT " + qtdDeProdutos + ";";

        return instrucao;
    }

    

    public static ArrayList<String> lerScriptSQL(String caminhoArquivo) 
    {
        ArrayList<String> instrucoes = new ArrayList<>();
        
        try
        {
            Scanner scanner = new Scanner(new File(caminhoArquivo));

            StringBuilder instrucaoAtual = new StringBuilder();

            
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();

                instrucaoAtual.append(line).append("\n");
                
                if (line.trim().endsWith(";")) 
                {
                    instrucoes.add(instrucaoAtual.toString().trim());
                    
                    instrucaoAtual.setLength(0); 
                }
            }

            scanner.close();
        }         
        catch(FileNotFoundException e)
        {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        }


        return instrucoes;
    }
    /* ATRIBUTOS */
    private String scriptPath;
    

}
