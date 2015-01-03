package com.imalu.alyou.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConversationGroups {
	
	private List<ConversationGroup> grouplist;
	
	public ConversationGroups() {
		if (grouplist == null) {
			grouplist = new ArrayList<ConversationGroup>();
        }
	}
	
	public void addConversationGroup(ConversationGroup group){
		if (grouplist == null) {
			grouplist = new ArrayList<ConversationGroup>();
        }
		grouplist.add(group);
	}
	
	public List<ConversationGroup> getConversationGroupList(){
		if (grouplist == null) {
			grouplist = new ArrayList<ConversationGroup>();
        }
		return grouplist;
	}
	
	public ConversationGroup getGroupByKey(String groupkey) {	
		for (Iterator<ConversationGroup> iter = grouplist.iterator(); iter.hasNext();) {
			ConversationGroup group = (ConversationGroup)iter.next();
			if( group.getGroupKey().equals(groupkey) ) {
				return group;
			}
		}
		return null;
	}

}
