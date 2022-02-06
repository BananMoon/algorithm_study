package moon;

import java.util.*;
/**
 * Music 정적 클래스 이용. (프로그래머스 통과)
 */
public class 해쉬_베스트앨범2 {
    public static class Music {
        String genre;
        int play;
        int idx;

        public Music(String genre, int play, int idx) {
            this.genre = genre;
            this.play = play;
            this.idx = idx;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap<>();
        // 1. 해쉬 추가 : <장르, play횟수 합>
        for (int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }

        // 2. 장르 내림차순  orderedGenres : (pop, classic)
        ArrayList<String> orderedGenres = new ArrayList<>();
        while(map.size()!=0){     // while (map.size() != 0)
            int max = -1;
            String maxKey = "";
            for (String key : map.keySet()) {
                int tmpCnt = map.get(key);
                if (tmpCnt > max) {
                    max = tmpCnt;
                    maxKey = key;
                }
            }
            // play가 큰 장르부터 add 후 제거
            orderedGenres.add(maxKey);
            map.remove(maxKey);
        }

        // 3. 장르 내 수록될 play 선정 (최대 2개, 내림차순)
        ArrayList<Music> orderedPlays = new ArrayList<>();
        ArrayList<Music> list;
        for (String genre : orderedGenres) {
            list = new ArrayList<>();
            for (int i=0; i< genres.length ;i++) {
                if (genre.equals(genres[i]))        // 내림차순된 장르명과 일치하면
                    list.add(new Music(genre, plays[i], i));     // Music 인스턴스를 추가
            }
            // 3-1. 한 장르 내에서 play 수에 따라 내림차순 정렬 후 1개 무조건 수록
            Collections.sort(list, (p1, p2) -> p2.play - p1.play);
            orderedPlays.add(list.get(0));
            // 3-2. 2개 이상있으면 2번째 노래도 추가
            if (list.size() > 1) {
                orderedPlays.add(list.get(1));
            }
        }

        // 5. result의 index 출력
        int[] answer = new int[orderedPlays.size()];
        for (int i=0; i< orderedPlays.size(); i++)
            answer[i] = orderedPlays.get(i).idx;
        return answer;

    }


    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        해쉬_베스트앨범2 k = new 해쉬_베스트앨범2();
        int[] answer = k.solution(genres, plays);
        for (int i :
                answer) {
            System.out.print(i + " ");        // 4 1 3 0
        }
    }
}
