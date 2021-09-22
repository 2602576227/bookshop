package com.briup.bd2103.grms;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
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
* 用户-商品正排
* 商品-用户倒排*/
public class Step2 extends Configured implements Tool {

    public static class S2Mapper extends Mapper<Text,Text,Text,Text>{
        @Override
        protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
            String uid_gid = key.toString();
            String[] data = uid_gid.split("[:]");
            Text outkey = new Text(data[1]);//商品id
            //用户id与偏好值组合生成value
            Text outvalue = new Text(data[0]+":"+value.toString());
            context.write(outkey,outvalue);
        }
    }
    public static class S2Reducer extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            //整理输出字符串的形式
            StringBuffer sb = new StringBuffer();
            for (Text value : values){
                //uid：like
                sb.append(values.toString()+",");
            }
            Text outvalue = new Text(sb.toString());
            context.write(key,outvalue);
        }
    }

    public static void main(String[] args) {
        try {
            ToolRunner.run(new Step2(),args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = getConf();
        conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPARATOR,"\t");
        Job job = Job.getInstance(conf,"step2");
        job.setJarByClass(Step2.class);
        job.setMapperClass(S2Mapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setReducerClass(S2Reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setInputFormatClass(KeyValueTextInputFormat.class);
        KeyValueTextInputFormat.addInputPath(job,new Path("src/step1"));
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job,new Path("src/step2"));
        job.waitForCompletion(true);
        return 0;
    }
}
