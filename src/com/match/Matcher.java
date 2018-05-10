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

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        String content1 = "职位诱惑：\n" +
                "五险一金,大神带领,团建,公司环境好\n" +
                "\n" +
                "职位描述：\n" +
                "岗位职责：\n" +
                "\n" +
                "1、负责iRAS软件的设计开发，包括文档编写与模块开发；\n" +
                "\n" +
                "2、负责相关模块的优化及日常维护。\n" +
                "\n" +
                "\n" +
                "\n" +
                "职位要求：\n" +
                "\n" +
                "1、计算机或相关专业，熟练掌握C++或VC++；\n" +
                "\n" +
                "2、了解COM、STL等技术，熟悉MFC以及第三方UI框架；\n" +
                "\n" +
                "3、熟练使用Visual Studio开发工具；\n" +
                "\n" +
                "4、熟悉Socket、多线程等编程技术；\n" +
                "\n" +
                "5、熟悉tcp、http等协议；\n" +
                "\n" +
                "6、有高并发、性能调优经验优先；有远程桌面开发工作经验优先；\n" +
                "\n" +
                "7、编码风格严谨，自学能力强，能较快掌握新知识；能够独立分析问题并解决问题；具有较好的质量意识和文档编写能力；";
        String content2 = "【职责】 \n" +
                "\n" +
                "·         交易系统的设计，实现和开发\n" +
                "\n" +
                "·         使用先进的 C++ 技术进行规划、设计和实施高质量的 Linux 系统\n" +
                "\n" +
                "·         使用敏捷方法构建软件\n" +
                "\n" +
                "·         参与系统设计和代码评审\n" +
                "\n" +
                "·         和不同团队合作去解决复杂问题\n" +
                "\n" +
                " \n" +
                "\n" +
                "【要求】 \n" +
                "\n" +
                "·         1-8年以上的C++开发工作经验，熟悉Linux 和Python, 对写出高性能的程序有极致追求\n" +
                "\n" +
                "·         对C++ 14等技术具有浓厚兴趣\n" +
                "\n" +
                "·         本科学历及其以上，计算机科学，工程类相关专业\n" +
                "\n" +
                "·         熟悉网络，多线程，内存管理，分布式系统开发，有高性能，低延时系统开发经验优先\n" +
                "\n" +
                "·         熟悉Kernel的调度，内存，网络子系统，有内核开发，调试，优化\n" +
                "\n" +
                "·         流利的英文沟通\n" +
                "\n" +
                "·         熟悉对象数据库等相关技术\n" +
                "\n" +
                "·         对金融科技充满兴趣";
        String content3 = loadRecruit();
        int count1 = match(content1, content3);
        int count2 = match(content2, content3);
        if (count1 > count2) {
            System.out.println("简历与岗位1更匹配");
        } else {
            System.out.println("简历与岗位2更匹配");
        }
    }
}
