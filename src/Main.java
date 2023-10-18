//package com.company;
import java.util.*;

import java.util.ArrayList;

class Slovar {
    private Map<String, Map<String, List<String>>> dictionary;

    {
        dictionary = new TreeMap<String, Map<String, List<String>>>();
    }


    public boolean addLang(String lang) {
        lang = lang.toLowerCase();
        if (dictionary.containsKey(lang)) {
            return false;
        }
        dictionary.put(lang, new TreeMap<String, List<String>>());
        return true;
    }

    public boolean removeLang(String lang) {
        lang = lang.toLowerCase();
        if (dictionary.containsKey(lang)) {
            dictionary.remove(lang);
            return true;
        }
        return false;
    }

    private List<String> toLowerCase(List<String> list){
        List<String> listArr = new ArrayList<String>();
        for (String word:list) {
            listArr.add(word.toLowerCase());
        }
        return listArr;
    }

    public boolean addWord(String keyLang, String originalWord, List<String> arrWord) {
        originalWord = originalWord.toLowerCase();
        keyLang=keyLang.toLowerCase();
        arrWord = toLowerCase(arrWord);
        if (dictionary.containsKey(keyLang)) {
            if(dictionary.get(keyLang).containsKey(originalWord)) {
                return false;
            }else {
                dictionary.get(keyLang).put(originalWord, arrWord);
                return true;
            }
        }else {
            if(addLang(keyLang))
                return addWord(keyLang, originalWord, arrWord);
            else return false;
        }
    }
    public boolean addWord(String keyLang, String originalWord, String word) {
        originalWord = originalWord.toLowerCase();
        keyLang=keyLang.toLowerCase();
        word=word.toLowerCase();
        if (dictionary.containsKey(keyLang)) {
            if(dictionary.get(keyLang).containsKey(originalWord)) {
                if(dictionary.get(keyLang).get(originalWord).contains(word)){
                    return false;
                }else{
                    dictionary.get(keyLang).get(originalWord).add(word);
                    return true;
                }
            }else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(word);
                dictionary.get(keyLang).put(originalWord, list);
                return true;
            }
        }else {
            if(addLang(keyLang)) {
                boolean isadd = addWord(keyLang, originalWord, word);
                return isadd;
            }else {
                return false;
            }
        }
    }

    public void printLang() {
        int count = 1;
        for (String language : dictionary.keySet()) {
            System.out.println(count++ + ") " + language);
        }
    }

    public void printSlovarLang(String key){
        int count = 1;
        for (String newKey:dictionary.keySet()) {
//            System.out.println(newKey);
            System.out.println("_______________________________________________________");
            for (String word:dictionary.get(newKey).keySet()) {
                System.out.print(word+" : ");
                for (String translate:dictionary.get(newKey).get(word)) {
                    System.out.print(translate+" . ");
                }
                System.out.println();
            }
        }
//        System.out.println(dictionary.get(key));
    }


    public static String mScanerString() {
        String k=null;
        Scanner scanner = new Scanner(System.in);
        boolean isString = scanner.hasNextLine();
        if (isString) k = scanner.nextLine();{

            return k;}
    }
    public static int mScanerInt() throws Exception {
        int k = 0;
        Scanner scanner = new Scanner(System.in);
        boolean isInt = scanner.hasNextInt();
        if (isInt) k = scanner.nextInt();
        if (k >= 1 & k <= 5) {
            return k;
        }
        throw new Exception("Ввести нужно только число число от 1 до 5, буквы вводить нельзя");

    }
    }


public class Main {

    public static void main(String[] args) throws Exception {
        Slovar slovar = new Slovar();
        slovar.addLang("en");
        slovar.addLang("ru");
        slovar.addWord("en", "Car", "Автомобиль");
        slovar.addWord("en", "Car", "Машина");
        slovar.addWord("en", "Car", "Транспорт");
        slovar.addWord("en", "Head", "Голова");
        slovar.addWord("ru", "кот", "cat");
        int m=0;
        String k = null;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Для использования словаря выберите пункт меню");
        System.out.println("1. Показать имеющиеся словари");
        System.out.println("2. Показать показать все имеющиеся в словатрях слова с преводом");
        System.out.println("3. Добавить слова в словарь");
        System.out.println("4. Удалить словарь целеком");
        m=slovar.mScanerInt();
        switch (m) {
            case 1:
                slovar.printLang();
                break;
            case 2:
                slovar.printSlovarLang("en");
                break;
            case 3:
                ArrayList<String> arr = new ArrayList<String>();
                System.out.println("Напишите слово, нажмите Enter и напишите перевод этого слова");
            slovar.addWord("en", slovar.mScanerString(), slovar.mScanerString());
                System.out.println("Теперь словарь имеет такие слова");
                slovar.printSlovarLang("en");
                break;
            case 4:
                System.out.println("Сейчас имеются следующие словари:");
                slovar.printLang();
                System.out.println("Введите номер словаря, который хотите удалить");
                m=slovar.mScanerInt();
                switch (m) {
                    case 1:
                    slovar.removeLang("en");
                    System.out.println("Теперь остались следующие словари, содержащие такие слова");
                    slovar.printSlovarLang("en");

                case 2:
                    slovar.removeLang("ru");
                    System.out.println("Теперь остались следующие словари, содержащие такие слова");
                    slovar.printLang();
                    slovar.printSlovarLang("en");
//
            }
//            case 5:
//                arr[4] = '0';
//                break;
//            default:
//                System.out.println("Введите число от 1 до 5");
//                break;
        }

//        k= Slovar.mScanerString();
//

//        slovar.printSlovarLang("en");
//
//        slovar.printSlovarLang("ru");


    }
}
