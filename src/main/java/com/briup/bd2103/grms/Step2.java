package com.briup.bd2103.grms;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
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

    public static void main(String[] args) {
        try {
            ToolRunner.run(new Step2(),args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int run(String[] strings) throws Exception {
        return 0;
    }
}
