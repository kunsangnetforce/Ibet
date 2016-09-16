package com.netforceinfotech.ibet.dashboard.home.create_bet.searchfriend;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SearchFriendData {
    public String id, name, profilepic;

    SearchFriendData(String id, String name, String profilepic) {
        this.id = id;
        this.name = name;
        this.profilepic = profilepic;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SearchFriendData)) {
            return false;
        }

        SearchFriendData that = (SearchFriendData) obj;
        return this.id.equals(that.id);
    }
}
