package mwang.online.hot100;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/22 09:43
 * @description: No208_TrieWords
 * <p>
 * 前缀数、字典树
 */
public class No208_TrieWords {

    No208_TrieWords[] children;
    boolean isEnd;

    public No208_TrieWords() {
        this.children = new No208_TrieWords[26];
    }

    public void insert(String word) {
        No208_TrieWords cur = this;
        for (int i = 0; i < word.length(); i++) {
            final char c = word.charAt(i);
            int index = c - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new No208_TrieWords();
            }
            cur = cur.children[index];
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        No208_TrieWords cur = this;
        for (int i = 0; i < word.length(); i++) {
            final char c = word.charAt(i);
            int index = c - 'a';
            if (cur.children[index] == null) return false;
            cur = cur.children[index];
        }
        return cur.isEnd;
    }

    public boolean startsWith(String prefix) {
        No208_TrieWords cur = this;
        for (int i = 0; i < prefix.length(); i++) {
            final char c = prefix.charAt(i);
            int index = c - 'a';
            if (cur.children[index] == null) return false;
            cur = cur.children[index];
        }
        return true;
    }

    public static void main(String[] args) {
        final No208_TrieWords test = new No208_TrieWords();
        test.insert("app");
        test.insert("apple");
        System.out.println(test.search("apps"));
        System.out.println(test.startsWith("app"));
    }
}
