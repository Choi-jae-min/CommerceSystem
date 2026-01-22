package org.commerce;

import java.util.Optional;
import java.util.Scanner;

public class CommerceSystem {
    private Category category;
    private final Scanner sc;
    Customer customer;
    Purchase purchase;
    Admin isAdmin;


    CommerceSystem() {
        System.out.println("어서오세요 성함과 이메일을 입력해주세요.");
        sc = new Scanner(System.in);
        System.out.print("고객님의 성함 : ");
        String customerName = sc.nextLine();
        System.out.print("고객님의 이메일 : ");
        String customerEmail = sc.nextLine();
        customer = new Customer(customerName , customerEmail);

        System.out.println("환영합니다" + customerName + "님!");
        System.out.println("즐거운 시간보내세요! ^^7");
    }

    ///  커머스 시스템 시작 메서드.
    public void start(){
        showMenu();
    }

    ///  실시간 커머스 플랫폼 기본 메뉴 출력 메서드.
    private void showMenu() {
        char menu;
        for (;;){
            System.out.println("[ 실시간 커머스 플랫폼 ]");
            System.out.println("원하시는 제품 카테고리를 선택 해 주세요.");
            System.out.println("1. 상품보기");
            System.out.println("2. 장바구니");
            System.out.println("3. 관리자모드");
            System.out.println("0. 종료    | 프로그램 종료");
            menu = sc.next().charAt(0);
            sc.nextLine();
            try {
                switch (menu){
                    case '1' -> shoppingProcess();
                    case '2' -> cartController();
                    case '3' -> {
                        loginToAdmin();
                        if(isAdmin.getIsLoin()){
                            // 관리자 메뉴 활성화.
                            showAdminMenu();
                        }
                    }
                    default -> System.out.println("잘못된 메뉴를 선택 하셨습니다.");
                }
            }catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            if(menu == '0'){
                System.out.println("프로그램을 종료합니다.");
                break;
            }
        }
    }

    private void showAdminMenu() {
        for (;;){
            if(!isAdmin.getIsLoin()){
                System.out.println("[ 관리자만 접근 할 수 있습니다. ]");
                break;
            }
            System.out.println("[ 관리자 화면 ]");
            System.out.println("원하는 기능을 선택하세요.");
            System.out.println("1. 상품 추가 | 2. 상품 수정 | 3. 상품 삭제 | 0. 관리자 모드 종료 ( 로그아웃 )");
            char adminMenu = sc.nextLine().charAt(0);
            switch (adminMenu){
                case '1' -> addProduct();
                case '2' -> updateProduct();
                case '3' -> removeProduct();
                default -> isAdmin.logout();
            }
        }
    }

    private void removeProduct(){
        category = new Category(CategoryType.ALL);
        System.out.println("=================================상품 삭제===============================");
        System.out.println(category);
        System.out.println("삭제할 상품명을 입력 해주세요.");
        String productName = sc.nextLine();
        boolean isValidName = category.productRepository.checkValidName(productName);
        if(isValidName){
            System.out.println("이미 존재하지 않는 상품입니다.");
        }else {
            System.out.println("정말 삭제 하시겠습니까? D 입력시 삭제. | 그외 취소");
            System.out.println("장바구니 안에 있는 제품도 함께 사라집니다.");
            String isRemove = sc.nextLine();
            if(isRemove.equals("d") || isRemove.equals("D")){
                String removedProductId = category.productRepository.removeProductByName(productName);
                customer.cart.removeItem(removedProductId);
                System.out.println("상품 제거 완료 : " + removedProductId);
            }
        }
    }

    private void updateProduct() {
        category = new Category(CategoryType.ALL);
        System.out.println("=================================상품 수정===============================");
        System.out.println(category);
        System.out.println("수정할 상품명을 입력 해주세요.");
        String productName = sc.nextLine();
        boolean isValidName = category.productRepository.checkValidName(productName);
        if(!isValidName){
            System.out.println("수정할 항목을 선택하세요 1. 가격 | 2. 설명 | 3. 수량");
            int updateMenu = sc.nextInt();
            switch (updateMenu){
                case 1 -> {
                    System.out.println("수정할 가격을 입력하세요.");
                    int newPrice = sc.nextInt();
                    category.productRepository.updatePriceByProductName(productName, newPrice);
                }
                case 2 -> {
                    System.out.println("새로운 설명을 입력 해주세요.");
                    String newDescription = sc.nextLine();
                    category.productRepository.updateDescriptionByProductName(productName, newDescription);
                }
                case 3 -> {
                    System.out.println("새로운 수량 입력 해주세요.");
                    int newCount = sc.nextInt();
                    category.productRepository.updateQuantityByProductName(productName, newCount);
                }
                default -> System.out.println("잘못된 메뉴입니디.");
            }
        }else {
            System.out.println("일치하는 상품이 없습니다.");
        }
    }

