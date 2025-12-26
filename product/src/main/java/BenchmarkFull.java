import com.t2406e.product.dao.ProductDAO;
import com.t2406e.product.model.Product;

import java.util.*;

public class BenchmarkFull {

    private static final int LOOP = 500_000; // số vòng lặp lớn để mô phỏng tải

    public static void main(String[] args) {

        System.out.println("=== BENCHMARK START ===");

        testHashMap();
        testLinkedHashMap();
        testProductDAOGetAll();
        testProductDAOGetById();

        System.out.println("=== BENCHMARK END ===");
    }

    /* ================= HASHMAP ================= */
    private static void testHashMap() {
        Map<Integer, Integer> map = new HashMap<>();

        long startPut = System.nanoTime();
        for (int i = 0; i < LOOP; i++) {
            map.put(i, i);
        }
        long endPut = System.nanoTime();

        long startGet = System.nanoTime();
        for (int i = 0; i < LOOP; i++) {
            map.get(i);
        }
        long endGet = System.nanoTime();

        long startIter = System.nanoTime();
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {}
        long endIter = System.nanoTime();

        System.out.println("\n--- HashMap ---");
        System.out.println("Put:  " + (endPut - startPut) / 1_000_000 + " ms");
        System.out.println("Get:  " + (endGet - startGet) / 1_000_000 + " ms");
        System.out.println("Iter: " + (endIter - startIter) / 1_000_000 + " ms");
    }

    /* ================= LINKEDHASHMAP ================= */
    private static void testLinkedHashMap() {
        Map<Integer, Integer> map = new LinkedHashMap<>();

        long startPut = System.nanoTime();
        for (int i = 0; i < LOOP; i++) {
            map.put(i, i);
        }
        long endPut = System.nanoTime();

        long startGet = System.nanoTime();
        for (int i = 0; i < LOOP; i++) {
            map.get(i);
        }
        long endGet = System.nanoTime();

        long startIter = System.nanoTime();
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {}
        long endIter = System.nanoTime();

        System.out.println("\n--- LinkedHashMap ---");
        System.out.println("Put:  " + (endPut - startPut) / 1_000_000 + " ms");
        System.out.println("Get:  " + (endGet - startGet) / 1_000_000 + " ms");
        System.out.println("Iter: " + (endIter - startIter) / 1_000_000 + " ms");
    }

    /* ================= DAO GET ALL ================= */
    private static void testProductDAOGetAll() {
        ProductDAO dao = new ProductDAO();

        long start = System.nanoTime();
        List<Product> list = dao.getAll();
        long end = System.nanoTime();

        System.out.println("\n--- productDAO.getAll() ---");
        System.out.println("Rows returned: " + list.size());
        System.out.println("Time: " + (end - start) / 1_000_000 + " ms");
    }

    /* ================= DAO GET BY ID ================= */
    private static void testProductDAOGetById() {
        ProductDAO dao = new ProductDAO();

        long start = System.nanoTime();
        for (int i = 1; i <= 1000; i++) {
            dao.getById(3); // đo tốc độ đọc cache
        }
        long end = System.nanoTime();

        System.out.println("\n--- productDAO.getById() (1000 lần) ---");
        System.out.println("Time: " + (end - start) / 1_000_000 + " ms");
    }
}
