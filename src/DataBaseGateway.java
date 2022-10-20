import java.util.*;

public class DataBaseGateway {

    int amountOfAccesses = 0;
    BloomFilter bf = new BloomFilter();
    Set<String> dataBase = new HashSet<>();

    boolean isBloomFilterEnabled = false;

    boolean test(String id) {
        if(isBloomFilterEnabled) {
            if(!bf.test(id)){
                return false;
            }
        }
        amountOfAccesses++;
        return dataBase.contains(id);
    }

    void add(String id){
        if(isBloomFilterEnabled) {
            bf.add(id);
        }
        amountOfAccesses++;
        dataBase.add(id);
    }


}
