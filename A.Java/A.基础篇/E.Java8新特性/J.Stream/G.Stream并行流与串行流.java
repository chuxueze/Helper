1:了解 Fork/Join 框架
2:并行流
3:串行流







--------------------------------------------------------------------------------------------------------------------------------------------------------
1:了解 Fork/Join 框架
  Fork/Join 框架:就是在必要的情况下，将1个大任务，进行拆分(fork)成若干个小任务(拆到不可再拆封)，
再将1个个小任务去处的结果进行 join 汇总。

与传统线程池的区别:
  采用“工作窃取”模式
  当执行新的任务时，它可以将其拆分为更小的任务执行，并将小任务加到线程队列中，然后再从1个随机
线程的队列中偷1个并把它放在自己的队列中。

只有在需要处理大数据的情况下才需要用到，原理就是充分利用CPU,将其使用效率提高。


Java7 中的并行框架
例子:
public class ForkJoinTest extends RecursiveTask {

    private long start;
    private long end;
    private static final long HOLD = 100000000L;

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask forkJoinTask = new ForkJoinTest(0, 1000000000L);
        Object sum = forkJoinPool.invoke(forkJoinTask);
        System.out.println(sum);
    }

    public ForkJoinTest(long start, long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 并行框架
     */
    @Override
    protected Long compute() {
        long length = end - start;

        if (length <= HOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinTest left = new ForkJoinTest(start, middle);
            left.fork();

            ForkJoinTest right = new ForkJoinTest(middle + 1, end);
            right.fork();
            return (long) left.join() + (long) right.join();
        }

    }
}


Java8 对并行时行了优化，可以更容易对数据进行并行操作。
	private static void test21(){
        Long num = LongStream.rangeClosed(0,1000000000L)
                  .parallel()
                  .reduce(0,Long::sum);
        System.out.println(num);
    }
