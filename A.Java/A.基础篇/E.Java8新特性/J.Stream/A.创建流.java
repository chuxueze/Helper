1.流的介绍
2.4 种创建流的方式









-----------------------------------------------------------------------------------------------------------------
1.流的介绍
  流是数据渠道，用于操作数据源(集合、数组等)所生成的元素序列。
集合讲的是数据，流讲的是计算。

注意:
1)Stream 自己不会存储元素
2)Stream 不会改变源对象，相反，他们会返回1个持有结果的新 Stream
3)Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。

操作的三个步骤:
1)创建 Stream 
1 个数据源(如，集合、数组)，获取1个流

2)中间操作
1 个中间操作链，对数据源的数据进行处理

3)终止操作(终端操作)
1 个终止操作，执行中间操作链，并产生结果



------------------------------------------------------
2.4 种创建流的方式
	

	private void test1() {
        //1.通过Collection 系统集合提供的 stream() 或 parallelStream()
        List<String> list = Arrays.asList("北京", "欢迎", "您", "！");
        Stream stream = list.stream();
    }

    private void test2() {
        //2.通过Arrays 中静态方法 stream() 获取数组流
        Car[] cars = new Car[10];
        Stream<Car> stream = Arrays.stream(cars);
    }

    private void test3() {
        //3.通过Stream 类中的静态方法of()
        Stream stream = Stream.of("北京", "欢迎", "您", "！");
    }

    private void test4() {
        //4.创建无限流
        //迭代
        Stream<Integer> stream = Stream.iterate(0, x -> x * 2);
        stream.limit(10).forEach(System.out::println);
    }
    private void test5(){
        Stream.generate(()->Math.random()).limit(10).forEach(System.out::println);
    }