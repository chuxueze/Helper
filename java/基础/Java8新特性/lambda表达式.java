
将行为传到函数
作用：
1.替代匿名内部类
  @Test
    public void oldRunable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("The old runable now is using!");
            }
        }).start();
    }

    @Test
    public void runable() {
        new Thread(() -> System.out.println("It's a lambda function!")).start();
    }



2.使用lambda表达式对集合进行迭代


    public static void getList() {
    	List<Integer> list = Arrays.asList(1,2,3);
    	list.forEach(l -> pringNum(l));
    }
    public static void pringNum(int a) {
    	System.out.println(a);
    }


3.用lambda表达式实现map
    @Test
    public void mapTest() {
        List<Double> cost = Arrays.asList(10.0, 20.0,30.0);
        cost.stream().map(x -> x + x*0.05).forEach(x -> System.out.println(x));
    }

    public static void getList2() {
    	List<Map<String,Object>> list = new ArrayList<>();
    	for(int i = 0;i<10;i++) {
    		Map<String,Object> map = new HashMap<>();
    		map.put(i+"", i+10);
    		list.add(map);
    	}
    	list.stream().map(m -> m).forEach(x -> System.out.println(x));
    }