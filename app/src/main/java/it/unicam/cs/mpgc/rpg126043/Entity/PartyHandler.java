package it.unicam.cs.mpgc.rpg126043.Entity;

import it.unicam.cs.mpgc.rpg126043.Entity_Object.*;
import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.util.ArrayList;
import java.util.List;

public class PartyHandler {
    GamePanel gP;
    List<Party_Member> party = new ArrayList<>();
    Party_Member leader;
    Party_Member tank;
    Party_Member healer;
    Party_Member mage;
    public PartyHandler(GamePanel gP) {
        this.gP = gP;

        addMember( this.leader = new Party_Member(gP,"Player",5,14,9,1,new OBJ_Sword_Normal(gP) ,new OBJ_Shield_Wood(gP),true,false));
        addMember(this.tank = new Party_Member(gP,"Tank",3,20,14,1,new OBJ_Axe(gP),new OBJ_Iron_Shield(gP),false,false));
        addMember(this.healer = new Party_Member(gP,"Healer",1,16,5,1,new OBJ_Scepter(gP),new OBJ_Shield_Wood(gP),false,false));
        addMember(this.mage = new Party_Member(gP,"Mage",10,10,5,1,new OBJ_Scepter(gP),new OBJ_Shield_Wood(gP),false,false));
    }
    public void addMember(Party_Member member){
        party.add(member);
    }
    public void removeMember(Party_Member member){
        if(!member.isLeader){
            party.remove(member);
        }
    }
    public int getPartySize(){
        return party.size();
    }
    public List<Party_Member> getParty(){
        return party;
    }

    public boolean isLeader(int i){
        return party.get(i).isLeader;
    }
}
