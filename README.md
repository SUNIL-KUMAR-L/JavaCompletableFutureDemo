# JavaCompletableFutureDemo

## Merge results of parallel service requests using CompletableFuture

**Step by Step Guide**

_Approach 1_ : Plain old simple java way

### Introduce CompletableFuture with different approaches

_Approach 2_ : Parallel service calls and collect data using CompletableFuture::get()

`Approach 3` : Parallel service calls and collect data using CompletableFuture::join()

`Approach 4` : Parallel service calls and group futures using CompletableFuture::allOf() and then collect each data

`Approach 5` (My Preference) : Parallel service calls and group futures using CompletableFuture::allOf() and then assemble using CompletableFuture::thenApply()



goto src directory (now, compile and run)

_cd src_

_complie java classes_

_run Java main class_
javac *.java 


`execute CFmain`

```
java CFMain 
===== Approach 1 : Plain old simple java way =====
PersonWithAddress{person=Person{id='1', firstName='hello', age='22'}, address=Address{addressId='1', addressLine1='add1', addressLine2='add2', addressLine3='add3', zipCode='55305', city='Mpls', state='MN', country='USA'}}
====== Approach 1 : took 810 milliseconds ======
===== Approach 2 : Parallel service calls and collect data using CompletableFuture::get() =====
PersonWithAddress{person=Person{id='1', firstName='hello', age='22'}, address=Address{addressId='1', addressLine1='add1', addressLine2='add2', addressLine3='add3', zipCode='55305', city='Mpls', state='MN', country='USA'}}
====== Approach 2 : took 571 milliseconds ======
===== Approach 3 : Parallel service calls and collect data using CompletableFuture::join() =====
PersonWithAddress{person=Person{id='1', firstName='hello', age='22'}, address=Address{addressId='1', addressLine1='add1', addressLine2='add2', addressLine3='add3', zipCode='55305', city='Mpls', state='MN', country='USA'}}
====== Approach 3 : took 505 milliseconds ======
===== Approach 4 (get()) : Parallel service calls and group futures using CompletableFuture::allOf() and then collect each data =====
PersonWithAddress{person=Person{id='1', firstName='hello', age='22'}, address=Address{addressId='1', addressLine1='add1', addressLine2='add2', addressLine3='add3', zipCode='55305', city='Mpls', state='MN', country='USA'}}
====== Approach 4 (get()) : took 506 milliseconds ======
===== Approach 4 (join()) : Parallel service calls and group futures using CompletableFuture::allOf() and then collect each data =====
PersonWithAddress{person=Person{id='1', firstName='hello', age='22'}, address=Address{addressId='1', addressLine1='add1', addressLine2='add2', addressLine3='add3', zipCode='55305', city='Mpls', state='MN', country='USA'}}
====== Approach 4 (get()) : took 505 milliseconds ======
===== Approach 5 (My Preference) : Parallel service calls and group futures using CompletableFuture::allOf() and then assemble using CompletableFuture::thenApply() =====
PersonWithAddress{person=Person{id='1', firstName='hello', age='22'}, address=Address{addressId='1', addressLine1='add1', addressLine2='add2', addressLine3='add3', zipCode='55305', city='Mpls', state='MN', country='USA'}}
====== Approach 5  : took 505 milliseconds ======
```