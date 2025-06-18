
# 🛒 Shopping Cart Application (Java)

This is a **console-based Shopping Cart application** built in Java. It simulates the process of adding, removing, and viewing items in a shopping cart, with automatic calculation of total price.

The application includes **two implementations** of the cart logic:
- One using `LinkedList`
- Another using `HashMap`

Each implementation demonstrates different ways of storing and managing cart items.

---

## 🚀 Features

- ✅ Add items with name, price, and quantity
- ❌ Remove items completely or partially
- 📋 Display cart items
- 💵 Calculate total price

---

## 🧩 Implementation Versions

### 🔹 Version 1: `LinkedList<Item>`

- Items are stored in a `LinkedList` sequentially.
- Accessed by **index**.
- Allows duplicate entries for same item names.
- Item removal is by index.
- Display order is preserved (insertion order).

### 🔸 Version 2: `HashMap<Integer, Item>`

- Items are stored in a `HashMap` with a unique item number as the key.
- Accessed by **key** (item number).
- Prevents duplicates by replacing old entries.
- Faster operations: direct access, update, and delete.
- Order is not guaranteed.

---

## ⚖️ Comparison: `LinkedList` vs `HashMap`

| Feature                  | `LinkedList`                          | `HashMap`                                 |
|--------------------------|----------------------------------------|--------------------------------------------|
| Storage Type             | Ordered list                           | Key-value map                              |
| Access                   | By index                               | By key (item number)                       |
| Search Speed             | O(n) (linear search)                   | O(1) (constant time)                       |
| Duplicate Items Allowed? | Yes (same name, different instances)   | No (same key overwrites old item)          |
| Initial Quantity Issue   | Can create duplicates                  | Overwrites and updates same item           |
| Display Order            | Maintains insertion order              | Order may vary depending on keys           |

---

## 🧪 Code Snippets

### ➕ Add Item

#### 📦 LinkedList Version
```java
public void addItem(String name, double price, int quantity) {
    cartItems.add(new Item(name, price, quantity));
}
````

* Adds a new item regardless of duplicates.

#### 📦 HashMap Version

```java
public void addItem(int itemNumber, String name, double price, int quantity) {
    cartItems.put(itemNumber, new Item(name, price, quantity));
}
```

* Replaces the existing item if the key already exists.

---

### ➖ Remove Item

#### 🗑️ LinkedList Version

```java
public void removeItem(int index, int quantityToRemove) {
    Item item = cartItems.get(index);
    if (item.getQuantity() <= quantityToRemove) {
        cartItems.remove(index); // remove entirely
    } else {
        item.setQuantity(item.getQuantity() - quantityToRemove); // update quantity
    }
}
```

#### 🗑️ HashMap Version

```java
public void removeItem(int itemNumber, int quantityToRemove) {
    Item item = cartItems.get(itemNumber);
    if (item != null) {
        if (item.getQuantity() <= quantityToRemove) {
            cartItems.remove(itemNumber); // remove from map
        } else {
            item.setQuantity(item.getQuantity() - quantityToRemove); // update quantity
        }
    }
}
```

---

### 🔍 Search for Item

#### 🔎 LinkedList (by name)

```java
public boolean containsItem(String name) {
    for (Item item : cartItems) {
        if (item.getName().equalsIgnoreCase(name)) {
            return true;
        }
    }
    return false;
}
```

#### 🔎 HashMap (by item number)

```java
public boolean containsItem(int itemNumber) {
    return cartItems.containsKey(itemNumber);
}
```

---

### 🖨️ Display Cart

#### 📝 LinkedList

```java
public void printCart() {
    int i = 1;
    for (Item item : cartItems) {
        System.out.println(i++ + ". " + item);
    }
}
```

#### 📝 HashMap

```java
public void printCart() {
    for (Map.Entry<Integer, Item> entry : cartItems.entrySet()) {
        System.out.println(entry.getKey() + ". " + entry.getValue());
    }
}
```

---

## 🧠 Example Output

### ✅ LinkedList Output:

```
1. Shirt (Price: $30.0) - Quantity: 2
2. Pants (Price: $50.0) - Quantity: 1
```
![image](https://github.com/user-attachments/assets/253de7d6-b0bd-403b-bdb4-80673dae3292)


### ✅ HashMap Output:

```
101. Shirt (Price: $30.0) - Quantity: 2
102. Pants (Price: $50.0) - Quantity: 1
```
![image](https://github.com/user-attachments/assets/961dcb88-da26-4ed2-b8ce-1e3ec3dae770)

> ⚠️ In `HashMap`, display order may change depending on the item numbers.

---

## ✅ Why `HashMap` is Better (in most cases)

* 🔑 Fast access with unique keys
* 🧼 No duplication issues
* 🔁 Easy updates and clean overwrites
* 🚀 More scalable for larger data

Use `HashMap` if you need performance and clarity.
Use `LinkedList` if insertion order and duplicates matter.


---

Made with ☕ and ❤️.
