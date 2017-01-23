package com.project.dao;

import java.util.List;

import com.project.model.Friend;

public interface FriendDao {
List<Friend> getAllFriends(String username);
void sendFriendRequest(String from,String to);
List<Friend> getPendingRequest(String username);
void updatePendingRequest(char friendStatus,String fromId,String toId);
void updatePendingf(char friendStatus, String fromId, String username);
void updatePendingt(char friendStatus, String toId, String username);
List<Friend> getbFriends(String username);
public int countrequests(String toId);
}