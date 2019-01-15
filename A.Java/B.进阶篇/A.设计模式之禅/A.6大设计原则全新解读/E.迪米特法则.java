1.简介:
2.例子:







---------------------------------------------------------------------------------------------------------------------------------
1.简介:

    迪米特法则（Law of Demeter，LoD）也称为最少知识原则（Least Knowledge
Principle，LKP）

    一个对象应该对其他对象有最少的了解。
    通俗地讲，一个类应该对自己需要耦合或调用的类知道得最少，你（被耦合或调
用的类）的内部是如何复杂都和我没关系，那是你的事情，我就知道你提供的这么多 public
方法，我就调用这么多，其他的我1概不关心。



--------------------------------------------------------------
2.例子:
public 方法只公布最少的，其他的业务逻辑处理放在 private 方法中。
这样如果后期有修改，可以让调用方尽量少地修改。高内聚的特性。

反例:
public class Person(){
	//吃苹果	
	@Service
	AppleService appleService;
	public eatApple(){
		//1：洗苹果
		appleService.wash();
		//2：剥皮
		appleService.peel();
		//3：放入口中
		appleService.putMouth();
	}

}

public interface AppleService(){
		public void wash();
		public void peel();
		public void putMouth();
}

public class AppleServiceImpl implements AppleService(){
		public void wash(){
			System.out.println("洗苹果");
		}
		public void peel(){
			System.out.println("剥皮");
		}
		public void putMouth(){
			System.out.println("放进口中");
		}
}

正例:

public class Person(){
	//吃苹果	
	@Service
	AppleService appleService;
	public eatApple(){
		//1：吃苹果
		appleService.eat();
	}

}

public interface AppleService(){
		public void eat();
}

public class AppleServiceImpl implements AppleService(){
		public void eat(){
			wash();
			peel();
			putMouth();
		}
		private void wash(){
			System.out.println("洗苹果");
		}
		private void peel(){
			System.out.println("剥皮");
		}
		private void putMouth(){
			System.out.println("放进口中");
		}
}

