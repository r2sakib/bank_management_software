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
            if (bankerList.get(i).getNid().equals(nid)) {
                bankerList.remove(i);
            }
        }
    }

    public Banker getBankerByNid(String nid) {
        for (int i = 0; i < bankerList.size(); i++) {
            if (bankerList.get(i).getNid().equals(nid)) {
                return bankerList.get(i);
            }
        }
        return null;
    }

    public Banker getBankerByEmail(String email) {
        for (int i = 0; i < bankerList.size(); i++) {
            if (bankerList.get(i).getEmail().equals(email)) {
                return bankerList.get(i);
            }
        }
        return null;
    }

    public String bankersToString() {
        String str = "";
        for (int i = 0; i < bankerList.size(); i++) {
            Banker banker = bankerList.get(i);
            str += banker.getName() + ",";
            str += banker.getNid() + ",";
            str += banker.getBirthYear() + ",";
            str += banker.getAddress() + ",";
            str += banker.getMobileNumber() + ",";
            str += banker.getEmail() + ",";
            str += banker.getPassword() + ",";
            str += banker.getJobTitle();
            str += "\n";
        }
        return str;
    }

    public boolean isValid(String email, String password) {
        boolean valid = false;
        for (int i = 0; i < bankerList.size(); i++) {
            if (bankerList.get(i).getEmail().equals(email) && bankerList.get(i).getPassword().equals(password)) {
                valid = true;
            }
        }
        return valid;
    }

    public void clear() {
        bankerList.clear();
    }
    
}
