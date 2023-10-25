//package com.company;
import java.util.*;

import java.util.ArrayList;

class Slovar {
//    public static String[] getArr() {
//        return arr;
//    }
//
//    public static void setArr() {
//        Slovar.arr = arr;
//    }

//    private static String[] arr;
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
    public List<String> checkList(List<String> nowArrWord, List<String> newArrWord) {
        for (String word : newArrWord) {
            if (!nowArrWord.contains(word)) {
                nowArrWord.add(word);
            }
        }
        return nowArrWord;
    }

    public boolean addWord(String keyLang, String originalWord, List<String> arrWord) {
        originalWord = originalWord.toLowerCase();// ������������� ��������
        keyLang = keyLang.toLowerCase();// ������������� ��������
        arrWord = toLowerCase(arrWord);// ������������� ��������
        if (dictionary.containsKey(keyLang)) {
            if (dictionary.get(keyLang).containsKey(originalWord)) {
                List<String> arr = dictionary.get(keyLang).get(originalWord);
                List<String> arrNew;
                //��������� ���� �� ����� ������������� � arrWord
                arrNew = checkList(arr, arrWord);
                if (arr.size() != arrNew.size()) {
                    dictionary.get(keyLang).put(originalWord, arrNew);
                    return true;
                }
                return false;
            } else {
                dictionary.get(keyLang).put(originalWord, arrWord);
                return true;
            }
        } else {
            if (addLang(keyLang))
                return addWord(keyLang, originalWord, arrWord);
            else return false;
        }
    }

    public void printSlovarLang(String newKey) {
        int count = 1;
        if (dictionary.containsKey(newKey)) {
            System.out.println(newKey);
            System.out.println("--------------------------------");
            for (String word : dictionary.get(newKey).keySet()) {
                System.out.print(word + " : ");
                for (String tr : dictionary.get(newKey).get(word)) {
                    System.out.print(tr + " . ");
                }
                System.out.println();
            }
        }
    }

//    public void printSlovarLang() {
//        int count = 1;
//        if (dictionary.keySet().size() > 0) {
//
//            for (String lang : dictionary.keySet()) {
//                System.out.println(count++ + ") " + lang);
//            }
//        }
//    }

    public String getLangByIndex(int index) {
        int count = 1;
        if (dictionary.keySet().size() >= index && index >= 0) {

            for (String lang : dictionary.keySet()) {
                if (index == count) return lang;
                count++;
                //  System.out.println(count++ + ") "+ lang);
            }
        }

        return null;
    }

    public void searchWord(String lang, String search) {
        int count = 1;
        search = search.toLowerCase();
        if (dictionary.containsKey(lang)) {
            for (String word : dictionary.get(lang).keySet()) {
                if (word.startsWith(search)) {
                    if (count == 1)
                        System.out.println("Search : " + search);
                    System.out.println(count++ + ") " + word);
                }
            }
            if (count == 1) {
                System.out.println("Такого слова нет!!!");
            }
        }
    }

    public void printWordSearch(String lang, String newWord) {
        int count = 1;
        String[] arr = newWord.split("[*]");
        String startChar = arr[0];
        String endChar = arr[arr.length-1];
        lang = lang.toLowerCase();
        startChar = startChar.toLowerCase();
        endChar = endChar.toLowerCase();
        if (dictionary.containsKey(lang)) {
            for (String word : dictionary.get(lang).keySet()) {
                if (word.startsWith(startChar) && word.endsWith(endChar)) {
                    if (count == 1)
                        System.out.println("Начало : " + startChar + " Конец : " + endChar);
                    System.out.print(count++ + ") " + word + " ");
                    for (String tr : dictionary.get(lang).get(word)) {
                        System.out.print(tr + " ");
                    }
                    System.out.println();
                    break;
                }
                if (count == 1) {
                    System.out.println("Такого слова нет !!!");
                }
            }
        }
    }

