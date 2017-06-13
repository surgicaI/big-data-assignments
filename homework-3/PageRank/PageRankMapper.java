import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PageRankMapper extends Mapper<LongWritable, Text, Text, Text> {
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString(); 
		String[] splitVal = line.split("\t");
		
		double srcPageRank = Double.parseDouble(splitVal[splitVal.length-1]);
		double newPageRank = (srcPageRank * 1.0)/(splitVal.length-2) ;
		//StringBuilder sb = new StringBuilder(splitVal[0]);
		//sb.append("\t");
		//sb.append(String.valueOf(newPageRank));
		
		StringBuilder sb = new StringBuilder(String.valueOf(newPageRank));
		StringBuilder sb2 = new StringBuilder();
		
		for(int i = 1; i < splitVal.length-1 ; i++) { 
			sb2.append(splitVal[i]);
			if(i != splitVal.length-2) sb2.append("\t");
			context.write(new Text(splitVal[i]), new Text(sb.toString())); 
		}
		
		context.write(new Text(splitVal[0]), new Text(sb2.toString()));
	}
}
