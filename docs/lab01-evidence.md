# Lab 01 Evidence - SCELS

## Object identification

| Concept | Java class | State | Behaviour |
|---|---|---|---|
| Borrower | `CampusMember` | `memberId`, `fullName`, `email`, `department` | `displayProfile()`, `canBorrow()` |
| Equipment | `EquipmentItem` | `itemId`, `name`, `category`, `dailyFee`, `available` | `isAvailable()`, `markBorrowed()`, `markReturned()`, `displaySummary()` |

The `App` class is the entry point. It has no object state; it only contains `main()` and runs the scenario.

## Expected final availability before execution

| Equipment | Initial availability | Expected final availability | Reason |
|---|---:|---:|---|
| Laptop | `true` | `true` | It is never borrowed. |
| Camera | `true` | `true` | It is borrowed once and then returned. |
| Projector | `true` | `true` | It is never borrowed. |

The second attempt to borrow the camera must display an error and leave the camera borrowed until `markReturned()` is called.

## Complete console output

```text
=== CAMPUS MEMBERS ===
Member ID: M001
Full Name: Alice Martin
Email: alice.martin@campus.ca
Department: Computer Science

Member ID: M002
Full Name: Karim Benali
Email: karim.benali@campus.ca
Department: Engineering

[PASS] Alice can borrow equipment
[PASS] Karim can borrow equipment

=== INITIAL EQUIPMENT ===
Item ID   : E001
Name      : Laptop
Category  : Computer
Daily Fee : 15.0
Available : true
---------------------------------
Item ID   : E002
Name      : Camera
Category  : Video
Daily Fee : 12.5
Available : true
---------------------------------
Item ID   : E003
Name      : Projector
Category  : Presentation
Daily Fee : 10.0
Available : true
---------------------------------

=== BORROW CAMERA ===
Item ID   : E001
Name      : Laptop
Category  : Computer
Daily Fee : 15.0
Available : true
---------------------------------
Item ID   : E002
Name      : Camera
Category  : Video
Daily Fee : 12.5
Available : false
---------------------------------
Item ID   : E003
Name      : Projector
Category  : Presentation
Daily Fee : 10.0
Available : true
---------------------------------

=== TRY TO BORROW CAMERA AGAIN ===
[ERROR] Camera is already borrowed.
[PASS] Laptop is still available
[PASS] Projector is still available

=== RETURN CAMERA ===
[OK] Camera has been returned.
Item ID   : E001
Name      : Laptop
Category  : Computer
Daily Fee : 15.0
Available : true
---------------------------------
Item ID   : E002
Name      : Camera
Category  : Video
Daily Fee : 12.5
Available : true
---------------------------------
Item ID   : E003
Name      : Projector
Category  : Presentation
Daily Fee : 10.0
Available : true
---------------------------------

=== FINAL CHECKS ===
[PASS] Camera has returned to its initial state
[PASS] Laptop stayed available throughout the scenario
[PASS] Projector stayed available throughout the scenario
```

## Object-oriented concepts

A class is a blueprint that describes the data and actions of an object. `CampusMember` is a class because it describes what a campus member has and can do. An object is a concrete instance of a class, such as `firstMember` or `secondMember` in `App`. The state is the data stored in an object: for example, a `CampusMember` stores its ID, name, email, and department, while an `EquipmentItem` stores its ID, name, category, fee, and availability. Behaviour is what an object can do through methods. For example, `displayProfile()` displays a member profile and `markBorrowed()` changes only the `available` state of the equipment object that receives the call. This respects encapsulation because the state is private and each equipment method modifies only its own object.
