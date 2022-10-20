public class BloomFilter {

    int m = Config.AMOUNT_OF_FILTERS;
    int n = Config.BLOOM_FILTER_SIZE;
    boolean[] values = new boolean[n];

    int hash(String key, int seed) {
        int hash = seed;
        for (int i = 0; i < key.length(); i++) {
            hash = 31 * hash + (int) key.charAt(i);
        }
        return hash;
    }

    int index(int hash) {
        if(hash == Integer.MIN_VALUE) return 0;
        return Math.abs(hash) % n;
    }

    boolean test(String id) {
        for (int filter = 0; filter < m; filter++) {
            if (!values[index(hash(id, filter))]) return false;
        }
        return true;
    }

    void add(String id) {
        for (int filter = 0; filter < m; filter++) {
            values[index(hash(id, filter))] = true;
        }
    }

}
