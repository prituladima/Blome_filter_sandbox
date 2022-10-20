import java.util.*;

public class Testing1 {


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int testCases = 10;
        for (int test = 1; test <= testCases; test++) {
            System.out.printf("-----Test begin %d-----\n", test);
            //            int size = 100_000_000;
            int size = 1_000_000;
            String[] array = new String[size];
            for (int i = 0; i < size; i++) {
                array[i] = UUID.randomUUID().toString();
            }
            //data base init
            DataBaseGateway dataBaseGateway = new DataBaseGateway();
            dataBaseGateway.isBloomFilterEnabled = test % 2 == 0;
            for (int i = 0; i < size; i++) {
                dataBaseGateway.add(array[i]);
            }
            int start = dataBaseGateway.amountOfAccesses;
            //data test
            Random r = new Random(test);
            int p = 0;
            for (int i = 0; i < 500_000; i++) {
                int prob = r.nextInt(110);
                if (prob < 80) {
                    //read not existing
                    String randomUUID = UUID.randomUUID().toString();
                    dataBaseGateway.test(randomUUID);
                } else if (prob < 100) {
                    //read existing
                    dataBaseGateway.test(array[p]);
                } else {
                    //write non-existing
                    String randomUUID = UUID.randomUUID().toString();
                    dataBaseGateway.add(randomUUID);
                }
            }
            int end = dataBaseGateway.amountOfAccesses;
            System.out.println(end - start);
            list.add(end - start);
            System.out.println((dataBaseGateway.isBloomFilterEnabled ? "With " : "Without ") + "Bloom filter");
            System.out.printf("-----Test end %d-----\n", test);
        }
        for (int i : list) {
            System.out.println(i);
        }
    }
}