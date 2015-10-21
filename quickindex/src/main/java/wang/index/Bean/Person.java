package wang.index.Bean;

import java.util.Comparator;

import wang.index.util.PinyinUtils;

/**
 * 公司:ihealth
 * 创建日期: 15/10/8 上午9:54
 * 作者:wanghao
 * 描述:
 */
public class Person implements Comparable<Person>{
    private  String name;
    private  String pinyin;

    public Person(String name) {
        this.name = name;
        this.pinyin = PinyinUtils.getPinyin(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public String getPinyin() {
        return pinyin;
    }

    @Override
    public int compareTo(Person another) {
        return this.pinyin.compareTo(another.getPinyin());
    }
}
