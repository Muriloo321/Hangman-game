package models.services;

import java.util.Scanner;

public class GameStructure {

    private char[] sortedWord;
    private char[] hiddenWord;
    private int lives;

    public GameStructure() {
        lives = 5;
    }

    public int getLives() {
        return lives;
    }

    private void setLives(int lives) {
        this.lives = lives;
    }

    public void initializeGame(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Bem-vindo ao jogo da forca! Tente adivinhar a palavra!");
        System.out.println("Primeiramente, selecione um tema:");
        System.out.println("1- Jogos");
        System.out.println("2- Filmes");
        System.out.println("3- Nomes");
        System.out.println("4- Animais");
        System.out.println("5- Objetos");
        int themeChoice = sc.nextInt();
        this.sortedWord = WordsManager.chooseWord(themeChoice).toCharArray();
        this.hiddenWord = new char[sortedWord.length];

        for(int i = 0; i < hiddenWord.length; i++){
            hiddenWord[i] = '-';
        }

        System.out.println("Você possui "+getLives()+" vidas.");
        System.out.println("Vamos começar.");
        System.out.println();
        System.out.println("Adivinhe a palavra abaixo");
    }

    public void round(){
        Scanner sc = new Scanner(System.in);
        int undiscoveredWords = 0;

        endGame();
        System.out.print("Vidas restantes: "+getLives());
        System.out.println();
        for(char l : hiddenWord){
            System.out.print(l);
        }
        System.out.println();
        System.out.print("Insira uma letra: " );

        String input = sc.next();
        char l = getValidLetter(input);
        verifyLetter(l);

        for(char c : hiddenWord){
            if(c == '-'){
                undiscoveredWords++;
            }
        }
        if(undiscoveredWords == 0){
            System.out.println("VOCÊ ACERTOU! PARABÉNS!");
            System.out.print("A palavra era: ");
            for(char o : sortedWord){
                System.out.print(o);
            }
            System.exit(0);
        }
    }

    private void verifyLetter(char l){
    int counter = 0;
        for(int i = 0; i < sortedWord.length; i++){
            if(sortedWord[i] == Character.toUpperCase(l)){
                hiddenWord[i] = Character.toUpperCase(l);
                counter++;
            }
        }
        if(counter <= 0){
            System.out.println("Você não acertou! Sinto muito.");
            setLives(getLives()-1);
        }
    }

    private char getValidLetter(String input) {
        try {
            if (input.length() > 1) {
                throw new IllegalArgumentException("Deve-se inserir apenas uma letra, não mais que isto.");
            } else if (input.length() == 0) {
                throw new IllegalArgumentException("Insira algo!");
            }
            return input.charAt(0);
        } catch (IllegalArgumentException e) {
            System.out.println();
            System.out.println("Erro: " + e.getMessage());
            System.out.println();
            return ' ';
        }
    }

    private void endGame(){
        if(getLives() == 0){
            System.out.print("Você perdeu! A palavra era: ");
            for(char c : sortedWord){
                System.out.print(c);
            }
            System.exit(0);
        }
    }
}