    private void addProduct(){
        category = new Category(CategoryType.ALL);
        System.out.println("등록할 상품의 ID 입력 해주세요.");
        String productId = sc.nextLine();

        System.out.println("등록할 상품의 이름을 입력 해주세요.");
        String productName = sc.nextLine();
        boolean isValidName = category.productRepository.checkValidName(productName);
        if(!isValidName){
            System.out.println("이미 등록된 상품입니다.");
            return;
        }
        System.out.println("가격을 설정해 주세요.");
        int productPrice = sc.nextInt();
        sc.nextLine();
        if(productPrice <= 0){
            System.out.println("가격은 0이하가 될 수 없습니다.");
            return;
        }
        System.out.println("상품의 설명을 작성해주세요.");
        String productDescription = sc.nextLine();

        System.out.println("수량을 입력 해주세요.");
        int productCount= sc.nextInt();
        if(productCount <= 0){
            System.out.println("수량은 0이하가 될 수 없습니다.");
            return;
        }

        System.out.println("어떤 카테고리에 입력할까요?");
        System.out.println("1. 전자 제품 | 2. 주방 용품 | 3. 음식 | 4. 음료");
        int productCategory = sc.nextInt();
        switch(productCategory){
            case 1:
                category.productRepository.addProduct(
                        new Product(
                                productId,
                                productName,
                                productPrice,
                                productDescription,
                                productCount,
                                CategoryType.ELECTRONIC.getCategoryType()
                        ));
                break;
            case 2:
                category.productRepository.addProduct(
                        new Product(
                                productId,
                                productName,
                                productPrice,
                                productDescription,
                                productCount,
                                CategoryType.KITCHEN.getCategoryType()
                        ));
                break;
            case 3:
                category.productRepository.addProduct(
                        new Product(
                                productId,
                                productName,
                                productPrice,
                                productDescription,
                                productCount,
                                CategoryType.FOOD.getCategoryType()
                        ));
                break;
            case 4:
                category.productRepository.addProduct(
                        new Product(
                                productId,
                                productName,
                                productPrice,
                                productDescription,
                                productCount,
                                CategoryType.BEVERAGE.getCategoryType()
                        ));
                break;
            default:
                System.out.println("존재 하지 않는 카테고리 입니다.");
                break;
        }
    }

