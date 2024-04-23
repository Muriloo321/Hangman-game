package models.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordsManager {
    /*Esta classe possui como intuito gerenciar a lista de possíveis palavras para o jogo, a partir do arquivo
    possives_palavras.txt, além de lançar uma palavra aleatória para o programa principal.*/

    private static List generateWords() {
        List<String> possibleWords = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader("possiveis_palavras.txt"))) {

            String line;
            while ((line = bf.readLine()) != null) {
                possibleWords.add(line);
            }
            return possibleWords;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("O arquivo não foi encontrado.");
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ocorreu um erro ao tentar ler o arquivo.");
        }
    }

    public static String chooseWord(){
        Random rand = new Random();

        List<String> possibleWords = generateWords();
        String word = possibleWords.get(rand.nextInt(possibleWords.size()));

        return word;
    }
}
