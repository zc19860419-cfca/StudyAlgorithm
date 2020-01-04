package com.sadk.algorithm.karatsuba;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
/**
 * @author zhangchong
 * @CodeReviewer
 * @Description
 */
@BenchmarkMode({Mode.Throughput, Mode.AverageTime}) // 测试方法平均执行时间和吞吐量
@OutputTimeUnit(TimeUnit.SECONDS) // 输出结果的时间粒度为微秒
@State(Scope.Thread) // 默认的State，每个测试线程分配一个实例
public class KaratsubaBenchmark {

    public static final String XVAL = "622337203685477580";
    public static final String YVAL = "678789656787896567";
    private BigInteger x;
    private BigInteger y;
    private Long x1;
    private Long y1;

    @Setup
    public void prepare() throws Exception {
        x = new BigInteger(XVAL);
        y = new BigInteger(YVAL);

        x1 = new Long(XVAL);
        y1 = new Long(YVAL);
    }

    @Benchmark
    public Long multiply() throws Exception {
        return  x1* y1;
    }

    @Benchmark
    public BigInteger karatsuba() throws Exception {
        return  x.multiply(y);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(KaratsubaBenchmark.class.getSimpleName())
//                .exclude("idleTest")
                .forks(1)
                .threads(4)
                .warmupIterations(5)
                .measurementIterations(10)
                .resultFormat(ResultFormatType.JSON)
                .result("Karatsuba.json")
                .build();
        new Runner(options).run();
    }
}