    ///  관리자 로그인 흐름
    private void loginToAdmin() {
        isAdmin = new Admin();
        boolean isLogin = false;
        while (!isLogin){
            try {
                System.out.println("비밀번호를 입력 해 주세요.");
                String inputPassword = sc.nextLine();
                isLogin = isAdmin.login(customer.getName() , inputPassword);
            }catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }catch (RuntimeException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        System.out.println("환영합니다 관리자님!.");
    }

    /// 장바구니 서비스 컨트롤 입출력 흐름제어
    private void cartController() {
        Cart cart = customer.cart;
        if (cart.getItems().isEmpty()) {
            System.out.println("장바구니가 비어있습니다.");
            return;
        }
        System.out.println("=================================== 장바구니 =============================================");
        System.out.println(cart);

        System.out.println("1. 전체 상품 구매 | 2. 수량 조절 | 3. 상품 삭제 | 0. 뒤로");
        int cartMenu = sc.nextInt();
        if (cartMenu == 0) {
            return;
        }
        switch (cartMenu) {
            case 1 -> purchaseAllProductFromCart();
            case 2 -> {
                System.out.println("원하는 상품을 선택 해주세요.");
                String inputCardItemId = sc.next().trim();
                System.out.println("추가 혹은 뺴기 원하는 수를 입력 해 주세요 ex) 추가 : 4 빼기 : -4");
                int itemCount = sc.nextInt();
                cart.manageItemQuantity(inputCardItemId , itemCount);
            }
            case 3 -> {
                System.out.println("원하는 상품을 선택 해주세요.");
                String inputCardItemId = sc.next().trim();
                cart.removeItem(inputCardItemId);
            }
        }
    }

    ///  상품을 조회하고 카트에 담는 쇼핑 프로세스 흐름제어.
    private void shoppingProcess() {
        System.out.println("상품 카테고리를 선택 해 주세요.");
        System.out.println("1. 전체 상품 | 2. 전자 제품 | 3. 주방 용품 | 4. 음식 | 5. 음료 | 0. 뒤로가기");
        char menu = sc.next().charAt(0);
        switch (menu){
            // DB 에서 가져 와서 카테고리에 담는다.
            case '1' -> category = new Category(CategoryType.ALL );
            case '2' -> category = new Category(CategoryType.ELECTRONIC);
            case '3' -> category = new Category(CategoryType.KITCHEN);
            case '4' -> category = new Category(CategoryType.FOOD);
            case '5' -> category = new Category(CategoryType.BEVERAGE);
            case '0' -> {
                category = null;
                return;
            }
            default -> throw new RuntimeException("잘못된 메뉴 입력 발생...");
        }

        try {
            // 추후 while문을 통해서 계속 쇼핑 할건지 여부 확인 후 계속 쇼핑 진행.
            System.out.println("======================================================================================================");
            System.out.printf("[ %s 카테고리 ] 원하시는 상품 ID를 입력하세요." , category.getCategoryType().getCategoryType());
            System.out.println();

            System.out.print(category);
            System.out.println("0 -> 뒤로가기");
            System.out.println("======================================================================================================");

            Optional<Product> selectedProduct = selectProduct();
            selectedProduct.ifPresent(product -> {
                System.out.println("선택하신 상품을 장바구니에 넣을까요? 0: 뒤로가기 | 그외 숫자 : 장바구니에 넣을 숫자.");
                int quantity = sc.nextInt();
                if (quantity == 0){
                    System.out.println("제품 추가 취소");
                    return;
                }
                //올바른 갯수를 입력했는지 확인
                product.checkIsValidQuantity(quantity);
                // 고객이 들고있는 카트에 아이템 추가
                customer.cart.addItemToCart(product.getId(),quantity,product.getName(),product.getPrice());
            });
        }catch (Exception e) {
            System.out.println("에러 발생. 다시 시도 해 주세요 = " + e);
        }
    }

    ///  불러온 상품 중 원하는 상품을 선택 하는 메서드
    /// DB전체 데이터가 아닌 현재 불러온 객체 안에 담겨있는 데이터만 검사하기때문에.
    /// 실제 DB에 있어도 조회가 안될 수 있습니다.
    /// 이는 사용자가 UI상으로 보이지 않는 데이터를 입력 했을때 갑자기 값이 입력된다면, 혹시라도 숨기고싶은 상품이나.
    /// 버그로 인식할 수 있지않을까? 하는 관점에서 의도된 동작입니다.
    private Optional<Product> selectProduct(){
        sc.nextLine();
        String selectProductID = sc.nextLine();
        // "0" 입력시 메서드 종료
        if("0".equals(selectProductID)){
            return Optional.empty();
        }
        //DB가 아닌 객체에서 조회하는 로직.
        Optional<Product> selectProduct = category.getProduct()
                .stream()
                .filter(product -> selectProductID.equals(product.getId()))
                .findFirst();

        // id가 같은 값이 없으면 "상품을 잘못 선택 하셨습니다 에러 던기지.
        if(selectProduct.isEmpty()){
            throw new RuntimeException("상품을 잘못 선택 하셨습니다.");
        }
        return selectProduct;
    }

    /// 현재 장바구니에 담겨있는 모든 제품을 구매하고
    /// DB데이터를 반영합니다.
    public void purchaseAllProductFromCart() {
        Cart cart = customer.cart;

        // DB에 실제 제품이 있는지 확인하고.
        cart.getItems().forEach(cartItem -> {
            Optional<Product> isProduct = category.productRepository.findById(cartItem.getProductId());

            isProduct.ifPresent(product -> {
                // 남아있는 재고가 장바구니에 있는 재고보다 많으면 구매.
                // 근데 여기서 반복적으로 사면, 하나하나하나 제품을 구매하는건데
                // 돈을 우선 빼고, 수량 조절하는게 맞는가.
                // 아니면 수량을 먼저 제어하고 돈을 뺴야하는가.
                // 일단 수량을 하나하나 제어하고 마지막에 토탈 금액을 빼는게 맞는거같다.

                // 그러나 만약 결제가 실패한다면, 재고를 원상복구 시키는 프로세스가 필요할것임.
                try {
                    if(product.getQuantity() > cartItem.getProductQuantity()){
                        Boolean isSuccessManageProductDb = category.productRepository.updateQuantityByProductId(product.getId(),product.getQuantity() - cartItem.getProductQuantity());
                        if(isSuccessManageProductDb){
                            // db업데이트를 성공했다면, 여기서 상품id 와,금액을 추가.
                            // 구매를 담당하는 객체를 생성.
                            // 구매하고자 하는 데이터를 저장. 이 데이터는 나중에 혹시 결제가 실패했을때(돈 부족, 도중 이탈 등.) DB 복구하기 위해서 필요하다고 판단.
                            purchase = new Purchase();
                            purchase.addProductId(cartItem.getProductId(),  cartItem.getProductPrice() , cartItem.getProductQuantity());
                        }
                    }else {
                        // 없으면 cart 에서 제거
                        cart.removeItem(cartItem.getProductId());
                    }
                }catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            });

            // 모든 장바구니 상품을 검사 완료 하였다면 구매를 시작.
            buy();
        });
    }

    ///  구매 매서드.
    public void buy(){
        int customerMoney = customer.getMoney();
        int totalPayment = purchase.getTotalPrice();

        if(customerMoney < totalPayment){
            throw new RuntimeException("고객님이 소유하신 금액이 부족합니다.");
        }
        // 고객이 돈을 지불함.
        customer.payment(totalPayment);
        customer.cart.cleanCart();
        category = new Category(CategoryType.ALL);
        System.out.println("상품구매에 성공 하였습니다.");
        System.out.println("구매 후 고객님의 정보 = " + customer);
        System.out.println("============================구매 후 남은 재고 현황============================ " +
                "\n" + category);
    }

    public void restoreProductDb() {}
}
