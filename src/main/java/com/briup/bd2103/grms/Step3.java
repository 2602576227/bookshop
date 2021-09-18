package com.briup.bd2103.grms;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.ArrayList;

/*
* 输入数据：
* 4:000f238da6684372a370a4fd021f8e3c	0.5
* 4:028eb9b397744e35a4a2bccb4382d211	0.5
*输出数据
* map：用户id，物品id
* reduce：物品1，物品2 */
public class Step3 extends Configured implements Tool {
    public static class S3Mapper extends Mapper<Text,Text,Text,Text>{
        @Override
        protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
            String data = key.toString();
            String[] split = data.split(":");
            //将用户id作为输出的key
            Text outkey = new Text(split[0]);
            //将商品id作为输出value
            Text outvalue = new Text(split[1]);
            context.write(outkey,outvalue);
        }
    }

    //经过shuffle之后所有商品都会进入相同的商品组中，方便进行商品组合
    public static class S3Reduce extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            /*
            * 商品的两两组合，对商品列表进行遍历，组合商品共线关系
            * 由于values列表是单向的，不能进行两两组合，需要将所有商品放入新容器中，通过新容器进行商品组合*/
            ArrayList<String> list = new ArrayList<>();
            for (Text value : values){
                list.add(value.toString());
            }
            for (String g1 : list){
                for (String g2 : list){
                    Text outkey = new Text(g1);
                    Text outvalue = new Text(g2);
                    context.write(outkey,outvalue);
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            ToolRunner.run(new Step3(),args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = getConf();
        conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPARATOR,"\t");
        Job job = Job.getInstance(conf,"step3");
        job.setJarByClass(Step3.class);
        job.setMapperClass(S3Mapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setReducerClass(S3Reduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setInputFormatClass(KeyValueTextInputFormat.class);
        KeyValueTextInputFormat.addInputPath(job,new Path("src/step1"));
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job,new Path("src/step3"));
        job.waitForCompletion(true);
        return 0;
    }
}
