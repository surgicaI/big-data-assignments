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
		int forExit = 1;	
		StringBuilder inputPath = new StringBuilder(args[0]);
		StringBuilder outputPath = new StringBuilder(args[1]);
		while(i++ <= 3){
			Job job = new Job();
			job.setJarByClass(PageRank.class);
			job.setJobName("Page Rank");
			FileInputFormat.addInputPath(job, new Path(inputPath.toString()));
			FileOutputFormat.setOutputPath(job, new Path(outputPath.toString()));
			job.setMapperClass(PageRankMapper.class);
			job.setReducerClass(PageRankReducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			while(true){
				if(job.waitForCompletion(true)){
					forExit = 0;
					break;
				}	
			}
			inputPath = new StringBuilder(outputPath.toString());
			outputPath.append(String.valueOf(i));
		}
		System.exit(forExit);
	}
}
