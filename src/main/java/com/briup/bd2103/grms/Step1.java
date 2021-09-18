package com.briup.bd2103.grms;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.math.BigDecimal;

/*
输入数据：
用户，商品id，行为，时间
4,2a60dba5d290434eb374bcfa7109f774,showProduct,2021 八月 05 15:27:12,853
4,2a60dba5d290434eb374bcfa7109f774,addCart,2021 八月 05 15:27:14,147
* 获得数据：用户id；商品id；偏好
* 偏好：购买记录，订单，购物车，浏览，平均*/
public class Step1 extends Configured implements Tool {

    public static class S1Mapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] split = line.split("[,]");
            //将各种行为转换为偏好值
            double like = 0.0;
            if (split.length >= 3) {
                String str = split[2].toLowerCase();
                if (str.equals("addcart")) {
                    like = 0.15;
                } else if (str.equals("showproduct")) {
                    like = 0.05;
                } else if (str.equals("createorder")) {
                    like = 0.3;
                } else {
                    like = 0.5;
                }
                Text outkey = new Text(split[0] + ":" + split[1]);
                DoubleWritable outvalue = new DoubleWritable(like);
                context.write(outkey, outvalue);
            }
        }
    }

    public static class S1Reduce extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
        @Override
        protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
            //避免double类型数据精度丢失，使用bigDecimal
            BigDecimal sum = new BigDecimal("0");
            for (DoubleWritable d : values) {
                BigDecimal value = new BigDecimal(d.toString());
                sum = sum.add(value);
            }
            DoubleWritable outvalue = new DoubleWritable(sum.doubleValue());
            context.write(key, outvalue);
        }
    }

    public static void main(String[] args) {
        try {
            ToolRunner.run(new Step1(), args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = getConf();
        Job job = Job.getInstance(conf,"step1");
        job.setJarByClass(Step1.class);
        job.setMapperClass(S1Mapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DoubleWritable.class);
        job.setReducerClass(S1Reduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job,new Path("src/userLog.log"));
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job,new Path("src/step1"));
        job.waitForCompletion(true);
        return 0;
    }
}
