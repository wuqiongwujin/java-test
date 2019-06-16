package Algorithm.wordGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Description 字谜游戏
 * @date 2019/3/12
 */
public class WordGame {
    private static final String[] words = {"school", "driver", "girl", "phone","word"};

    private static char[][] panel = new char[5][5];

    private void createPanel() {
        List<Character> chars = new ArrayList<>();
        for (int i=0; i<words.length; i++) {
            String word = words[i];
            char[] letters = word.toCharArray();
            for (int j=0; j<letters.length; j++) {
                chars.add(letters[j]);
            }
        }
        Random random = new Random(47);
        for (int i=0; i<panel.length; i++) {
            for (int j=0; j<panel[i].length; j++) {
                int charsIndex = random.nextInt(chars.size());
                panel[i][j] = chars.get(charsIndex);
                chars.remove(charsIndex);
            }
        }
    }

    private void findWord() {
        for (int i=0;i<panel.length; i++) {
            char[] wordChars = panel[i];
            if (isCharsAllInWord(wordChars)) {
                System.out.println("第"+i+"行:"+Arrays.toString(wordChars));
            }
        }
    }

    private boolean isCharsAllInWord(char[] chars) {
        for (int i=0;i<words.length; i++) {
            String word = words[i];
            boolean match = true;
            for (int j=0;j<chars.length;j++){
                if (!word.contains(String.valueOf(chars[j]))) {
                    match= false;
                    break;
                }
            }
            if (match) {
                return match;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordGame wordGame = new WordGame();
        wordGame.createPanel();
        System.out.println(Arrays.deepToString(panel));
        wordGame.findWord();
    }
}
