package org.example;

//import java.util.Scanner;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class WordManager {

  //  Scanner s = new Scanner(System.in);
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    WordCRUD wordCRUD;

    WordManager(){
        wordCRUD = new WordCRUD(br);
    }
    public int selectMenu(){
        try {
            bw.write("""
                    *** 영단어 마스터 ***
                    ********************
                    1. 모든 단어 보기
                    2. 수준별 단어 보기
                    3. 단어 검색
                    4. 단어 추가
                    5. 단어 수정
                    6. 단어 삭제
                    7. 파일 저장
                    0. 나가기
                    ********************
                    => 원하는 메뉴는?\s""");
            bw.flush();
            return Integer.parseInt(br.readLine());
        }catch (IOException e){
            throw new RuntimeException("숫자만 가능합니다!");
        }

    }

    public void start(){
        while (true) {
            int menu = selectMenu();
            if(menu == 0) {
                try {
                    bw.close();
                    break;//0을 입력 받으면 실행종료
                }catch (IOException e){
                    throw new RuntimeException("flush,close 에러");
                }
            }
            if(menu == 4){//4를 입력 받으면 단어 추가
                wordCRUD.addWord();
            }
            else if(menu == 1){//1을 입력 받으면 단어리스트를 보여줌
                wordCRUD.listAll();
            }
        }
    }
}