    public void printWordSize(String langguage, String newWord_) {
        int count = 1;
        String[] arr = newWord_.split("[_]");
        String startChar = arr[0];
        String endChar = arr[arr.length-1];
        langguage = langguage.toLowerCase();
        startChar = startChar.toLowerCase();
        endChar = endChar.toLowerCase();
        if (dictionary.containsKey(langguage)) {
            for (String word : dictionary.get(langguage).keySet()) {
                if (word.startsWith(startChar) && word.endsWith(endChar) && word.length() >= 3) {
                    if (count == 1)
                        System.out.println("Начало : " + startChar + " Конец : " + endChar);
                    System.out.print(count++ + ") " + word + " ");

                    for (String tr : dictionary.get(langguage).get(word)) {
                        System.out.print(tr + " ");
                    }
                    System.out.println();
                }

            }
            if (count == 1) {
                System.out.println("Такого слова нет !!!");
            }
        }
    }

    public boolean checkWordChar(String word, String search) {
                if (word.length() == search.length()) {
            for (int i = 0; i < search.length(); i++) {
                if (word.charAt(i) != search.charAt(i)) {
                    if (search.charAt(i) != '_')
                        return false;
                }
            }
            return true;
        }
        return false;
    }
    public void printWordArr(String langP, String newWordP) {
        int count = 1;
        for (String str : dictionary.get(langP).keySet()) {
            if (checkWordChar(str, newWordP)) {
                System.out.println(str);
            }
        }
    }

//    public boolean addWord(String keyLang, String originalWord, List<String> arrWord) {
//        originalWord = originalWord.toLowerCase();
//        keyLang=keyLang.toLowerCase();
//        arrWord = toLowerCase(arrWord);
//        if (dictionary.containsKey(keyLang)) {
//            if(dictionary.get(keyLang).containsKey(originalWord)) {
//                return false;
//            }else {
//                dictionary.get(keyLang).put(originalWord, arrWord);
//                return true;
//            }
//        }else {
//            if(addLang(keyLang))
//                return addWord(keyLang, originalWord, arrWord);
//            else return false;
//        }
//    }
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

//    public void printSlovarLang(String key){
//        int count = 1;
//        for (String newKey:dictionary.keySet()) {
//
//            System.out.println("_______________________________________________________");
//            for (String word:dictionary.get(newKey).keySet()) {
//                System.out.print(word+" : ");
//                for (String translate:dictionary.get(newKey).get(word)) {
//                    System.out.print(translate+" . ");
//                }
//                System.out.println();
//            }
//        }
//
//    }


    public static String mScanerString() {
        String k=null;
        Scanner scanner = new Scanner(System.in);
        boolean isString = scanner.hasNextLine();
        if (isString) k = scanner.nextLine();{

            return k;}
    }

    public static String[] mScanerStringArr() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Сколько значений перевода слов вы хотите ввести? ");
        int numWords = scanner.nextInt();
        String dumb= scanner.nextLine();
        String words[]= new String[numWords];
        for(int i=0;i<numWords; i++)
        {
            words[i]=scanner.nextLine();
            }
            return words;}

    public static int mScanerInt() throws Exception {
        int k = 0;
        Scanner scanner = new Scanner(System.in);
        boolean isInt = scanner.hasNextInt();
        if (isInt) k = scanner.nextInt();
        if (k >= 1 & k <= 10) {
            return k;
        }
        throw new Exception("Ввести нужно только число число от 1 до 10, буквы вводить нельзя");
    }
    }
public class Main {

