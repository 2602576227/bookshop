package com.briup.bd2103.grms;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/*
* 将商品共现关系整理成商品的共现矩阵*/
public class Step5 extends Configured implements Tool {
    public static class S5Mapper extends Mapper<Text,Text,Text,Text>{
        @Override
        protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
            String data = key.toString();
            String[] goods = data.split("[:]");
            //key=第一列商品数据
            Text outkey = new Text(goods[0]);
            //value=第二列商品和相似数据的组合
            Text outvalue = new Text(goods[1]+":"+value.toString());
            context.write(outkey,outvalue);
        }
    }

    public static class S5Reducer extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            //拼接输出的value字符串
            StringBuffer sb = new StringBuffer();
            for (Text value : values){
                sb.append(value.toString()+",");
            }
            Text outvalue = new Text(sb.toString());
            context.write(key,outvalue);
        }
    }


    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = getConf();
        conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPARATOR,"\t");
        Job job = Job.getInstance(conf,"step5");
        job.setJarByClass(Step5.class);
        job.setMapperClass(S5Mapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setReducerClass(S5Reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setInputFormatClass(KeyValueTextInputFormat.class);
        KeyValueTextInputFormat.addInputPath(job,new Path("src/step4"));
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job,new Path("src/step5"));
        job.waitForCompletion(true);
        return 0;
    }

    public static void main(String[] args) {
        try {
            ToolRunner.run(new Step5(),args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
