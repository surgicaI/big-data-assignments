import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PageRank {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: PageRank <input path> <output path>");
            System.exit(-1);
        }       
        int i =1;
        int exit_code = 0;    
        String inputPath = args[0];
        String outputPath = args[1];
        while(i <= 3){
            Job job = new Job();
            job.setJarByClass(PageRank.class);
            job.setJobName("Page Rank");
            job.setNumReduceTasks(1); // 1 Reduce task 
            FileInputFormat.addInputPath(job, new Path(inputPath));
            FileOutputFormat.setOutputPath(job, new Path(outputPath+String.valueOf(i)));
            job.setMapperClass(PageRankMapper.class);
            job.setReducerClass(PageRankReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);
            exit_code = job.waitForCompletion(true) ? 0 : 1;
            if(exit_code == 1) break;
            inputPath = outputPath + String.valueOf(i);
            i++;
        }
        System.exit(exit_code);
    }
}