    public static void main(String[] args) throws Exception {
        Slovar slovar = new Slovar();
        slovar.addLang("en");
        slovar.addLang("ru");
        slovar.addWord("en", "Car","автомобиль");
        slovar.addWord("en", "dog","собака");
        slovar.addWord("en", "Power","мощность");
        slovar.addWord("en", "Head", "Голова");
        slovar.addWord("ru", "кот", "cat");
//        slovar.searchWord("ru", "кот");
        int m=0;
        String k = null;
        Scanner scanner=new Scanner(System.in);
        do {
            System.out.println("Для использования словаря выберите пункт меню");
            System.out.println("1. Показать имеющиеся словари");
            System.out.println("2. Показать показать все имеющиеся в словатрях слова с преводом");
            System.out.println("3. Добавить слова в словарь");
            System.out.println("4. Удалить словарь целеком");
            System.out.println("5. Добавить перевод имеющегося слова");
            System.out.println("6. Найти слово");
            System.out.println("7. Найти слово по начальной и конечной буквам");
            System.out.println("8. Найти слово, в котором есть пропущеные буквы");
            System.out.println("9. Вывести слова из словаря, зная количеству букв в них");

            m = slovar.mScanerInt();
            switch (m) {
                case 1:
                    slovar.printLang();
                    break;
                case 2:
                    slovar.printSlovarLang("en");
                    break;
                case 3:
                    ArrayList<String> arr = new ArrayList<String>();
                    System.out.println("Напишите название словаря, нажмите Enter, напишите слово, нажмите Enter и напишите перевод этого слова");
                    slovar.addWord(slovar.mScanerString(), slovar.mScanerString(), List.of(slovar.mScanerStringArr()));
                    System.out.println("Теперь словарь имеет такие слова");
                    slovar.printSlovarLang("en");
                    break;
                case 4:
                    System.out.println("Сейчас имеются следующие словари:");
                    slovar.printLang();
                    System.out.println("Введите номер словаря, который хотите удалить");
                    m = slovar.mScanerInt();
                    switch (m) {
                        case 1:
                            slovar.removeLang("en");
                            System.out.println("Теперь остались следующие словари, содержащие такие слова");
                            slovar.printSlovarLang("en");
                            break;

                        case 2:
                            slovar.removeLang("ru");
                            System.out.println("Теперь остались следующие словари, содержащие такие слова");
                            slovar.printLang();
                            slovar.printSlovarLang("en");
                            break;
                    }
                    break;
                case 5:
                    System.out.println("В какой словарь вы хотите добавить перевод слова?");
            String langword =scanner.nextLine();
                System.out.println("Введите слово, перевод которого хотите дополнить");
            String originalWord =scanner.nextLine();
                System.out.println("Ведите новый перевод этого слова");
                    String translateWord =scanner.nextLine();
                    slovar.addWord(langword, originalWord, translateWord);
                    break;
                case 6:
                    System.out.println("Ввдите название словаря где хотите найти слово");
                String langWordSearch =scanner.nextLine();
                System.out.println("Введите искомое слово");
                String originalWordSearch =scanner.nextLine();
                    slovar.searchWord(langWordSearch, originalWordSearch);
                    break;
                case 7:
                    System.out.println("Ввдите название словаря где хотите найти слово");
                    String lang =scanner.nextLine();
                    System.out.println("Введите искомое слово в таком формате: первая буква * последняя буква");
                    String newWord =scanner.nextLine();
                    slovar.printWordSearch(lang, newWord);
                    break;
                case 8:
                    System.out.println("Ввдите название словаря где хотите найти слово");
                    String langguage =scanner.nextLine();
                    System.out.println("Введите искомое слово, вместо неизвеестных букв введите _, но обязатьльно введите первую и последнюю буквы ");
                    String newWord_ =scanner.nextLine();
                    slovar.printWordSize(langguage,newWord_);
                    break;
                case 9:
                    System.out.println("Ввдите название словаря где хотите найти слова");
                    String langP =scanner.nextLine();
                    System.out.println("Введите _ столько раз, сколько букв в искомом слове и нажмите Enter");
                    String newWordP =scanner.nextLine();
                    slovar.printWordArr(langP, newWordP);
                    break;
            }
        }while (true);
}}
