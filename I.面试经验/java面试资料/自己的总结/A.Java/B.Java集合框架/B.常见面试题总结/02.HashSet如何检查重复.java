    当你把对象加入 HashSet 时，HashSet 会先计算对象的 hashcode 值来判断对象加入的位置，
同时也会与其他加入的对象的 hashcode 值作比较，如果没有相符的 hashcode，HashSet 会假设对象没有重复出现。
但是如果发现有相同 hashcode 值的对象，这时会调用 equals（）方法来检查 hashcode 相等的对象是否真的相同。
如果两者相同，HashSet 就不会让加入操作成功。


hashCode（）与equals（）的相关规定：

如果两个对象相等，则 hashcode 一定也是相同的
两个对象相等,对两个 equals 方法返回 true
两个对象有相同的 hashcode 值，它们也不1定是相等的
综上，equals 方法被覆盖过，则 hashCode 方法也必须被覆盖
hashCode()的默认行为是对堆上的对象产生独特值。如果没有重写 hashCode()，则该 class 的两个对象无论如何都不会相等（即使这两个对象指向相同的数据）。