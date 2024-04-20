package entityList;

import java.util.ArrayList;
import entity.person.Banker;

public class BankerList {
    private ArrayList <Banker> bankerList;

    public BankerList() {
        bankerList = new ArrayList<Banker>();
    }

    public void addBanker(Banker banker) {
        bankerList.add(banker);
    }

    public void removeBanker(String nid) {
        for (int i = 0; i < bankerList.size(); i++) {
            if (bankerList.get(i).getNid() == nid) {
                bankerList.remove(i);
            }
        }
    }

    public Banker getBankerByNid(String nid) {
        for (int i = 0; i < bankerList.size(); i++) {
            if (bankerList.get(i).getNid() == nid) {
                return bankerList.get(i);
            }
        }
        return null;
    }

    public Banker getBankerByEmail(String email) {
        for (int i = 0; i < bankerList.size(); i++) {
            if (bankerList.get(i).getEmail() == email) {
                return bankerList.get(i);
            }
        }
        return null;
    }
    
}
