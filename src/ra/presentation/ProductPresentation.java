package ra.presentation;

import ra.bussiness.IBussiness;
import ra.bussiness.ProductBussiness;
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
                        createProduct(scanner);
                        break;
                    case 3:
                        updateProduct(scanner);
                        break;
                    case 4:
                        searchProduct(scanner);
                        break;
                    case 5:
                        updateStatusProduct(scanner);
                        break;
                    case 6:
                        isExit = false;
                        break;
                    default:
                        System.out.println("vui lòng chọn từ 1 - 6!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }while (isExit);
    }

    public static void createProduct(Scanner scanner) {
        Product product = new Product();
        product.inputData(scanner, productBussiness);
        boolean resultCreate = productBussiness.create(product);
        if (resultCreate) {
            System.out.println("thêm mới thành công!");
        }else {
            System.err.println("thêm mới thất bại!");
        }
    }
    public static void updateProduct(Scanner scanner) {
        System.out.println("Mã của sản phẩm bạn muốn thay đổi:");
        String updateId = scanner.nextLine();

        if (updateId.trim().length() == 5) {
            Product product = (Product) productBussiness.findById(updateId);

            if(product != null) {
                product.updateData(scanner, productBussiness);
                boolean result = productBussiness.update(product);
                if (result) {
                    System.out.println("cập nhật thành công!");
                }else {
                    System.err.println("cập nhật thất bại!");
                }
            }else {
                System.err.println("mã sản phẩm không tồn tại!");
            }
        }else {
            System.err.println("mã sản phẩm phải có 5 kí tự!");
        }
    }

    public static void searchProduct(Scanner scanner) {
        System.out.println("Tên sản phẩm bạn muốn tìm kiếm:");
        String searchName = scanner.nextLine();

        List<Product> listProduct = productBussiness.searchName(searchName);

        if(listProduct.isEmpty()) {
            System.err.println("không tìm thấy sản phẩm!");
        }else {
            listProduct.stream().forEach(System.out::println);
        }
    }

    public static void updateStatusProduct(Scanner scanner) {
        System.out.println("Mã sản phẩm bạn muốn thay đổi:");
        String updateId = scanner.nextLine();

        if (updateId.trim().length() == 5) {
            Product product = (Product) productBussiness.findById(updateId);

            if(product != null) {
                product.updateDataStatus(scanner);
                boolean result = productBussiness.update(product);
                if (result) {
                    System.out.println("cập nhật thành công!");
                }else {
                    System.err.println("cập nhật thất bại!");
                }
            }else {
                System.err.println("mã sản phẩm không tồn tại!");
            }
        }else {
            System.err.println("mã sản phẩm phải có 5 kí tự!");
        }
    }
}
