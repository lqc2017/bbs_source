package grp3022.bbs.service;

import java.util.List;

import grp3022.bbs.po.Browse;
import grp3022.bbs.po.BrowseKey;

public interface BrowseService {
	
	Browse getByKey(BrowseKey key);
	
	void updateByKey(Browse record);
	
	void add(Browse record);
	
	List<Browse> getBrowseByUserId(long userId);
}
