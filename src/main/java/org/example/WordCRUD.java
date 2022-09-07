package org.example;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import java.util.ArrayList;
import java.util.StringTokenizer;

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
            bw.newLine();
            bw.write("=> 난이도(1,2,3) & 새 단어 입력 : ");
            bw.flush();
            int level = br.read()-48;
            String word = br.readLine();

            bw.write("뜻 입력 : ");
            bw.flush();
            String meaning = br.readLine();
            bw.newLine();
            return new Word(0, level, word, meaning);//Object로 리턴
        }catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void addItem(){
        Word one = (Word) add();//(Word type)add함수 실행
        list.add(one);
        try {
            bw.write("새 단어가 단어장에 추가되었습니다. \n");
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
            bw.newLine();
            bw.write("-----------------------------------\n");
            bw.flush();
            for(int i = 0; i < list.size(); i++){
                bw.write((i+1) + " ");
                bw.flush();
                bw.write(list.get(i).toString());
                bw.newLine();
                bw.flush();
            }
            bw.write("-----------------------------------\n");
            bw.newLine();
            bw.flush();
        }catch (IOException e){
            throw new RuntimeException("listAll() 파일에서 오류 발생");
        }

    }
    public ArrayList<Integer> listAll(String keyword){
        try {
            ArrayList<Integer> idlist = new ArrayList<>();
            int j = 0;
            bw.newLine();
            bw.write("-----------------------------------\n");
            bw.flush();
            for(int i = 0; i < list.size(); i++){
                String word =list.get(i).getWord();
                if(word.contains(keyword)) {
                    bw.write((j + 1) + " ");
                    bw.flush();
                    bw.write(list.get(i).toString());
                    bw.newLine();
                    bw.flush();
                    idlist.add(i);
                    j++;
                }
            }
            bw.write("-----------------------------------\n");
            bw.newLine();
            bw.flush();
            return idlist;
        }catch (IOException e){
            throw new RuntimeException("listAll() 파일에서 오류 발생");
        }

    }

    public void updateItem() throws IOException {
        bw.write("=> 수정할 단어 검색 : ");
        bw.flush();
        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        String keyword = str.nextToken();
        ArrayList<Integer> idlist = this.listAll(keyword);
        bw.write("=> 수정할 번호 선택 : ");
        bw.flush();
        int id = Integer.parseInt(br.readLine());
        bw.write("=> 뜻 입력 : ");
        bw.flush();
        String meaning = br.readLine();
        Word word = list.get(idlist.get(id-1));
        word.setMeaning(meaning);
        bw.write("단어가 수정되었습니다.\n");
        bw.flush();
    }

    public void deleteItem() throws IOException {
        bw.write("=> 삭제할 단어 검색 : ");
        bw.flush();
        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        String keyword = str.nextToken();
        ArrayList<Integer> idlist = this.listAll(keyword);
        bw.write("=> 삭제할 번호 선택 : ");
        bw.flush();
        int id = Integer.parseInt(br.readLine());
        bw.newLine();


        bw.write("=> 정말로 삭제하시겠습니까?(Y/n) ");
        bw.flush();
        str = new StringTokenizer(br.readLine(), " ");
        String ans = str.nextToken();
        if(ans.equalsIgnoreCase("y")){
            list.remove((int)idlist.get(id-1));
            bw.write("단어가 삭제되었습니다.\n");
            bw.flush();
        } else{
            bw.write("취소되었습니다. \n");
            bw.flush();
        }
        }
}
