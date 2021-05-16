package com.sadk.algorithm.taocp.generate.permutation;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangchong
 * @CodeReviewer
 * @Description
 */

/**
 * [BenchmarkMode]
 * Throughput:整体吞吐量,例如"1秒内可以执行多少次调用".
 * AverageTime:调用的平均时间,例如"每次调用平均耗时xxx毫秒".
 * SampleTime:随机取样,最后输出取样结果的分布,例如"99%的调用在xxx毫秒以内,99.99%的调用在xxx毫秒以内".
 * SingleShotTime:以上模式都是默认一次 iteration 是 1s,唯有 SingleShotTime 是只运行一次.
 *      往往同时把 warmup 次数设为0，用于测试冷启动时的性能.
 */
//@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@BenchmarkMode({Mode.Throughput})
@OutputTimeUnit(TimeUnit.SECONDS) // 输出结果的时间粒度(毫秒/微秒/秒)
@State(Scope.Thread) // 每个测试线程一个实例
public class PlainChangePermutationsBenchmark {

    Integer[] data = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};

    @Benchmark
    public void search() {
        PlainChangePermutationsGenerator generator = new PlainChangePermutationsGenerator(data);
        for (int i = 0; i < 40319; i++) {
            data = generator.next();
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PlainChangePermutationsBenchmark.class.getSimpleName())//找寻指定类中Benchmark注解的方法来测试
                .forks(1)//使用n个进程(forks(n))执行测试
                .threads(4)//使用n个线程(threads(n))执行测试
                .warmupIterations(5)//执行N遍warmup(warmupIterations(N))
                .measurementIterations(5)//然后执行n遍测试(measurementIterations(n))
                .resultFormat(ResultFormatType.JSON)
                .result("TestData/DictionaryPermutations.json")
                .build();
        new Runner(opt).run();
    }

}