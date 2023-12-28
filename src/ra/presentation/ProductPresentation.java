package ra.presentation;

import ra.bussiness.IBussiness;
import ra.bussiness.ProductBussiness;
import ra.bussiness.userBussiness;
import ra.entity.Product;

import java.util.List;
import java.util.Scanner;

public class ProductPresentation {
    private static IBussiness productBussiness = new ProductBussiness();
    public static void productMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("******************PRODUCT MANAGEMENT****************\n" +
                    "1. Danh sách sản phẩm\n" +
                    "2. Thêm mới sản phẩm\n" +
                    "3. Cập nhật sản phẩm\n" +
                    "4. Tìm kiếm sản phẩm\n" +
                    "5. Cập nhật trạng thái sản phẩm\n" +
                    "6. Thoát");
            System.out.println("lựa chọn của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        List<Product> listProduct = productBussiness.getAll();
                        listProduct.stream().forEach(System.out::println);
                        break;
                    case 2:
                        Product product = new Product();
                        product.inputData(scanner, productBussiness);
                        boolean resultCreate = productBussiness.create(product);
                        if (resultCreate) {
                            System.out.println("thêm mới thành công!");
                        }else {
                            System.err.println("thêm mới thất bại!");
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        isExit = false;
                        break;
                    default:
                        System.out.println("vui lòng chọn từ 1 - 8!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }while (isExit);
    }
}
