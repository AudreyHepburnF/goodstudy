package com.goodstudy.interview.dataStructure;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  9:12 2019-06-21
 * @Description :请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class StringReplace {

    public static void main(String[] args) {
        StringReplace stringReplace = new StringReplace();
        StringBuffer str =new StringBuffer( "We Are Happy");
        System.out.println(stringReplace.replaceSpace(str));
    }

    public String replaceSpace(StringBuffer str) {
        int spaceNum = 0; // 计算空格数
        for(int i = 0;i<str.length();i++){
            if(str.charAt(i) == ' '){ // 包含空字符串
                spaceNum ++;
            }
        }
        int oldNumIndex = str.length()-1; // 原字符串的最大索引
        int newNum = str.length() + spaceNum *2; // 新字符串的长度
        int newNumIndex = newNum -1; // 新字符串的最大索引
        str.setLength(newNum);
        // 遍历原字符串(递减遍历)
        for(;oldNumIndex>0 && oldNumIndex<newNum ; --oldNumIndex){
            // 原字符串为空，进行反向操作新字符串
            if(str.charAt(oldNumIndex) == ' '){
                str.setCharAt(newNumIndex--, '0');
                str.setCharAt(newNumIndex--, '2');
                str.setCharAt(newNumIndex--, '%');
            }else{
                str.setCharAt(newNumIndex--, str.charAt(oldNumIndex));
            }
        }
        return str.toString();
    }
}
