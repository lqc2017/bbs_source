package grp3022.bbs.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import grp3022.bbs.jo.Percentage;
import grp3022.bbs.po.Question;
import grp3022.bbs.type.Tag;

public final class TagUtil {
	/**
	 * 2017年5月14日 下午1:55:40
	 * 
	 * @param date
	 * @return
	 */
	public final static List<Tag> getTagListByIndex(List<Integer> indexes) {
		List<Tag> tags = new ArrayList<Tag>();
		for (int index : indexes) {
			for (Tag tag : Tag.values()) {
				if (tag.getIndex() == index) {
					tags.add(tag);
				}
			}
		}
		return tags;
	}
	
	/**
	 * 2017年5月21日 下午8:03:45
	 * @return
	 */
	public final static Map<Integer, Tag> getTagMap() {
		Map<Integer,Tag> tags = new HashMap<Integer,Tag>();
		for (Tag tag : Tag.values()) {
			tags.put(tag.getIndex(), tag);
		}
		return tags;
	}
	
	/**
	 * 2017年5月14日 下午1:55:40
	 * 
	 * @param date
	 * @return
	 */
	public final static List<Percentage> getTagPercentage(List<Question> questions,Integer denominator) {
		/*(On,n=tag.size)初始化percentsMap*/
		Map<Integer,Float> percentsMap = new HashMap<Integer,Float>();
		for(Tag tag:Tag.values()){
			percentsMap.put(tag.getIndex(), (float) 0);
		}
		/*(On2,n1=ql.size,n2=tag.size)遍历questions中的tag属性给percentsMap中的index(key)赋值，value为统计的个数*/
		for (Question question : questions) {
			List<Integer> indexes = JSON.parseArray(question.getTags(), Integer.class);
			if (indexes.size() > 0) {
				for (Integer index : indexes) {
					float newValue = percentsMap.get(index) + 1;
					percentsMap.put(index, newValue);
				}
			}
		}
		/*(On,n=tag.size)遍历percentsMap，value从CNT转化成百分比*/
		List<Percentage> percentsList = new ArrayList<Percentage>();
		for (Map.Entry<Integer, Float> entry : percentsMap.entrySet()) {
			if(entry.getValue()>0){
				float newValue = entry.getValue()/denominator;
				Percentage p = new Percentage(entry.getKey(),(float)(Math.round(newValue*1000)/1000.0));
				percentsList.add(p);
			}
		}
		
		/*從高到低排序*/
		Collections.sort(percentsList, new Comparator<Percentage>() {   
			@Override
			public int compare(Percentage o1, Percentage o2) {
				return (o2.getPercent().compareTo(o1.getPercent()));
			}
		});
		return percentsList;
	}
}
