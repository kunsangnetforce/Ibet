package com.netforceinfotech.ibet.currentbet.betarena.betdetail;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class DetailBetData {
    String userdp,username,selectedteam,score,result;
    DetailBetData(String userdp, String username,String result,String selectedteam,String score){
        this.userdp=userdp;
        this.username=username;
        this.result=result;
        this.selectedteam=selectedteam;
        this.score=score;
    }
}
