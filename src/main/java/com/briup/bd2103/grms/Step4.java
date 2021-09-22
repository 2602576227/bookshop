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

public class Step4 extends Configured implements Tool {

    public static class S4Mapper extends Mapper<Text,Text,Text, IntWritable>{
        @Override
        protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
            String g1 = key.toString();
            String g2 = value.toString();
            Text outkey = new Text(g1+":"+g2);
            IntWritable outvalue = new IntWritable(1);
            context.write(outkey,outvalue);
        }
    }
    public static class S4Reducer extends Reducer<Text,IntWritable,Text,IntWritable>{
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0 ;
            for (IntWritable value : values){
                sum += value.get();
            }
            context.write(key,new IntWritable(sum));
        }
    }

    public static void main(String[] args) {
        try {
            ToolRunner.run(new Step4(),args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = getConf();
        conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPARATOR,"\t");
        Job job = Job.getInstance(conf,"step4");
        job.setJarByClass(Step4.class);
        job.setMapperClass(S4Mapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setReducerClass(S4Reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setInputFormatClass(KeyValueTextInputFormat.class);
        KeyValueTextInputFormat.addInputPath(job,new Path("src/step3"));
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job,new Path("src/step4"));
        job.waitForCompletion(true);
        return 0;
    }
}
