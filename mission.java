; 1. 아래 코드와 설명을 보고, [섹션 3. 논리, 사고의 흐름]에서 이야기하는 내용을 중심으로 읽기 좋은 코드로 리팩토링해 봅시다.

; 2. SOLID에 대하여 자기만의 언어로 정리해 봅시다.

; ✔️ 사용자가 생성한 '주문'이 유효한지를 검증하는 메서드. 
; ✔️ Order는 주문 객체이고, 필요하다면 Order에 추가적인 메서드를 만들어도 된다. (Order 내부의 구현을 구체적으로 할 필요는 없다.) 
; ✔️ 필요하다면 메서드를 추출할 수 있다.

; Original
public boolean validateOrder(Order order) {
    if (order.getItems().size() == 0) {
        log.info("주문 항목이 없습니다.");
        return false;
    } else {
        if (order.getTotalPrice() > 0) {
            if (!order.hasCustomerInfo()) {
                log.info("사용자 정보가 없습니다.");
                return false;
            } else {
                return true;
            }
        } else if (!(order.getTotalPrice() > 0)) {
            log.info("올바르지 않은 총 가격입니다.");
            return false;
        }
    }
    return true;
}

;ordering
public boolean validateOrder(order order) {
    if (order.getItems().size() == 0) {
        log.info("주문 항목이 없습니다.");
        return false;
    }

    if (!(order.getTotalPrice() > 0)) {
            log.info("올바르지 않은 총 가격입니다.");
            return false;
        }

    if (!order.hasCustomerInfo()) {
        log.info("사용자 정보가 없습니다.");
            return false;
        }     
    return true;
}

; 캡슐화
public class Order {
    public boolean isValid() {
        return !getItems().isEmpty() && getTotalPrice() >0 && hasCustomerInfo();
    }

    public String getValidaionMessage {
        if(getItems().isEmpty()) return "No order list";
        if(getTotalPrice() <= 0) return "Wrong Price";
        if(!hasCustomerInfo()) return "No user information";
        return "Thanks for order."
    }
}

;부정문 제거
public class Order {
    public boolean isValid() {
        return hasItems() && getTotalPrice() > 0 && hasCustomerInfo();
    }

    public String getValidationMessage() {
        if(!hasItems()) return "No order list";
        if(getTotalPrice() <= 0) return "Wrong Price";
        if(!hasCustomerInfo()) return "No user information";
        return "Thanks for order."
    }

    public boolean hasItems() {
        return !getItems().isEmpty();
    }

    public boolean getTotalPrice() {
        return totalPrice;
    }

    public boolean hasCustomerInfo() {
        return customerInfo() != null;
    }
}

