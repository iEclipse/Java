
import java.util.ArrayList;
import java.util.LinkedList;

public class Structure {

    LinkedList<iNode> iNodes = new LinkedList();
    long blocks;
    int[] fat = new int[64];

    void put(String name, int size) {
        int index = 0;
        for (int i = 0; i < iNodes.size(); i++) {
            if (iNodes.get(i).name.equals(name)) {
                System.out.println("Error: iNode Already Exists.\n");
                return;
            }
        }
        if (size > 64 - Long.bitCount(blocks)) {
            System.out.println("Error: Insufficient Space on the FileSystem.\n");
            return;
        }
        iNodes.add(new iNode(name));

        while (size > 0) {
            if (((blocks >> index) & 1) == 0) {
                blocks = blocks ^ (1L << index);

                size--;
                iNodes.getLast().fat.add(index);
            }
            index++;
        }
    }

    void del(String name) {
        for (int i = 0; i < iNodes.size(); i++) {
            if (i + 1 == iNodes.size() && !iNodes.get(i).name.equals(name)) {
                System.out.println("Error: File Not Found.\n");
            } else if (iNodes.get(i).name.equals(name)) {
                for (int j = 0; j < iNodes.get(i).fat.size(); j++) {
                    blocks = blocks ^ (1 << iNodes.get(i).fat.get(j));
                }
                iNodes.remove(i);
            }
        }
    }

    void bitmap() {
        for (int i = 0; i < 64; i++) {
            if (i % 8 == 0) {
                System.out.printf("%2d ", i);
            }
            System.out.print((blocks >> i) & 1);
            if ((i + 1) % 8 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    void inodes() {
        for (int i = 0; i < iNodes.size(); i++) {
            System.out.print(iNodes.get(i).name + ": ");
            for (int j = 0; j < iNodes.get(i).fat.size();) {
                System.out.print(iNodes.get(i).fat.get(j));
                if (++j < iNodes.get(i).fat.size()) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private class iNode {

        String name;
        ArrayList<Integer> fat = new ArrayList();

        iNode(String name) {
            this.name = name;
        }
    }
}
