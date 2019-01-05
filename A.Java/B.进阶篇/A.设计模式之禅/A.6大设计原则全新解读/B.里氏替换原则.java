1.简介:
2.最佳实践:
3.实例:






-------------------------------------------------------------------------------------------------------------------------
1.简介:

    通俗点讲，只要父类能出现的地方子类就可以出现，而且替换为子类也不会产生任何错误或异常，使用者可能根本就不需要知道是父类还是子类。
但是，反过来就不行了，有子类出现的地方，父类未必就能适应



----------------------------------------------------------
2.最佳实践:

    在项目中，采用里氏替换原则时，尽量避免子类的“个性”，一旦子类有“个性”，这个子
类和父类之间的关系就很难调和了，把子类当做父类使用，子类的“个性”被抹杀——委屈了
点；把子类单独作为1个业务来使用，则会让代码间的耦合关系变得扑朔迷离——缺乏类替
换的标准。


----------------------------------------------------------
3.实例:
  
   定义抽象类让子类去继承。