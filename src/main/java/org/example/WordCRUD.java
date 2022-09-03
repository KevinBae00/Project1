package org.example;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import java.util.ArrayList;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    BufferedReader br;
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    WordCRUD(BufferedReader br){
        list = new ArrayList<>();
        this.br = br;
    }

    @Override
    public Object add() {
        try {
            bw.write("=> 난이도(1,2,3) & 새 단어 입력 : ");
            bw.flush();
            int level = br.read()-48;
            String word = br.readLine();

            bw.write("뜻 입력 : ");
            bw.flush();
            String meaning = br.readLine();
            return new Word(0, level, word, meaning);//Object로 리턴
        }catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void addWord(){
        Word one = (Word) add();//(Word type)add함수 실행
        list.add(one);
        try {
            bw.write("새 단어가 단어장에 추가되었습니다. ");
            bw.newLine();
            bw.flush();
        }catch (IOException e){
            throw new RuntimeException("단어 출력 오류");
        }
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }
    public void listAll(){
        try {
            bw.write("-----------------------------------");
            bw.newLine();
            bw.flush();
            for(int i = 0; i < list.size(); i++){
                bw.write((i+1) + " ");
                bw.flush();
                bw.write(list.get(i).toString());
                bw.newLine();
                bw.flush();
            }
            bw.write("-----------------------------------");
            bw.newLine();
            bw.flush();
        }catch (IOException e){
            throw new RuntimeException("listAll() 파일에서 오류 발생");
        }

    }
}
