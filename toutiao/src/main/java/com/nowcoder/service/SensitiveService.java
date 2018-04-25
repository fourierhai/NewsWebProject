package com.nowcoder.service;

import com.nowcoder.controller.IndexController;
import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 敏感词过滤
 */

@Service
public class SensitiveService implements InitializingBean{
    private static final Logger logger = LoggerFactory.getLogger(SensitiveService.class);

    //将需要过滤的词库 初始化
    @Override
    public void afterPropertiesSet() throws Exception {
        try{
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("SensitiveWord.txt");
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String lineTxt;

            while((lineTxt = bufferedReader.readLine()) !=null ){
                addWord(lineTxt.trim());
            }
            reader.close();

        }catch (Exception e){
            logger.error("读取敏感词文件失败"+e.getMessage());
        }

    }


    // 增加关键词
    private void addWord(String lineTxt){
        TrieNode tempNode = rootNode;
        for(int i = 0; i < lineTxt.length();i++){
            Character c = lineTxt.charAt(i);

            TrieNode node = tempNode.getSubNode(c);

            if(node ==null){
                node = new TrieNode();
                tempNode.addSubNode(c,node);
            }
            tempNode = node;

            if(i==lineTxt.length()-1){
                tempNode.setkeywordEnd(true);
            }

        }
    }

    /**
     * 前缀树 （字典树）
     */
    private class TrieNode{
        //是不是关键词的结尾
        private boolean end = false;
        // 当前节点下所有的子节点
        private Map<Character,TrieNode> subNodes = new HashMap<Character,TrieNode>();
        public void addSubNode(Character key, TrieNode node){
            subNodes.put(key,node);
        }

        public TrieNode getSubNode(Character key){
            return subNodes.get(key);
        }

        public boolean isKeyWordEnd(){
            return end;
        }

        void setkeywordEnd(boolean end){
            this.end = end;
        }
    }

    // 树的根
    private TrieNode rootNode = new TrieNode();

    // 判断c是否是很奇怪的字或者是空格
    private boolean isSymbol(char c){
        int ic = (int) c;
        // 东亚文字  0x2E80-0x9FFF
        return !CharUtils.isAsciiAlphanumeric(c)&&(ic<0x2E80 || ic>0x9FFF);
    }

    public String filter(String text){
        if(StringUtils.isBlank(text)){
            return text;
        }

        StringBuilder result = new StringBuilder();

        String replacement = "***";
        //字典树指针
        TrieNode tempNode = rootNode;
        // 指针2
        int begin = 0;
        // 指针3
        int position = 0;

        while(position < text.length()){
            char c = text.charAt(position);
            if(isSymbol(c)){
                if(tempNode == rootNode){
                    result.append(c);
                    begin++;
                }
                position++;
                continue;
            }

            tempNode = tempNode.getSubNode(c);
            //如果没有以c开头的敏感词
            if(tempNode == null ){
                result.append(text.charAt(begin));
                position = begin+1;
                begin = position;
                tempNode =rootNode;
            }else if(tempNode.isKeyWordEnd()){
                //发现敏感词
                //打码
                result.append(replacement);
                position = position + 1;
                begin = position;
                tempNode = rootNode;
            }else{
                // 继续往下遍历
                position++;
            }

        }
        result.append(text.substring(begin));
        return result.toString();
    }
}
