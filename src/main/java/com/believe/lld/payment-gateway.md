# Payment Gateway – Low Level Design (LLD)

This document describes the Low Level Design (LLD) for a Payment Gateway system. The Payment Gateway acts as a mediator between merchants, customers, and financial institutions, enabling secure online payments.

---

## 1. **Key Entities & Classes**

### 1.1. **User**
- `User`
  - userId: String
  - name: String
  - email: String
  - paymentMethods: List<PaymentMethod>

### 1.2. **Merchant**
- `Merchant`
  - merchantId: String
  - name: String
  - merchantAccount: MerchantAccount

### 1.3. **PaymentMethod** (abstract)
- `PaymentMethod`
  - methodId: String
  - type: PaymentMethodType (enum: CARD, UPI, WALLET, NETBANKING)
  - details: Map<String, String>
- Subclasses: `Card`, `UPI`, `Wallet`, `NetBanking`

### 1.4. **Transaction**
- `Transaction`
  - transactionId: String
  - user: User
  - merchant: Merchant
  - amount: double
  - currency: String
  - status: TransactionStatus (enum: INITIATED, PROCESSING, SUCCESS, FAILURE, REFUNDED)
  - paymentMethod: PaymentMethod
  - createdAt: DateTime
  - updatedAt: DateTime
  - paymentProcessor: PaymentProcessor

### 1.5. **MerchantAccount**
- `MerchantAccount`
  - accountId: String
  - bankName: String
  - accountNumber: String
  - ifsc: String

### 1.6. **PaymentProcessor** (interface)
- `PaymentProcessor`
  - processPayment(Transaction): PaymentResult
  - refundPayment(Transaction): PaymentResult

- Implementations: `CardProcessor`, `UPIProcessor`, `WalletProcessor`, `NetBankingProcessor`

### 1.7. **PaymentResult**
- `PaymentResult`
  - success: boolean
  - message: String
  - errorCode: String (nullable)
  - updatedStatus: TransactionStatus

---

## 2. **Class Diagram (Textual)**

```
User
  |-- paymentMethods --> PaymentMethod (Card/UPI/Wallet)
  |-- makes --> Transaction <-- Merchant
Transaction -- uses --> PaymentProcessor (strategy)
```

---

## 3. **Class Relationships & Interactions**

- **User initiates payment**: Selects a merchant, payment method, and amount.
- **PaymentGateway** (main orchestrator class) creates a Transaction, picks the correct PaymentProcessor implementation, and calls `processPayment`.
- **PaymentProcessor** interacts with external bank/UPI/wallet APIs.
- **Transaction** status is updated based on PaymentResult.

---

## 4. **Sequence Diagram (Textual)**

1. User initiates payment (User Portal).
2. PaymentGateway creates Transaction (status=INITIATED).
3. PaymentGateway selects PaymentProcessor based on PaymentMethod type.
4. PaymentProcessor.processPayment(Transaction) is called.
5. External Bank/UPI/Wallet API interaction.
6. PaymentProcessor returns PaymentResult.
7. PaymentGateway updates Transaction status.
8. Notify user and merchant of the outcome.

---

## 5. **Sample Class Skeletons (Java-like Pseudocode)**

```java
class User {
    String userId;
    String name;
    String email;
    List<PaymentMethod> paymentMethods;
}

abstract class PaymentMethod {
    String methodId;
    PaymentMethodType type;
    Map<String, String> details;
}

class Card extends PaymentMethod {
    String cardNumber;
    String expiry;
    String cvv;
}

class UPI extends PaymentMethod {
    String upiId;
}

class Transaction {
    String transactionId;
    User user;
    Merchant merchant;
    double amount;
    String currency;
    TransactionStatus status;
    PaymentMethod paymentMethod;
    PaymentProcessor paymentProcessor;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

interface PaymentProcessor {
    PaymentResult processPayment(Transaction txn);
    PaymentResult refundPayment(Transaction txn);
}

class CardProcessor implements PaymentProcessor { ... }
class UPIProcessor implements PaymentProcessor { ... }

class PaymentResult {
    boolean success;
    String message;
    String errorCode;
    TransactionStatus updatedStatus;
}

class PaymentGateway {
    PaymentResult makePayment(User user, Merchant merchant, PaymentMethod method, double amount) {
        // 1. Create Transaction
        // 2. Select PaymentProcessor
        // 3. Call processPayment
        // 4. Update Transaction status
        // 5. Return result
    }
}
```

---

## 6. **Non-Functional Considerations**

- **Security**: All sensitive data (card number, CVV, etc.) must be encrypted.
- **PCI Compliance**: For card processing.
- **Idempotency**: Prevent double payments on retries (e.g., use unique transaction IDs).
- **Logging & Auditing**: For compliance and debugging.
- **Extensibility**: New payment methods can be added via new PaymentProcessor implementations.
- **Rate Limiting & Fraud Detection**: Protect against abuse.

---

## 7. **Error Handling & Status Flow**

- Transaction status transitions:
  - INITIATED → PROCESSING → SUCCESS/FAILURE
  - SUCCESS → REFUNDED (if refund is processed)

---

## 8. **Extension Points**

- Support for recurring payments (subscriptions).
- Integration with 3rd party fraud-check APIs.
- Webhooks for asynchronous notifications.

---

**Tip:** In interviews, walk through your design, explain class relationships, and discuss how you handle extensibility, security, and error scenarios.
