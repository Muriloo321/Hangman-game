package models.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordsManager {
    /*Esta classe possui como intuito gerenciar a lista de possíveis palavras para o jogo, a partir do arquivos
    .txt, para escolha do tema, além de lançar uma palavra aleatória para o programa principal.*/

    //O vetor "ARQUIVOS" está totalmente em maiúsculo por se tratar de constantes
    private static final String[] ARQUIVOS = {"jogos.txt", "filmes.txt", "nomes.txt", "animais.txt", "objetos.txt"};

    private static List<String> generateWords(int choice) {
        List<String> possibleWords = new ArrayList<>();
        String arquivo = null;

        try{
        arquivo = ARQUIVOS[choice - 1];
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Opção inválida. Você deve escolher um arquivo entre 1 e "+ARQUIVOS.length);
            System.exit(0);
        }

        try (BufferedReader bf = new BufferedReader(new FileReader(arquivo))) {
            String line;
            while ((line = bf.readLine()) != null) {
                possibleWords.add(line);
            }
            return possibleWords;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ocorreu um erro ao tentar ler o arquivo.");
        }
    }


    public static String chooseWord(int choice){
        Random rand = new Random();

        List<String> possibleWords = generateWords(choice);
        String word = possibleWords.get(rand.nextInt(possibleWords.size())); //Aqui serve para gerar aleatoriamente uma palavra

        return word;
    }
}
