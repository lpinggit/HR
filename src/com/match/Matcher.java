package com.match;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sykus on 2018/5/9.
 */
public class Matcher {
    private Matcher() {
    }

    /**
     * 根据本地职位库匹配content1和content2
     *
     * @param content1
     * @param content2
     * @return
     */
    public static int match(String content1, String content2) {
        ArrayList<String> pos = loadPos();//本地职位库
        ArrayList<String> seg1 = segment(content1);//对content1分词
        ArrayList<String> seg2 = segment(content2);//对content2分词
        boolean bitmap1[] = new boolean[pos.size()];
        boolean bitmap2[] = new boolean[pos.size()];

        System.out.println("seg1 大小" + seg1.size());
        for (int i = 0; i < seg1.size(); i++) {
            String word = seg1.get(i).toUpperCase();
            for (int j = 0; j < pos.size(); j++) {
                String p = pos.get(j).toUpperCase();
                if (word.contains(p) || p.contains(word)) {
                    bitmap1[j] = true;
                }
            }
        }

        System.out.println("seg2 大小" + seg2.size());
        for (int i = 0; i < seg2.size(); i++) {
            String word = seg2.get(i).toUpperCase();
            for (int j = 0; j < pos.size(); j++) {
                String p = pos.get(j).toUpperCase();
                if (word.contains(p) || p.contains(word)) {
                    bitmap2[j] = true;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < bitmap1.length; i++) {
            bitmap1[i] = bitmap1[i] & bitmap2[i];
            if (bitmap1[i]) {
                ++count;
            }
        }
        System.out.println("匹配数" + count);

        return count;
    }


    /**
     * 加载简历
     *
     * @return
     */
    public static String loadRecruit() {
        StringBuffer rec = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("data/recruit.txt")));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                rec.append(line).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rec.toString();
    }

    /**
     * 加载本地岗位库
     *
     * @return
     */
    public static ArrayList<String> loadPos() {

        ArrayList<String> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("data/pos.txt")));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 调用jieba库对content分词
     *
     * @param content
     * @return
     */
    public static ArrayList<String> segment(String content) {
        JiebaSegmenter segmenter = new JiebaSegmenter();
        ArrayList<String> words = new ArrayList<>();
        List<SegToken> tokens = segmenter.process(content, JiebaSegmenter.SegMode.SEARCH);
        for (SegToken token : tokens) {
            String key = token.word.trim();
            if (filter(key)) continue;
//            System.out.println(key);
            words.add(key);
        }
        return words;
    }

    private static boolean filter(String str) {
        if (str.equals("") || str.equals("-") || str.equals("，") || str.equals("；") || str.equals("。") || str.equals("、") || str.equals("：") || str.equals("/")
                || str.equals("（") || str.equals("）")) {
            return true;
        }
        return false;
    }

}
