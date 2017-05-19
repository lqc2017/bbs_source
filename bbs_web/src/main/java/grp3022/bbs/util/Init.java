package grp3022.bbs.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;

import grp3022.bbs.type.Tag;

public final class Init {
	/**
	 * 2017年5月14日 下午1:55:40
	 * 
	 * @param date
	 * @return
	 */
	public final static List<Tag> InitTagList(String JSONTags) {
		List<Tag> tags = new ArrayList<Tag>();
		List<Integer> indexes = JSON.parseArray(JSONTags, Integer.class);
		for (int index : indexes) {
			for (Tag tag : Tag.values()) {
				if (tag.getIndex() == index) {
					tags.add(tag);
				}
			}
		}
		return tags;
	}
}
